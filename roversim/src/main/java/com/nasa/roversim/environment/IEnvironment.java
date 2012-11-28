package com.nasa.roversim.environment;

import com.nasa.roversim.environment.exception.MoveException;
import com.nasa.roversim.environment.exception.PositionOutOfBoundsException;
import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * An environment maintains a list of the agents (rovers) running
 * along with a certain instance of a surface, in this case we only
 * have one surface - a Plateau
 * @author Nathan
 *
 */
public interface IEnvironment {
			
	public void addRover(IRover rover) throws PositionOutOfBoundsException, MoveException;
	
	public void removeRover(IRover rover);
	
	public void setSurface(ISurface surface);
	
	public void moveRover(IRover rover, Movement movement) throws MoveException, PositionOutOfBoundsException;
}
