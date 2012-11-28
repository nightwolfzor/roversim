package com.nasa.roversim.environment;

/**
 * Contains a list of the movements a rover can make in the environment
 * @author Nathan
 *
 */
public enum Movement {
	Left("L"),
	Right("R"),
	MoveFwd("M");
	
	private String directionCode;
	
	private Movement(String code){
		directionCode = code;
	}
	
	public String getDirectionCode(){
		return directionCode;
	}
	
	public static Movement fromCommand(final String dirCode){
		switch(dirCode.toUpperCase()){
			case "L":
				return Left;
			case "R":
				return Right;
			case "M":
				return MoveFwd;
		}
		return null;
	}
}
