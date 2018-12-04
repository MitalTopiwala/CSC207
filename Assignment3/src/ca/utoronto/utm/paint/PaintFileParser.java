package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Parse a file in Version 1.0 PaintSaveFile format. An instance of this class
 * understands the paint save file format, storing information about
 * its effort to parse a file. After a successful parse, an instance
 * will have an ArrayList of PaintCommand suitable for rendering.
 * If there is an error in the parse, the instance stores information
 * about the error. For more on the format of Version 1.0 of the paint 
 * save file format, see the associated documentation.
 * 
 * @author 
 *
 */
public class PaintFileParser {
	private int lineNumber = 0; // the current line being parsed
	private String errorMessage =""; // error encountered during parse
	private PaintModel paintModel; 
	
	/**
	 * Below are Patterns used in parsing 
	 */
	private Pattern pFileStart=Pattern.compile("^PaintSaveFileVersion1.0$");
	private Pattern pFileEnd=Pattern.compile("^EndPaintSaveFile$");

	private Pattern pCircleStart=Pattern.compile("^Circle$");
	private Pattern pCircleEnd=Pattern.compile("^EndCircle$");
	// ADD MORE!!
	private Pattern pRectangleStart=Pattern.compile("^Rectangle$");
	private Pattern pRectangleEnd=Pattern.compile("^EndRectangle$");
	private Pattern pSquiggleStart=Pattern.compile("^Squiggle$");
	private Pattern pSquiggleEnd=Pattern.compile("^EndSquiggle$");
	
	//For all 3
	private Pattern pColour=Pattern.compile("^\\s\\s\\s\\scolour:[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+),[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+),[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)$");
	private Pattern pFilled=Pattern.compile("^\\s\\s\\s\\sfilled:(true|false)$");
	
	//Circle 
	private Pattern pCentre=Pattern.compile("^\\s\\s\\s\\scentre:\\(\\d+,\\d+\\)$");
	private Pattern pRadius=Pattern.compile("^\\s\\s\\s\\sradius:\\d+$");
	
	//Rectangle
	private Pattern pP1=Pattern.compile("^\\s\\s\\s\\sP1:\\(\\d+,\\d+\\)$");
	private Pattern pP2=Pattern.compile("^\\s\\s\\s\\sP1:\\(\\d+,\\d+\\)$");
	
	//Squiggle
	private Pattern pPoints=Pattern.compile("^\\s\\s\\s\\spoints$");
	private Pattern pPoint=Pattern.compile("^\\s\\s\\s\\spoint:\\(\\d+,\\d+\\)$");//for each point
	
