package ca.utoronto.utm.assignment1.q2;

import ca.utoronto.utm.assignment1.puzzle.Puzzle;
import ca.utoronto.utm.assignment1.puzzle.States;

/**
 * 
 * @author csc207student 
 *
 */
public class JugPuzzle extends Puzzle{
	private Jug[] jugList;
	
	private int numMoves;
	
	public JugPuzzle() {
		jugList = new Jug[3];
		this.jugList[0] = new Jug(8, 8);
		this.jugList[1] = new Jug(5, 0);
		this.jugList[2] = new Jug(3, 0);
		
		this.numMoves = 0;
		
	}//constructor
	
	/**
	 *  Makes a move
	 *  @param from The jug water is spilled from
	 *  @param to The jug water is being spilled into 
	 **/
	public void move(int from, int to) {
		int amountToPour = this.jugList[to].capacity - this.jugList[to].amount;
		int amountInStart = this.jugList[from].amount;
		
		if (amountInStart >= amountToPour) {
			this.jugList[from].amount = this.jugList[from].amount - amountToPour;
			this.jugList[to].amount = this.jugList[to].amount + amountToPour;
		}else {
			this.jugList[from].amount = 0;
			this.jugList[to].amount = this.jugList[to].amount + amountInStart;
		}
		this.numMoves += 1;
		
	}//move
	
	/**
	 * Return the number of moves taken so far in JugPuzzle
	 * @return Return the moves
	 **/
	public int getMoves() {
		return this.numMoves;
	}//getmoves
	
	/**
	 * Determines if the puzzle is solved
	 * @return Return true iff the puzzle is solved
	 **/
	public boolean isPuzzleSolved() {
		if(this.jugList[0].amount == 4 && this.jugList[1].amount ==4) {
			return true;
		}
		return false;
	}//isPuzzleSolved
	
	/**
	 * Creates and returns a string representation of JugPuzzle
	 * @return A string representation of JugPuzzle
	 **/
    public String toString() {
    	String rV = this.numMoves+ " 0:("+ this.jugList[0].amount+"/"+this.jugList[0].capacity+") 1:("+ this.jugList[1].amount+"/"+this.jugList[1].capacity+") 2:("+ this.jugList[2].amount+"/"+this.jugList[2].capacity+")";
		return rV;
	}//toString

	@Override
	public void nextStates(States states) {
		// TODO Auto-generated method stub
		
		
	}

}
