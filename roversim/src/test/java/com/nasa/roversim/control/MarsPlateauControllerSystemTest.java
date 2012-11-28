package com.nasa.roversim.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.InputMismatchException;

import org.junit.Before;
import org.junit.Test;

import com.nasa.roversim.environment.plateau.MarsPlateauEnvironment;

public class MarsPlateauControllerSystemTest {
	
	private Controller controller;


	@Before
	public void setUp() throws Exception {
		controller = new Controller(new MarsPlateauEnvironment());
		//Init the surface
		controller.sendCommand("5 5");
	}

	@Test
	public void testRoverA() {
		assertNull(controller.sendCommand("1 2 N"));
		assertEquals("1 3 N", controller.sendCommand("LMLMLMLMM"));
	}

	@Test
	public void testRoverB() {
		assertNull(controller.sendCommand("3 3 E"));
		assertEquals("5 1 E", controller.sendCommand("MMRMMRMRRM"));
	}
	
	@Test
	public void testMakeOutofBoundRoverContinuation() {
		controller.sendCommand("5 6 E");
		testRoverB();
	}
	
	@Test
	public void testRoverCrashAtSamePoint() {
		testRoverA();
		assertNull(controller.sendCommand("1 2 N"));
		assertEquals("1 2 N", controller.sendCommand("LMLMLMLMM"));
	}
	
	@Test(expected = InputMismatchException.class)
	public void testBadInputSequence() {
		controller.sendCommand("MMRMMRMRRM");
	}
	
	@Test(expected = InputMismatchException.class)
	public void testMakeOutofBoundRoverWithBadSequence() {
		assertNull(controller.sendCommand("5 6 E"));
		controller.sendCommand("MMRMMRMRRM");
	}

}
