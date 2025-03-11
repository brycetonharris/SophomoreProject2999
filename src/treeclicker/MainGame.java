package treeclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import treeplayer.Player;
import java.net.URL;

public class MainGame extends Application{	
		
	 public void start(Stage primaryStage) {    	
	    	
	        try {
	        // Load the FXML file
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/game.fxml"));
	        
	        Parent root = loader.load();               

	        Scene scene = new Scene(root, 800, 600);
	        root.setStyle("-fx-background-color: aquamarine;"); 	        
	        
	        Controller controller = loader.getController();
	        controller.onSceneReady(scene);

		/*TreeWeatherTimer time = new TreeWeatherTimer(controller);*/
	        
	          
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
	 
    @Override
	public void stop() throws Exception {
	        // Stop the timer when the application is closing.
	        Player.getInstance().stopLumberjackTimer();
	        System.out.println("Application is stopping. Timer stopped.");
	        super.stop();
	    }

	public static void main(String[] args) {
		launch(args);
		

	}
	

}
