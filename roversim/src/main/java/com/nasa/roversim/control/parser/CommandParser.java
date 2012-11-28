package com.nasa.roversim.control.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.nasa.roversim.environment.Direction;
import com.nasa.roversim.environment.plateau.Coordinate;
import com.nasa.roversim.environment.plateau.Plateau;
import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;
import com.nasa.roversim.rover.Rover;

/**
 * A singleton which contains the rules for reading the text
 * commands entered via the console.
 * @author Nathan
 *
 */
public class CommandParser implements ICommandParser {
	
	private static final String COMMAND_SEPARATOR = " ";
	
	private static CommandParser singleton;

	
	private CommandParser(){
		//singleton
	}
	
	public static CommandParser getInstance(){
		return singleton == null ? new CommandParser() : singleton;
	}
	
	public ISurface setupEnvironment(String size){
		Scanner sc = new Scanner(size).useDelimiter(COMMAND_SEPARATOR);
		Plateau env = new Plateau();
		env.setSize(new Coordinate(sc.nextInt(), sc.nextInt()));
		return env;
	}
	
	public IRover buildRover(String roverInit){
		Scanner sc = new Scanner(roverInit).useDelimiter(COMMAND_SEPARATOR);
		Coordinate startCoord = new Coordinate(sc.nextInt(), sc.nextInt());
		Direction orientation = Direction.fromCommand(sc.next());
		Rover rover = new Rover();
		rover.setup(startCoord, orientation);
		return rover;
	}
	
	public List<Character> movementCommands(String movementCmd){
		char[] c = movementCmd.toCharArray();   
        List<Character> cList = new ArrayList<Character>();   
        for(char ch : c){   
            cList.add(ch);   
        }
        return cList;
	}
	
	//Prevent clones for singleton
	public Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException(); 
	}
}
