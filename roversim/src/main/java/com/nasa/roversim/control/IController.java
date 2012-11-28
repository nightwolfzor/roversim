package com.nasa.roversim.control;

import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * The Controller class is responsible for implementing the rules of the
 * simulation itself. This class contains the necessary logic to manage
 * the environments and the agents running in these environments
 * @author Nathan
 *
 */
public interface IController {
	
	public String sendCommand(String line);
	
	public String addSurfaceToEnvironment(ISurface surface);

	public String addRoverToPlatform(IRover rover);
	
	public String moveRoverInEnvironmment(Iterable<Character> commands);
}
