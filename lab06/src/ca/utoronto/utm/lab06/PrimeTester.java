package ca.utoronto.utm.lab06;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PrimeTester extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		guiElements(stage);
	}
	
	private void guiElements(Stage stage) {
		// Create a layout pane that contains the elements
		HBox pane = new HBox(5);
		pane.setPadding(new Insets(10));

		// Create the button
		Button is_prime = new Button("Is Prime?");


		// Create a text field
		TextField txt = new TextField();
		txt.setPrefColumnCount(20);
		
		// Create a non-editable text field
		TextField txt2 = new TextField();
		txt2.setPrefColumnCount(20);
		txt2.setDisable(true);
		
		// Put the text field on the pane
		pane.getChildren().add(txt);
		// Put the button on the pane
		pane.getChildren().add(is_prime);
		// Put the uneditable text field on the pane
		pane.getChildren().add(txt2);
		
		// Hook up the event handler that defines 
		// what to do when the button is clicked.
		is_prime.setOnAction(new PrimeTesterEventHandler(txt, txt2));
		
		// Use pane as the root of the scene
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		
		// Show the window
		stage.show();	
		
	}//guiElements

}//PrimeTester