	//Finding a double value
	private Pattern pDouble=Pattern.compile("[+]?([0-9]+(?:[\\.][0-9]*)?|\\.[0-9]+)");
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
	}
	
	/**
	 * 
	 * @return the error message resulting from an unsuccessful parse
	 */
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	/**
	 * Parse the inputStream as a Paint Save File Format file.
	 * The result of the parse is stored as an ArrayList of Paint command.
	 * If the parse was not successful, this.errorMessage is appropriately
	 * set, with a useful error message.
	 * 
	 * @param inputStream the open file to parse
	 * @param paintModel the paint model to add the commands to
	 * @return whether the complete file was successfully parsed
	 */
	public boolean parse(BufferedReader inputStream, PaintModel paintModel) {
		this.paintModel = paintModel;
		this.errorMessage="";
		
		String type = "";
		
		int rr = (int)(Math.random()*256);
		int gg = (int)(Math.random()*256);
		int bb= (int)(Math.random()*256);
		Color color = Color.rgb(rr, gg, bb);
		boolean fill = false;
		
		Point centre = new Point(0,0);
		int radius = 0;
		
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,0);
		
		
		ArrayList<Point> points=new ArrayList<Point>();
		// During the parse, we will be building one of the 
		// following commands. As we parse the file, we modify 
		// the appropriate command.
		
		CircleCommand circleCommand = null; 
		RectangleCommand rectangleCommand = null;
		SquiggleCommand squiggleCommand = null;
	
		try {	
			int state=0; Matcher m; String l;
			
			this.lineNumber=0;
			while ((l = inputStream.readLine()) != null) {
				this.lineNumber++;
				System.out.println(lineNumber+" "+l+" "+state);
				switch(state){
					case 0:
						m=pFileStart.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						if(m.matches()){
							type = "Circle";
							state=2; 
							//System.out.println("hi");
							break;
						}
						m=pRectangleStart.matcher(l);
						if(m.matches()){
							type = "Rectangle";
							state=2; 
							break;
						}
						m=pSquiggleStart.matcher(l);
						if(m.matches()){
							type = "Squiggle";
							state=2; 
							break;
						}
						m=pFileEnd.matcher(l);
						if(m.matches()){
						
							//state=2; 
							break;
						}
						error("Expected Start of a new object or end of the Save File");
						// ADD CODE
				
					case 2:
						m=pColour.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							int r = (int)(sc.nextDouble());
							int g = (int)(sc.nextDouble());
							int b= (int)(sc.nextDouble());
							sc.close();
							color = Color.rgb(r, g, b);
							state=3; 
							break;
						}
						//if it comes out here there is an error
						error("Expected Colour");
						
					case 3:
						m=pFilled.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							fill = sc.nextBoolean();
							sc.close();
							state=4; 
							break;
						}
						error("Expected Filled True/False");
					case 4:
						m=pCentre.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							int x = (sc.nextInt());
							int y = (sc.nextInt());
							centre = new Point(x,y);
							state=5; 
							break;
						}
						m=pP1.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							int x = (sc.nextInt());
							int y = (sc.nextInt());
							p1 = new Point(x,y);
							state=7; 
							break;
						}
						m=pPoints.matcher(l);
						if(m.matches()){
							state=9; 
							break;
						}
						error("Expected circle centre, or rectangle P1, or squiggle points");
					case 5:
						m=pRadius.matcher(l);
						if(m.matches()){
							radius = Integer.parseInt(l.substring(10));
							
							state=6; 
							break;
						}
						error("Expected Circle Radius");
					case 6:
						m=pCircleEnd.matcher(l);
						if(m.matches()){
							circleCommand = new CircleCommand(centre, radius);
							circleCommand.setColor(color);
							circleCommand.setFill(fill);
							paintModel.addCommand(circleCommand);
							
							state=1; //go back to check for next shape or end of file
							break;
						}
						error("Expected Circle End");
					case 7:
						m=pP2.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							int x = (sc.nextInt());
							int y = (sc.nextInt());
							p2 = new Point(x,y);
							state=8; 
							break;
						}
						error("Expected Rectangle P2");
					case 8:
						m=pRectangleEnd.matcher(l);
						if(m.matches()){
							rectangleCommand = new RectangleCommand(p1, p2);
							rectangleCommand.setColor(color);
							rectangleCommand.setFill(fill);
							paintModel.addCommand(rectangleCommand);
							state=1; //go back to check for next shape or end of file
							break;
						}
						error("Expected Rectangle End");
					case 9:
						m=pPoint.matcher(l);
						if(m.matches()){
							Scanner sc = new Scanner(l);
							int x = (sc.nextInt());
							int y = (sc.nextInt());
							Point p = new Point(x,y);
							points.add(p);
							state=9; //cycle through all the points
							break;
						}
						//if we are here, we are probably at the end of the Squiggle so..
						m=pSquiggleEnd.matcher(l);
						if(m.matches()){
							for (Point po: points) {
								squiggleCommand.add(po);
							}
							paintModel.addCommand(squiggleCommand);
							state=1; //go back to check for next shape or end of file
							break;
						}
						error("Expected Squiggle point or Squiggle End");
						
					// ...
				}
			}
		}  catch (Exception e){
			
		}
		return true;
	}
}
