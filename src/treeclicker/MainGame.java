package treeclicker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class MainGame extends Application{
	
	 public void start(Stage primaryStage) {    	
	    	
	        try {
	        //Load the FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
	        
	        Button btn = new Button();
	        btn.setText("'Chop Tree Button'");
	        btn.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	            	System.out.println("whatever function goes after this");
	            }});
	        
	        Parent root = loader.load();               

	        Scene scene = new Scene(root, 300, 250);
	        root.setStyle("-fx-background-color: aquamarine;");
	        primaryStage.setTitle("JavaFX Game");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        } catch (Exception e) {
	        	e.printStackTrace(); //Handle any loading errors
	        }
	    }

	public static void main(String[] args) {
		launch(args);
		

	}
	
	public void handleButtonClick() {
		
		System.out.println("Button clicked");
	}

}
