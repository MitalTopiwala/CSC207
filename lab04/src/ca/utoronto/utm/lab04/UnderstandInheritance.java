package ca.utoronto.utm.lab04;
public class UnderstandInheritance {

	public static void main(String[] args) {
		// 6) Review class Square, understand what happens when we execute the following:
		// that is, which methods are called and when...
		
		System.out.println("Starting");
		Square s=new Square(); 
		// The line above calls the Square() constructor 
		// which executes this("blue", 10, 100, 100); which calls 
		// the Square(String c, int width, int x, int y) constructor 
		// which executes super(c, width, width, x, y); which calls setPosition and setColor in the Shape class
		System.out.println(s.toString());
		// The line above calls the Rectangke toSTring method, which also calls the super toString Method
		s.setWidth(20);
		s.setX(10);
		//Both of the lines above call the respective rectangle methods 
		
	}

}
