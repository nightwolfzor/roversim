package com.nasa.roversim.environment.surface.plateau;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.nasa.roversim.environment.plateau.Coordinate;
import com.nasa.roversim.environment.surface.Dimension;

public class CoordinateTest {

	private Coordinate coord;
	
	@Before
	public void setUp() throws Exception {
		coord = new Coordinate(5, 5);
	}

	@Test
	public void testAdd() {
		assertEquals(new Coordinate(8, 9), coord.add(new Coordinate(3, 4)));
		assertEquals(new Coordinate(2, 1), coord.add(new Coordinate(-3, -4)));
	}

	@Test
	public void testGet() {
		assertEquals(Integer.valueOf(5), coord.get(Dimension.X));
		assertEquals(Integer.valueOf(5), coord.get(Dimension.Y));
	}

	@Test
	public void testEqualsObject() {
		assertEquals(new Coordinate(5, 5), coord);
	}

}
