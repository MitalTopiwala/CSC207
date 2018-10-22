package ca.utoronto.utm.assignment1.q2;

import ca.utoronto.utm.assignment1.puzzle.Move;
/**
 * Implements the Move class 
 * 
 * @author mital
 *
 */

public class JugMove implements Move{
	int start;
	int end;

	/**
	 * Creates a JugMove
	 * @param from the Jug that we are spilling water from
	 * @param to the Jug that we are spilling water into
	 **/
	public JugMove(int from, int to) {
		this.start = from;
		this.end = to;
		
	}
	/**
	 * Creates a string representation of JugMove
	 * @return A string representation of JugMove
	 **/
	public String toString() {
		return "Move from:"+start+" to:"+end;
	}
  
}
