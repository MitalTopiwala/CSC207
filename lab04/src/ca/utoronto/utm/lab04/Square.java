package ca.utoronto.utm.lab04;

public class Square extends Rectangle{
	
	public Square() {
		this("blue", 10, 10, 100, 100);
	}
	public Square(String c, int width, int x, int y) {
		Rectangle(c, width, width, x, y);
	}
	

}
