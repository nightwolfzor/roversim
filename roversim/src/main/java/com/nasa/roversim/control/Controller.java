package com.nasa.roversim.control;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.nasa.roversim.control.parser.CommandParser;
import com.nasa.roversim.control.parser.ICommandParser;
import com.nasa.roversim.environment.IEnvironment;
import com.nasa.roversim.environment.Movement;
import com.nasa.roversim.environment.exception.MoveException;
import com.nasa.roversim.environment.exception.PositionOutOfBoundsException;
import com.nasa.roversim.environment.surface.Dimension;
import com.nasa.roversim.environment.surface.ICoordinate;
import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * The Controller class is responsible for implementing the rules of the
 * simulation itself. This class contains the necessary logic to manage
 * the environments and the agents running in these environments
 * @author Nathan
 *
 */
public class Controller implements IController {
	private final static Logger LOGGER = Logger.getLogger(Controller.class);
	
	private ICommandParser cmdParser;
	private IEnvironment environment;
	private IRover currentRover = null;

	public Controller(IEnvironment env) {
		environment = env;
		cmdParser = CommandParser.getInstance();
	}

	@Override
	public String sendCommand(String line) {
		if (line.matches("^\\d+ \\d+$")) {
			// Landing Environment init commands
			return addSurfaceToEnvironment(cmdParser.setupEnvironment(line));
		} else if (line.matches("^\\d+ \\d+ [NSEW]$")) {
			// Rover Init Location Commands
			return addRoverToPlatform(cmdParser.buildRover(line));
		} else if (line.matches("^[LRM]+$")) {
			// Rover Movement Commands
			if (currentRover == null) {
				throw new InputMismatchException("Current Rover is null - please ensure correct command sequence.");
			}
			return moveRoverInEnvironmment(cmdParser.movementCommands(line));
		}
		return null;
	}
	
	public String addSurfaceToEnvironment(ISurface surface){
		environment.setSurface(surface);
		return null;
	}
	
	public String addRoverToPlatform(IRover rover){
		currentRover = rover;
		try {
			environment.addRover(currentRover);
		} catch (PositionOutOfBoundsException|MoveException e) {
			cleanupCurrentRover(); //remove the rover as its dead
			LOGGER.error("Could not deploy rover to surface, rover has crashed; " + e.information(), e);
		}
		return null;
	}
	
	public String moveRoverInEnvironmment(Iterable<Character> commands){
		try {
			for (Character c : commands) {
				environment.moveRover(currentRover,	Movement.fromCommand(c.toString()));
			}
		} catch (MoveException e) {
			return handleMoveException(e);
		} catch (PositionOutOfBoundsException e) {
			LOGGER.error("Move not in environment bounds, rover has crashed; " + e.information(), e);
			cleanupCurrentRover();
			return null;
		}
		return printRoverPosition(currentRover);
	}

	/** 
	 * Generate the output ready to be sent somewhere
	 * @param rover
	 * @return
	 */
	private String printRoverPosition(IRover rover) {
		ICoordinate<?> coord = rover.currentPosition();
		return "" + coord.get(Dimension.X) + " " + coord.get(Dimension.Y) + " "
				+ rover.currentOrientation().directionCode();
	}
	
	/**
	 * Implements the collision avoidance property for the environments
	 * @param e Handels a move exception
	 * @return
	 */
	private String handleMoveException(MoveException e){
		if(!currentRover.undoMove())
			throw new RuntimeException("ERROR: could not move rover back to original position - state wrong");
		String stallPosition = printRoverPosition(currentRover);
		currentRover = null;
		LOGGER.error("Move failed, rover emergency shutdown, stopping execution; " + e.information(), e);
		return stallPosition;
	}
	
	/**
	 * Removes a rover which has crashed
	 */
	private void cleanupCurrentRover() {
		environment.removeRover(currentRover);
		currentRover = null;
	}

}
