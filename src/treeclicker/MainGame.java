package treeclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGame extends Application{
	
	 public void start(Stage primaryStage) {    	
	    	
	        try {
	        //Load the FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
	        Parent root = loader.load();               

	        Scene scene = new Scene(root, 300, 250);
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
