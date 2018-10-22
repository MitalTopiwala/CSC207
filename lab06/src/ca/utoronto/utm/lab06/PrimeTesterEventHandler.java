package ca.utoronto.utm.lab06;


import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PrimeTesterEventHandler implements EventHandler<ActionEvent> {

	private TextField tf;
	private TextField tf2;
	
	public PrimeTesterEventHandler(TextField txt, TextField txt2) {
		this.tf = txt;
		this.tf2 = txt2;//the non-editable  text field
	}

    
	
	public void handle(ActionEvent event) {

		String text = tf.getText();
		
		try {
			int n=Integer.parseInt(text);
			// n is a valid integer in here...
			if (isPrime(n)) {
				tf2.setText("yes");
			}else {
				tf2.setText("no");
			}
			
		} catch(NumberFormatException nfe){
			tf2.setText("invalid number");
		}
	
		return;
	}
	
	private boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n%i == 0) return false;
		}
		return true;
	}

}
