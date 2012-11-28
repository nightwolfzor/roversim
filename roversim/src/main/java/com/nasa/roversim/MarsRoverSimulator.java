package com.nasa.roversim;

import java.util.ArrayList;
import java.util.Scanner;

import com.nasa.roversim.control.Controller;
import com.nasa.roversim.environment.plateau.MarsPlateauEnvironment;

/**
 * @author Nathan McCarthy
 * Mars Rover Simulator for Covata
 * 
 * ASSUMPTIONS: There is no mention of what happens when a rover
 * enters into an invalid state by either falling off the plateau
 * or if a rover can occupy more than one grid.
 * Therefore it is assumed that if a rover is commanded to drive 
 * outside the surface bounds then it dies (i.e. crashes) and 
 * its simulation stops. 
 * Likewise it is assumed that each grid can only contain one rover,
 * if more than one rover is in a grid (or tries to enter a grid) it
 * is assumed that the rover entering that grid goes into an emergency
 * shutdown to avoid a crash. This means the rover remains in its last
 * state/position. 
 * Finally if a rover tries to land in a grid where another rover already
 * exists its assumed the rover again enters and emergency state and
 * aborts landing, crashing and becoming inoperable.
 * 
 * USAGE:
 *   Enter commands for each simulation in the prescribed sequence. 
 *   Enter a blank line to display the output & quit.
 * 
 * Java 7
 * 
 */
public class MarsRoverSimulator 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to the NASA Rover Simulator" );
        System.out.println("Please enter rover command sequence:");
        
        //Create the sim controller:
        Controller sim = new Controller(new MarsPlateauEnvironment());

        //Setup for reading input and saving results:
        Scanner scanner = new Scanner(System.in);
        String line = null;
        
        ArrayList<String> results = new ArrayList<>();
        String result = null;
        
        //Read commands and pass them to our environment
        while((line = scanner.nextLine()) != null && !line.isEmpty()){
        	result = sim.sendCommand(line);
        	if(result != null)
        		results.add(result);
        }
        
        //Display results;
        if(!results.isEmpty()){
        	System.out.println("Output:");
	        for(String res : results){
	        	System.out.println(res);
	        }
        }
        System.out.println("\nExiting simulator");
    }
}
