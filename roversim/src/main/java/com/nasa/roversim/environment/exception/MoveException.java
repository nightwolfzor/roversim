package com.nasa.roversim.environment.exception;

import com.nasa.roversim.rover.IRover;

/**
 * An exception used to indicate a potential crash between two rovers
 * @author Nathan
 *
 */
public class MoveException extends EnvironmentException{
	private static final long serialVersionUID = -3169096451017360946L;
	
	private String errorMsg;
	private IRover rover;
	
	public MoveException(String msg) {
		this(msg, null);
	}
	
	public MoveException(String msg, IRover rover) {
		this.errorMsg = msg;
		this.rover = rover;
	}
	
	public String information() {
		String msg = errorMsg;
		if (rover != null)
			msg += " - Bad rover position@"
					+ rover.currentPosition().toString();
		return msg;
	}
}