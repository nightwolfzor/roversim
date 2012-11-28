package com.nasa.roversim.control.parser;

import java.util.List;

import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * A command parser contains the logic for parsing the 
 * input commands into something meaningful
 * @author Nathan
 *
 */
public interface ICommandParser {
	public ISurface setupEnvironment(String size);
	
	public IRover buildRover(String roverInit);
	
	public List<Character> movementCommands(String movementCmd);
}
