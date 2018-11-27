package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class SaveFactory {
	public static String save(String strategyName, PaintCommand command){
		String text;
		if(strategyName=="Circle"){
			text = CircleSaveStrategy(command);
		} else if(strategyName=="Squiggle"){
			text = SquiggleSaveStrategy(command);
		} else if(strategyName=="Rectangle"){
			text = RectangleSaveStrategy(command);
		}
		return text;
	}

}
