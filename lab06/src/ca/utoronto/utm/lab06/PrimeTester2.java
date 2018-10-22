package ca.utoronto.utm.lab06;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PrimeTester2 extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		VBox pane = new VBox(5);
		pane.setPadding(new Insets(5));

		pane.getChildren().add(new PrimeTesterPanel());
		pane.getChildren().add(new PrimeTesterPanel());
		pane.getChildren().add(new PrimeTesterPanel());
		
		// Create a text field
		TextField txt = new TextField();
		txt.setPrefColumnCount(20);
				
		// Create a non-editable text field
		TextField txt2 = new TextField();
		txt2.setPrefColumnCount(20);
		txt2.setDisable(true);
	

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		//stage.setTitle("Button Panels");
		stage.show();
	}
}
