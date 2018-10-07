package ca.utoronto.utm.assignment1.q2;

import ca.utoronto.utm.assignment1.puzzle.Move;


public class JugMove implements Move{
	int start;
	int end;

	public JugMove(int from, int to) {
		this.start = from;
		this.end = to;
		
	}
	public String toString() {
		return "Move from:"+start+" to:"+end;
	}
  
}
