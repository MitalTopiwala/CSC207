package ca.utoronto.utm.assignment1.q2;

import ca.utoronto.utm.assignment1.puzzle.Move;
import ca.utoronto.utm.assignment1.puzzle.Puzzle;
import ca.utoronto.utm.assignment1.puzzle.States;
import ca.utoronto.utm.assignment1.puzzle.State;
//import ca.utoronto.utm.assignment1.q2.JugMove;


import java.util.ArrayList;

/**
 * 
 * @author csc207student 
 *
 */
public class JugPuzzle extends Puzzle{
	public Jug[] jugList;
	
	public int numMoves;
	
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
		
	}//getMoves
	
	
	
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
	 * Creates a copy of JugPuzzle
	 * @return copy of JugPuzzle
	 **/
	public JugPuzzle copy() {
		
		JugPuzzle rPuzzle = new JugPuzzle();
		
		for(int i = 0; i < 3; i++) {
		    rPuzzle.jugList[i].amount = this.jugList[i].amount;
		}
		return rPuzzle;
		
	}//copy
		
	
	
	/**
	 * Creates and returns a string representation of JugPuzzle
	 * @return A string representation of JugPuzzle
	 **/
    public String toString() {
    	
    	String rV = this.numMoves+ " 0:("+ this.jugList[0].amount+"/"+this.jugList[0].capacity+") 1:("+ this.jugList[1].amount+"/"+this.jugList[1].capacity+") 2:("+ this.jugList[2].amount+"/"+this.jugList[2].capacity+")";
		return rV;
		
	}//toString

    
    
	/**
	 * Returns a JugMove arraylist of all of the possible moves the JugPuzzle can make 
	 * @return a array list of all next possible moves
	 **/
    public JugMove[] allPossibleMoves() {
    	
    	JugMove one = new JugMove(0,1);
    	JugMove two = new JugMove(0,2);
    	JugMove three = new JugMove(1,0);
    	JugMove four = new JugMove(1,2);
    	JugMove five = new JugMove(2,0);
    	JugMove six = new JugMove(2,1);
    	
    	JugMove [] allMoves = new JugMove []{one,two,three,four,five,six};
		return allMoves;
		
	}//allPossibleMoves
    
	/**
	 * Adds all of the next possible states of the JugPuzzle, and adds them to states
	 * @param states the arraylist all the next possible states will be added to
	 **/
	public void nextStates(States states) {
		
		JugMove [] allPossibleMoves;
		allPossibleMoves = allPossibleMoves();
		
		
		for (JugMove move: allPossibleMoves) {
			JugPuzzle tempPuzzle = this.copy();//enter the jugs in
			
			int from = move.start;
			int to = move.end;
			tempPuzzle.move(from, to);
			states.add(tempPuzzle, move);
		}//for
	
	}//nextStates
	
	

}//JugPuzzle
