package com.nasa.roversim.environment.exception;

import com.nasa.roversim.environment.surface.ISurface;
import com.nasa.roversim.rover.IRover;

/**
 * Indicates that a rover has fallen outside the bounds of the environment
 * @author Nathan
 *
 */
public class PositionOutOfBoundsException extends EnvironmentException{
	private static final long serialVersionUID = 7937119586095267546L;
	
	private ISurface surface;
	private IRover rover;

	public PositionOutOfBoundsException(ISurface surface) {
		this(surface, null);
	}

	public PositionOutOfBoundsException(ISurface surface, IRover rover) {
		this.surface = surface;
		this.rover = rover;
	}

	public String information() {
		return "Error: Rover position out of bounds " + surface.getSize()
				+ " Rover requested @" + rover.currentPosition();
	}
}
