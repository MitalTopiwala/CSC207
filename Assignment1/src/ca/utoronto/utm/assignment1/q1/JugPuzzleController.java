package ca.utoronto.utm.assignment1.q1;
import java.io.*;
import java.util.*;
/**
 * @author csc207student
 * This class allows a console user to play an instance of JugPuzzle.
 */
public class JugPuzzleController {
        private static final String INVALID_INPUT_MESSAGE="Invalid number, please enter 0,1 or 2";

        private Scanner scanner;
        private JugPuzzle jugPuzzle;

        /**
         * Constructs a new JugPuzzleController with a new JugPuzzle, 
         * ready to play with a user at the console.
         */
        public JugPuzzleController(){
                jugPuzzle=new JugPuzzle();
                scanner=new Scanner(System.in);
        }

        /**
         * Checks if the move is possible and returns the jug number if it is 
         * 
         * @param message A String that narrates what action is being done (i.e spills form jug:, into jug:, etc.)
         * @param lower   the lower bound (the first jug) 
         * @param upper   the upper bound (the last jug)
         * @return        the jug we could perform an action on(i.e the int in the second line of message)
         */
        private int getMove(String message, int lower, int upper){
                int move;
                while(true){
                        try {
                                System.out.print(message);
                                String line=scanner.nextLine();
                                move=Integer.parseInt(line);
                                if(lower<=move && move<=upper){
                                        return move;
                                } else {
                                        System.out.println(INVALID_INPUT_MESSAGE);
                                }
                        }
                        catch(NumberFormatException e){
                                System.out.println(INVALID_INPUT_MESSAGE);
                        }
                }
        }
        /**
         * Allows the user to play the game by continuously asking the user for input
         * Stops asking for input once the user has solved the puzzle
         * Then informs the user of how many moves it took them to solve the puzzle.
         */
        public void play(){
                while(!jugPuzzle.isPuzzleSolved()){
                        System.out.println(jugPuzzle); // called the toString() method of jugPuzzle
                        int from, to;
                        from = getMove("spill from jug: ", 0,2);
                        to   = getMove("into jug: ",0,2);
                        jugPuzzle.move(from,to);
                }
                if(jugPuzzle.getMoves()==1) {
                    System.out.println("Congrats you solved it in "+jugPuzzle.getMoves()+" move!!");
                } else {
                	System.out.println("Congrats you solved it in "+jugPuzzle.getMoves()+" moves!!");
                }
        }
        
    	/**
    	 * Creates a string representation of the game so far
    	 * @return A string representation of the game
    	 **/
        public String toString() {
    		String rV = "Return Value";
    		return rV;
    	}//toString

        public static void main(String [] args){
                JugPuzzleController jpcc=new JugPuzzleController();
                jpcc.play();
        }
}
