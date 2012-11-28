package com.nasa.roversim.rover;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nasa.roversim.environment.Direction;
import com.nasa.roversim.environment.Movement;
import com.nasa.roversim.environment.plateau.Coordinate;
import com.nasa.roversim.environment.surface.ICoordinate;

/**
 * A rover implementation
 * @author Nathan
 *
 */
public class Rover implements IRover{
	
	private final static Logger LOGGER = Logger.getLogger(Rover.class);
	
	private Coordinate position;
	private Coordinate lastPosition;
	private Direction orientation;
	
	private static final Map<Direction, Direction> leftDirectionMap;
	private static final Map<Direction, Direction> rightDirectionMap;
	private static final Map<Direction, Coordinate> movementMap;

	static{
		//Could use ordinal of Enums but this relies on it being in order - not ideal for changes
		HashMap<Direction, Direction> leftMap = new HashMap<>();
		leftMap.put(Direction.North, Direction.West);
		leftMap.put(Direction.West, Direction.South);
		leftMap.put(Direction.South, Direction.East);
		leftMap.put(Direction.East, Direction.North);
		leftDirectionMap = Collections.unmodifiableMap(leftMap);
		
		HashMap<Direction, Direction> rightMap = new HashMap<>();
		rightMap.put(Direction.North, Direction.East);
		rightMap.put(Direction.East, Direction.South);
		rightMap.put(Direction.South, Direction.West);
		rightMap.put(Direction.West, Direction.North);
		rightDirectionMap = Collections.unmodifiableMap(rightMap);
		
		HashMap<Direction, Coordinate> moveMap = new HashMap<>();
		moveMap.put(Direction.North, new Coordinate(0,1));
		moveMap.put(Direction.South, new Coordinate(0,-1));
		moveMap.put(Direction.East, new Coordinate(1,0));
		moveMap.put(Direction.West, new Coordinate(-1,0));
		movementMap = Collections.unmodifiableMap(moveMap);
	}

	@Override
	public void move(Movement movement) {
		orientation = changeOrientation(movement);
		position = (Coordinate) moveRover(movement);
		if(LOGGER.isDebugEnabled())
			LOGGER.debug("Ori=" + orientation + " Coord=" + position);
	}
	
	private Direction changeOrientation(Movement move){
		switch(move){
			case Left:
				return leftDirectionMap.get(orientation);
			case Right:
				return rightDirectionMap.get(orientation);
		}
		return orientation;
	}
	
	private ICoordinate<?> moveRover(Movement move){
		if(move.equals(Movement.MoveFwd)){
			lastPosition = position;
			return position.add(movementMap.get(orientation));
		}
		return position;
	}

	@Override
	public void setup(ICoordinate<?> startPosition, Direction direction) {		
		this.position = (Coordinate) startPosition;
		this.orientation = direction;
	}


	@Override
	public ICoordinate<?> currentPosition() {
		return position;
	}

	@Override
	public boolean undoMove(){
		//If the move we tried to make is rejected by the environment, 
		// the environment may allow us to go back one
		if(lastPosition != null){
			position = lastPosition;
			lastPosition = null;
			return true;
		}else
			LOGGER.warn("WARNING: No last position for Rover, rover reamining stationary");
		return false;
	}

	@Override
	public Direction currentOrientation() {
		return orientation;
	}
	
	

}
