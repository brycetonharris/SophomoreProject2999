package treeclicker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.net.URL;

import treeclicker.Controller;

public class MainGame extends Application{	
		
	 public void start(Stage primaryStage) {    	
	    	
	        try {
	        // Load the FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
	        
	        Parent root = loader.load();               

	        Scene scene = new Scene(root, 600, 600);
	        root.setStyle("-fx-background-color: aquamarine;"); 
	        
	        
	        Controller controller = loader.getController();
	        controller.onSceneReady(scene);
	          
	        URL imageURL = getClass().getResource("/resources/treeclicker logo.png");
	        
            if (imageURL != null) {
            	
                Image icon = new Image(imageURL.toExternalForm());
                primaryStage.getIcons().add(icon);  // Set the window icon
                
            } else {
            	
                System.out.println("Image not found!");
            }
            
            //primaryStage.setFullScreen(true);

	        primaryStage.setTitle("TreeClicker");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	        
	        } catch (Exception e) {
	        	e.printStackTrace(); // Handle any loading errors
	        }
	    }

	public static void main(String[] args) {
		launch(args);
		

	}
	

}
