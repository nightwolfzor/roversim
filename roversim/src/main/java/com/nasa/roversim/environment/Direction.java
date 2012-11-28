package com.nasa.roversim.environment;

/**
 * 
 * @author Nathan
 * Maintains the directions allowed in this environment
 *
 */
public enum Direction {
	North("N"),
	South("S"),
	East("E"),
	West("W");
	
	private String directionCode;
	
	private Direction(String code){
		directionCode = code;
	}
	
	public String getDirectionCode(){
		return directionCode;
	}
	
	public static Direction fromCommand(final String dirCode){
		switch(dirCode.toUpperCase()){
			case "N":
				return North;
			case "S":
				return South;
			case "E":
				return East;
			case "W":
				return West;
		}
		return null;
	}
	
	public String directionCode(){
		return directionCode;
	}
}
