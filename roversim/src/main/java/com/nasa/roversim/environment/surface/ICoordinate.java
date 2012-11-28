package com.nasa.roversim.environment.surface;

/**
 * Manages a collection of dimensions
 */
public interface ICoordinate<T> {
	public ICoordinate<T> add(ICoordinate<T> value);
	public T get(Dimension dimension);
}
