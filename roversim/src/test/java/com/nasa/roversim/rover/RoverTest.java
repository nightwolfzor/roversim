package com.nasa.roversim.rover;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.nasa.roversim.environment.Direction;
import com.nasa.roversim.environment.Movement;
import com.nasa.roversim.environment.plateau.Coordinate;

public class RoverTest {
	
	private Rover rover;

	@Before
	public void setUp() throws Exception {
		rover = new Rover();
		rover.setup(new Coordinate(0, 0), Direction.North);
	}

	@Test
	public void testMoveLeft() {
		rover.move(Movement.Left);
		assertEquals(Direction.West, rover.currentOrientation());
		rover.move(Movement.Left);
		assertEquals(Direction.South, rover.currentOrientation());
		rover.move(Movement.Left);
		assertEquals(Direction.East, rover.currentOrientation());
		rover.move(Movement.Left);
		assertEquals(Direction.North, rover.currentOrientation());
	}
	
	@Test
	public void testMoveRight() {
		rover.move(Movement.Right);
		assertEquals(Direction.East, rover.currentOrientation());
		rover.move(Movement.Right);
		assertEquals(Direction.South, rover.currentOrientation());
		rover.move(Movement.Right);
		assertEquals(Direction.West, rover.currentOrientation());
		rover.move(Movement.Right);
		assertEquals(Direction.North, rover.currentOrientation());
	}
	
	public void testMoveForward() {
		rover.move(Movement.MoveFwd);
		assertEquals(new Coordinate(0, 1), rover.currentPosition());
		rover.move(Movement.Right);
		rover.move(Movement.MoveFwd);
		assertEquals(new Coordinate(1, 1), rover.currentPosition());
		rover.move(Movement.Left);
		rover.move(Movement.MoveFwd);
		assertEquals(new Coordinate(2, 1), rover.currentPosition());
		rover.move(Movement.Left);
		rover.move(Movement.MoveFwd);
		assertEquals(new Coordinate(2, 0), rover.currentPosition());
		rover.move(Movement.Left);
		rover.move(Movement.MoveFwd);
		assertEquals(new Coordinate(1, 0), rover.currentPosition());
	}

	@Test
	public void testSetup() {
		assertNotNull(rover.currentOrientation());
		assertNotNull(rover.currentPosition());
	}

	@Test
	public void testCurrentPosition() {
		assertEquals(new Coordinate(0, 0), rover.currentPosition());
	}

//	@Test
//	public void testUndoMove() {
//		rover.move(Movement.MoveFwd);
//		rover.undoMove();
//		assertEquals(new Coordinate(0, 0), rover.currentPosition());
//	}
	
	@Test
	public void testCurrentOrientation() {
		assertEquals(Direction.North, rover.currentOrientation());
	}

}
