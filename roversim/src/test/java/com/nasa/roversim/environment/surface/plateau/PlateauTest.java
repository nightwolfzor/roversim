package com.nasa.roversim.environment.surface.plateau;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nasa.roversim.environment.plateau.Coordinate;
import com.nasa.roversim.environment.plateau.Plateau;

public class PlateauTest {

	private Plateau plateau;
	@Before
	public void setUp() throws Exception {
		plateau = new Plateau();
	}

	@Test
	public void testGetSize() {
		plateau.setSize(new Coordinate(5, 5));
		assertEquals(new Coordinate(5, 5), plateau.getSize());
	}

	@Test
	public void testSetSize() {
		plateau.setSize(new Coordinate(5, 5));
		assertNotNull(plateau.getSize());	
	}

	@Test
	public void testIsValid() {
		plateau.setSize(new Coordinate(5, 5));
		assertTrue(plateau.isValid(new Coordinate(5, 5)));
		assertTrue(plateau.isValid(new Coordinate(0, 0)));
		assertTrue(plateau.isValid(new Coordinate(2, 4)));
	}
	
	@Test
	public void testIsNotValid() {
		plateau.setSize(new Coordinate(5, 5));
		assertFalse(plateau.isValid(new Coordinate(6, 2)));
		assertFalse(plateau.isValid(new Coordinate(0, 6)));
		assertFalse(plateau.isValid(new Coordinate(-1, -4)));
	}

}
