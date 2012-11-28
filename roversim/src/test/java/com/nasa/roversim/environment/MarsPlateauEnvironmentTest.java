package com.nasa.roversim.environment;

import org.junit.Before;
import org.junit.Test;

import com.nasa.roversim.environment.exception.EnvironmentException;
import com.nasa.roversim.environment.exception.MoveException;
import com.nasa.roversim.environment.exception.PositionOutOfBoundsException;
import com.nasa.roversim.environment.plateau.Coordinate;
import com.nasa.roversim.environment.plateau.MarsPlateauEnvironment;
import com.nasa.roversim.environment.plateau.Plateau;
import com.nasa.roversim.rover.Rover;

public class MarsPlateauEnvironmentTest {
	
	private MarsPlateauEnvironment env;

	private Rover buildRover(Integer x, Integer y){
		Rover r = new Rover();
		r.setup(new Coordinate(x,y), Direction.North);
		return r;
	}
	
	@Before
	public void setUp() throws Exception {
		env = new MarsPlateauEnvironment();
		Plateau p = new Plateau();
		p.setSize(new Coordinate(5, 5));
		env.setSurface(p);
	}

	@Test
	public void testValidRoverMove() throws EnvironmentException {
		env.moveRover(buildRover(1,0), Movement.Right);
		env.moveRover(buildRover(1,0), Movement.Left);
	}
	
	@Test(expected = PositionOutOfBoundsException.class)
	public void testInvalidRoverMoveOffSurface() throws EnvironmentException {
		env.moveRover(buildRover(2,5), Movement.MoveFwd);
	}
	
	@Test
	public void testValidRoverLand() throws EnvironmentException{
		env.addRover(buildRover(3,4));
	}
	
	@Test(expected = PositionOutOfBoundsException.class)
	public void testInvalidRoverLandX() throws EnvironmentException {
		env.addRover(buildRover(7,2));
	}
	
	@Test(expected = PositionOutOfBoundsException.class)
	public void testInvalidRoverLandY() throws EnvironmentException {
		env.addRover(buildRover(5,6));
	}
	
	@Test(expected = MoveException.class)
	public void testInvalidRoverLandCrash() throws EnvironmentException {
		env.addRover(buildRover(2,2));
		env.addRover(buildRover(2,2));
	}
	
	@Test(expected = MoveException.class)
	public void testRoverLandCrashOneRoverMoved() throws EnvironmentException {
		Rover r1 = buildRover(0,0);
		Rover r2 = buildRover(0,1);
		env.addRover(r1);
		env.moveRover(r1, Movement.MoveFwd);
		env.addRover(r2);
	}
	
	@Test(expected = MoveException.class)
	public void testRoversCrashIntoEachOther() throws EnvironmentException {
		Rover r1 = buildRover(0,0);
		Rover r2 = buildRover(0,1);
		env.addRover(r1);
		env.addRover(r2);
		env.moveRover(r1, Movement.MoveFwd);
	}

}
