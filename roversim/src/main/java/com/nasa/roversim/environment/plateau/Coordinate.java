package com.nasa.roversim.environment.plateau;

import com.nasa.roversim.environment.surface.Dimension;
import com.nasa.roversim.environment.surface.ICoordinate;

/**
 * An implementation of ICoordinate for this simulation which 
 * has two dimensions, x & y of type Integer. 
 * @author Nathan
 *
 */
public class Coordinate implements ICoordinate<Integer>{
	private Integer x;
	private Integer y;
	
	public Coordinate(Integer x, Integer y){
		this.x=x;
		this.y=y;
	}
	
	@Override
	public ICoordinate<Integer> add(ICoordinate<Integer> value){
		return new Coordinate(x + value.get(Dimension.X), y + value.get(Dimension.Y));
	}

	@Override
	public String toString() {
		return "" + x + " " + y;
	}

	@Override
	public Integer get(Dimension dimension) {
		switch(dimension){
			case X:
				return x;
			case Y:
				return y;
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
}
