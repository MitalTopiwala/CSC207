package ca.utoronto.utm.lab04;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayWithShapes {

	// 0) Review OO notes/examples from lecture
	// 1) Declare a variable "shapes" that will reference an array of reference to Shape
	Shape [] shapes;
	
	public PlayWithShapes() {
	
		// 2) Make shapes equal a new array of 10 references to Shapes
        shapes = new Shape[10];
		
		// 3) point shapes[0], ..., shapes[9] to new Rectangles and Circles 
		shapes[0]=new Circle("red", 10, 8,5);
		shapes[1]=new Circle("green", 20, 28, 15);
		shapes[2]=new Rectangle("orange", 5, 10, 20, 41);
		shapes[3]=new Circle("red", 10, 5,5);
		shapes[4]=new Circle("green", 20, 20, 15);
		shapes[5]=new Rectangle("orange", 5, 17, 20, 10);
		shapes[6]=new Circle("red", 10, 5,8);
		shapes[7]=new Circle("blue", 20, 21, 16);
		shapes[8]=new Rectangle("orange", 5, 18, 20, 44);
		shapes[9]=new Circle("red", 10, 9,5);
		
		// ...
		
	}
	
	/**
	 * Print the String representation of all Shapes referenced to 
	 * within the "shapes" array.s
	 * (This calls the .toString() method for each Shape).
	 */
	public void printShapes() {
		for (Shape s:shapes) {
			System.out.println(s);
		}
	}
	
	// 4) Complete moveShapes below to satisfy its javadoc

	/**
	 * Change the position of all referenced Shapes in the shapes array 
	 * by the given dx and dy
	 * @param dx the amount to change all shapes x coordinates
	 * @param dy the amount to change all shapes y coordinates
	 */
	public void moveShapes(int dx, int dy) {
		//TODO: Complete this method
		
		for(int i=0;i<10;i++) {
		    shapes[i].setX(dx);
		    shapes[i].setY(dy);
		}//for
	}//moveShapes

	// 5) Complete colorShapes below to satisfy its javadoc

	/**
	 * Change the color of all referenced Shapes in the shapes array
	 * to the given color
	 * @param c the color that all the shapes should be set to
	 */
	public void colorShapes(String c) {
		//TODO: Complete this method
		for(int i=0;i<10;i++) {
		    shapes[i].setColor(c);
		}//for
	}//colorShapes
	
	
	public static void main(String[] args) {
		
		PlayWithShapes s = new PlayWithShapes();
		
		// 6) Move all of the shapes around a bit
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("Enter 'move' or 'color': ");
			String choice = scanner.nextLine();
			if (choice.equals("move")) {
				System.out.print("dx: ");
				String dxs=scanner.nextLine();
				int dx=Integer.parseInt(dxs);
			
				System.out.print("dy: ");
				String dys=scanner.nextLine();
				int dy=Integer.parseInt(dys);
			
				s.moveShapes(dx, dy);
				s.printShapes();
			} else if (choice.equals("color")) {
				System.out.print("what color?: ");
				String newColor = scanner.nextLine();
				s.colorShapes(newColor);
				s.printShapes();
			}
		}
	}

}
