package ca.utoronto.utm.lab06;


import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class HiByeEventHandler implements EventHandler<ActionEvent> {

	private TextField tf;
	public HiByeEventHandler(TextField txt) {
		this.tf = txt;
	}

	// 1) Modify this so that it prints out which button was pressed.
	// Hint: Lookup ActionEvent
	// 2) Modify this so that it sets the text field to which button was pressed
	// Hint: this event handler will need access to a the text field
    
	
	public void handle(ActionEvent event) {

		Button source=(Button)event.getSource();
		
		System.out.println(source.getText() +" button pressed");
		
		tf.setText(source.getText());
		return;
	}
	
	
}
