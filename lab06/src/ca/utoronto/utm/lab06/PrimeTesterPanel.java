package ca.utoronto.utm.lab06;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class PrimeTesterPanel extends FlowPane implements EventHandler<ActionEvent> {

	TextField tf;
	TextField tf2;

	public PrimeTesterPanel() {

		Button b;
		
		tf = new TextField();
		tf.setPrefColumnCount(10);
		this.getChildren().add(tf);
		
		b = new Button("Is Prime?");
		this.getChildren().add(b);
		b.setOnAction(this);

		//b = new Button("Down");
		//this.getChildren().add(b);
		//b.setOnAction(this);

		//b = new Button("Left");
		//this.getChildren().add(b);
		//b.setOnAction(this);

		//b = new Button("Right");
		//this.getChildren().add(b);
		//b.setOnAction(this);

		
		tf2 = new TextField();
		tf2.setPrefColumnCount(10);
		tf2.setDisable(true);
		this.getChildren().add(tf2);
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
