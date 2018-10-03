package ca.utoronto.utm.assignment1.q1;

public class Jug {
	
	int capacity;
	int amount;
	
	/**
	 * Creates Jug 
	 * @param capacity the amount the jug could hold
	 * @param amount the amount of water in the cup
	 * @return A string representation of Jug
	 **/
	public Jug(int capacity, int amount) {
		this.capacity = capacity;
		this.amount = amount;
	}//constructor
	
	
	
	/**
	 * Creates a string representation of Jug
	 * @return A string representation of Jug
	 **/
	public String toString() {
		String rV = "("+this.amount+"/"+this.capacity+")";
		return rV;
	}//toString

}
