package com.nasa.roversim.environment.plateau;

import java.util.HashSet;

import com.nasa.roversim.environment.IEnvironment;
import com.nasa.roversim.environment.Movement;
import com.nasa.roversim.environment.exception.MoveException;
import com.nasa.roversim.environment.exception.PositionOutOfBoundsException;
import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * An implementation of the IEnvironment for the Mars Plateau
 * @author Nathan
 *
 */
public class MarsPlateauEnvironment implements IEnvironment {
	
	//private final static Logger LOGGER = Logger.getLogger(MarsPlateauEnvironment.class);
	
	private HashSet<IRover> rovers = new HashSet<>();
	private ISurface surface;

	@Override
	public void addRover(IRover rover) throws PositionOutOfBoundsException, MoveException {
		if(!roverLocationOk(rover))
			throw new PositionOutOfBoundsException(surface, rover);
		if(roverCrash(rover))
			throw new MoveException("ERRROR ROVER LANDING CRASH", rover);
		rovers.add(rover);
	}

	@Override
	public void removeRover(IRover rover) {
		rovers.remove(rover);
	}

	@Override
	public void setSurface(ISurface surface) {
		this.surface = surface;
	}

	@Override
	public void moveRover(IRover rover, Movement movement) throws MoveException, PositionOutOfBoundsException {
		rover.move(movement);
		if(!roverLocationOk(rover))
			throw new PositionOutOfBoundsException(surface, rover);
		if(roverCrash(rover))
			throw new MoveException("ERRROR ROVER CRASH", rover);
	}
	
	private boolean roverLocationOk(IRover rover){
		return surface.isValid(rover.currentPosition());
	}
	
	private boolean roverCrash(IRover rover){
		for(IRover r : rovers){
			if(!rover.equals(r) && r.currentPosition().equals(rover.currentPosition()))
				return true;
		}
		return false;
	}
	
}
