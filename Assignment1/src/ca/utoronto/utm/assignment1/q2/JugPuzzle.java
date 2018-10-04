package ca.utoronto.utm.assignment1.q2;

/**
 * 
 * @author csc207student 
 *
 */
public class JugPuzzle {
	private int [] jugAmount;
	private int [] jugCapacity;
	
	private int numMoves;
	
	public JugPuzzle() {
		jugAmount = new int[3];
		jugCapacity = new int[3];
		
		this.jugCapacity[0] = 8;
		this.jugCapacity[1] = 5;
		this.jugCapacity[2] = 3;
		
		this.jugAmount[0] = 8;
		this.jugAmount[1] = 0;
		this.jugAmount[2] = 0;
		
		
		this.numMoves = 0;
		
	}//constructor
	
	/**
	 *  Makes a move
	 *  @param from The jug water is spilled from
	 *  @param to The jug water is being spilled into 
	 **/
	public void move(int from, int to) {
		int amountToPour = this.jugCapacity[to] - this.jugAmount[to];
		int amountInStart = this.jugAmount[from];
		
		if (amountInStart >= amountToPour) {
			this.jugAmount[from] = this.jugAmount[from] - amountToPour;
			this.jugAmount[to] = this.jugAmount[to] + amountToPour;
		}else {
			this.jugAmount[from] = 0;
			this.jugAmount[to] = this.jugAmount[to] + amountInStart;
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
		if(this.jugAmount[0] == 4 && this.jugAmount[1] ==4) {
			return true;
		}
		return false;
	}//isPuzzleSolved
	
	/**
	 * Creates and returns a string representation of JugPuzzle
	 * @return A string representation of JugPuzzle
	 **/
    public String toString() {
    	String rV = this.numMoves+ " 0:("+ this.jugAmount[0]+"/"+this.jugCapacity[0]+") 1:("+ this.jugAmount[1]+"/"+this.jugCapacity[1]+") 2:("+ this.jugAmount[2]+"/"+this.jugCapacity[2]+")";
		return rV;
	}//toString

}
