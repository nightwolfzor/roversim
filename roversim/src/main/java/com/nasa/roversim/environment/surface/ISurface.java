package com.nasa.roversim.environment.surface;


/**
 * 
 * @author Nathan
 * Surface is a gird made up of an ICoordinate
 */
public interface ISurface {
	
	public ICoordinate<?> getSize();
	
	public void setSize(ICoordinate<?> coordinate);
	
	public boolean isValid(ICoordinate<?> coordinate);

}
