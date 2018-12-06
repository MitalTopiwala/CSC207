package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
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
	private Pattern pFileStart=Pattern.compile("^( *)P( *)a( *)i( *)n( *)t( *)S( *)a( *)v( *)e( *)F( *)i( *)l( *)e( *)V( *)e( *)r( *)s( *)i( *)o( *)n( *)1( *)\\.( *)0(\\s*)$");
	private Pattern pFileEnd=Pattern.compile("^( *)E( *)n( *)d( *)P( *)a( *)i( *)n( *)t( *)S( *)a( *)v( *)e( *)F( *)i( *)l( *)e(\\s*)$");

	private Pattern pCircleStart=Pattern.compile("^( *)C( *)i( *)r( *)c( *)l( *)e( *)$");
	private Pattern pCircleEnd=Pattern.compile("^( *)E( *)n( *)d( *)C( *)i( *)r( *)c( *)l( *)e( *)$");
	// ADD MORE!!
	private Pattern pRectangleStart=Pattern.compile("^( *)R( *)e( *)c( *)t( *)a( *)n( *)g( *)l( *)e( *)$");
	private Pattern pRectangleEnd=Pattern.compile("^( *)E( *)n( *)d( *)R( *)e( *)c( *)t( *)a( *)n( *)g( *)l( *)e( *)$");
	private Pattern pSquiggleStart=Pattern.compile("^( *)S( *)q( *)u( *)i( *)g( *)g( *)l( *)e( *)$");
	private Pattern pSquiggleEnd=Pattern.compile("^( *)E( *)n( *)d( *)S( *)q( *)u( *)i( *)g( *)g( *)l( *)e( *)$");
	
	//For all 3
	//there is something wrong with the color regex
	private Pattern pColour=Pattern.compile("^(\\s*)color:(\\d+),(\\d+),(\\d+)$");
	private Pattern pFilled=Pattern.compile("^(\\s*)filled:(true|false)$");
	
	//Circle 
	private Pattern pCentre=Pattern.compile("^(\\s*)center:\\((\\d+),(\\d+)\\)$");
	private Pattern pRadius=Pattern.compile("^(\\s*)radius:(\\d+)$");
	
	//Rectangle
	private Pattern pP1=Pattern.compile("^(\\s*)p1:\\((\\d+),(\\d+)\\)$");
	private Pattern pP2=Pattern.compile("^(\\s*)p2:\\((\\d+),(\\d+)\\)$");
	
	//Squiggle
	private Pattern pPoints=Pattern.compile("^(\\s*)points$");
	private Pattern pPointsEnd=Pattern.compile("^(\\s*)end points$");
	private Pattern pPoint=Pattern.compile("^(\\s*)point:\\((\\d+),(\\d+)\\)$");//for each point
	
	//Finding a double value
	private Pattern pWhiteSpace=Pattern.compile("\\s*");
	
	
	
	/**
	 * Store an appropriate error message in this, including 
	 * lineNumber where the error occurred.
	 * @param mesg
	 */
	private void error(String mesg){
		this.errorMessage = "Error in line "+lineNumber+" "+mesg;
		JOptionPane.showMessageDialog(null, this.errorMessage);
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
						m=pWhiteSpace.matcher(l);
						if(m.matches()){
							state=0;
							break;
						}
						error("Expected Start of Paint Save File");
						return false;
					case 1: // Looking for the start of a new object or end of the save file
						m=pCircleStart.matcher(l);
						if(m.matches()){
							state=2; 
							//System.out.println("hi");
							break;
						}
						
						m=pRectangleStart.matcher(l);
						if(m.matches()){
							state=2; 
							break;
						}
						m=pSquiggleStart.matcher(l);
						if(m.matches()){
							state=2; 
							break;
						}
						m=pFileEnd.matcher(l);
						if(m.matches()){
							state=11;
							break;
						}
						
						m=pWhiteSpace.matcher(l);
						if(m.matches()){
							state=1;
							break;
						}
						error("Expected Start of a new object or end of the Save File");
						
						throw new Exception();
				
					case 2:
						//System.out.println("hi color out");
						
						m=pColour.matcher(l);
						if(m.matches()){
							
							//System.out.println("hi colour");
							
							//make sure each color value in between 0-255
							
						
							//System.out.println("hi colour2");
							int r = Integer.parseInt(m.group(2));
							//System.out.println("hi colour3");
							int g = Integer.parseInt(m.group(3));
							//System.out.println("hi colour4");
							int b = Integer.parseInt(m.group(4));
							//System.out.println("hi colour5");
						
							if ((r|g|b) >255 || (r|g|b) <0 ) {
								error("Expected Colour between 0-255");
								return false;
							}
							color = Color.rgb(r, g, b);
							//System.out.println(color);
							state=3;
							
							break;
						}
						//if it comes out here there is an error
						error("Expected Colour between 0-255");
						
						return false;
					case 3:
						m=pFilled.matcher(l);
						//System.out.println("hi fill out");
						if(m.matches()){
							//System.out.println("hi fill");
						
							fill = Boolean.parseBoolean(m.group(2));
						
							state=4; 
							break;
						}
						error("Expected Filled True/False");
						//System.out.println(errorMessage);
						return false;
					case 4:
						m=pCentre.matcher(l);
						if(m.matches()){
							//System.out.println("hi centre");
							//Scanner sc = new Scanner(l);
							int x = Integer.parseInt(m.group(2));
							int y = Integer.parseInt(m.group(3));
							centre = new Point(x,y);
							//sc.close();
							state=5; 
							break;
						}
						m=pP1.matcher(l);
						if(m.matches()){
							//Scanner sc = new Scanner(l);
							int x = Integer.parseInt(m.group(2));
							int y = Integer.parseInt(m.group(3));
							p1 = new Point(x,y);
							//sc.close();
							state=7; 
							break;
						}
						m=pPoints.matcher(l);
						if(m.matches()){
							state=9; 
							break;
						}
						error("Expected circle centre, or rectangle P1, or squiggle points");
						//System.out.println(errorMessage);
						//throw new Exception();
						return false;
					case 5:
						m=pRadius.matcher(l);
						if(m.matches()){
							
							radius = Integer.parseInt(m.group(2));
							//sc.close();
							state=6; 
							break;
						}
						error("Expected Circle Radius");
						
						return false;
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
						
						return false;
					case 7:
						m=pP2.matcher(l);
						if(m.matches()){
							//Scanner sc = new Scanner(l);
							int x = Integer.parseInt(m.group(2));
							int y = Integer.parseInt(m.group(3));
							p2 = new Point(x,y);
							state=8; 
							break;
						}
						error("Expected Rectangle p2");
						
						return false;
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
						
						return false;
					case 9:
						m=pPoint.matcher(l);
						if(m.matches()){
							
							int x = Integer.parseInt(m.group(2));
							int y = Integer.parseInt(m.group(3));
							Point p = new Point(x,y);
							points.add(p);
							state=9; //cycle through all the points
							break;
						}
						//if we are here, we are probably at the end of the points so..
						m=pPointsEnd.matcher(l);
						if(m.matches()){
							state=10; 
							break;
						}
					case 10:
						m=pSquiggleEnd.matcher(l);
						if(m.matches()){
							squiggleCommand = new SquiggleCommand();
							for (Point po: points) {
								squiggleCommand.add(po);
							}
							paintModel.addCommand(squiggleCommand);
							state=1; //go back to check for next shape or end of file
							break;
						}
						error("Expected Squiggle point or Squiggle End");
						
						return false;
					case 11:
						error("Cannot have lines after End Paint Save File");
						return false;
						
						
					// ...
				}
			}
		}  catch (Exception e){
			//if (!errorMessage.equals("")) {
				//JOptionPane.showMessageDialog(null, errorMessage);
				
			//}
			
		}
		return true;
	}
}
