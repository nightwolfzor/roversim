package com.nasa.roversim.rover;

import com.nasa.roversim.environment.Direction;
import com.nasa.roversim.environment.Movement;
import com.nasa.roversim.environment.surface.ICoordinate;

/**
 * Rover interface - a rover contains the logic defining how it can 
 * move about an environment
 * @author Nathan
 *
 */
public interface IRover {
	public void move(Movement movement);
	public boolean undoMove();
	public void setup(ICoordinate<?> startPosition, Direction direction);
	public ICoordinate<?> currentPosition();
	public Direction currentOrientation();
}
