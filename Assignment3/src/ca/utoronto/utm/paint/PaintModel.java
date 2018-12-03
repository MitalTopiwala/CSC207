package ca.utoronto.utm.paint;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.GraphicsContext;

public class PaintModel extends Observable implements Observer {

	//double fileVersion = 1.0;
	public void save(PrintWriter writer) {
		writer.println("Paint Save File Version 1.0");
		for(PaintCommand c: this.commands){
			if (c instanceof CircleCommand) {
				//System.out.println("circle command");
				writer.println("Circle");
				writer.print(c.toString());
				writer.println("	center:"+((CircleCommand)c).getCentre());
				writer.println("	radius:"+((CircleCommand)c).getRadius());
				writer.println("End Circle");
				
			}else if (c instanceof RectangleCommand) {
				//System.out.println("rect command");
				writer.println("Rectangle");
				writer.print(c.toString());
				writer.println("	p1:"+((RectangleCommand)c).getP1());
				writer.println("	p2:"+((RectangleCommand)c).getP2());
				writer.println("End Rectangle");
			}else if (c instanceof SquiggleCommand) {
				//System.out.println("squiggle command");
				writer.println("Squiggle");
				writer.print(c.toString());
				writer.println("	points");
				for (Point p: ((SquiggleCommand)c).getPoints()) {
					writer.println("		point:"+p);
				}
				writer.println("	end points");
				writer.println("End Squiggle");
			}
		}
		writer.println("End Paint Save File");
		writer.close();
		//fileVersion += 1;
		
	}
	public void reset(){
		for(PaintCommand c: this.commands){
			c.deleteObserver(this);
		}
		this.commands.clear();
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addCommand(PaintCommand command){
		this.commands.add(command);
		command.addObserver(this);
		this.setChanged();
		this.notifyObservers();
	}
	
	private ArrayList<PaintCommand> commands = new ArrayList<PaintCommand>();

	public void executeAll(GraphicsContext g) {
		for(PaintCommand c: this.commands){
			c.execute(g);
		}
	}
	
	/**
	 * We Observe our model components, the PaintCommands
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.setChanged();
		this.notifyObservers();
	}
}
