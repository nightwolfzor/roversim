package com.nasa.roversim.environment.plateau;

import com.nasa.roversim.environment.surface.Dimension;
import com.nasa.roversim.environment.surface.ICoordinate;
import com.nasa.roversim.environment.surface.ISurface;

/**
 * An implementation of ISurface which is comprised of a rectangular
 * grid represented by the Coordinate implementation. 
 * @author Nathan
 *
 */
public class Plateau implements ISurface {
		
	private Coordinate size;
	
	@Override
	public Coordinate getSize() {
		return size;
	}

	@Override
	public void setSize(ICoordinate<?> coordinate) {
		this.size = (Coordinate) coordinate;
	}

	@Override
	public boolean isValid(ICoordinate<?> coordinate) {
		Coordinate coord = (Coordinate) coordinate;
		boolean xOk = coord.get(Dimension.X) >= 0 && coord.get(Dimension.X) <= size.get(Dimension.X);
		boolean yOk = coord.get(Dimension.Y) >= 0 && coord.get(Dimension.Y) <= size.get(Dimension.Y);
		return xOk && yOk;
	}
	
}
