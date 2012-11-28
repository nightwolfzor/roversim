package com.nasa.roversim.environment.exception;

/**
 * A wrapper class for the Environment exceptions which contain some
 * pretty printing commands to give more information to the user
 * @author Nathan
 *
 */
public abstract class EnvironmentException extends Exception{
	private static final long serialVersionUID = 1L;

	public abstract String information();
}
