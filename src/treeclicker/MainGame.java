package treeclicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import treeplayer.Player;

import java.net.URL;
/*
import java.util.Timer;
import java.util.TimerTask;
import weather.Weather;
*/
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
	          
	        URL imageURL = getClass().getResource("/resources/treeclicker logo.png");
	        
            if (imageURL != null) {
            	
                Image icon = new Image(imageURL.toExternalForm());
                primaryStage.getIcons().add(icon);  // Set the window icon
                
            } else {
            	
                System.out.println("Image not found!");
            }
	
		/* 
		 * 
		 * Temporarily commented out the game timer/weather logic from the main class 
		 * to prevent ongoing bugs. Suggest refactoring this into dedicated classes for
		 * better separation of concerns
		 * //timer 
	            Timer timer = new Timer();
		        TimerTask task = new TimerTask() {
		        	int Sec = 0;
		        	int Min = 0;
					@Override
					public void run() {
						//timeLabel.setText(counter + "Seconds");
						if(Sec == 60) {
							Sec -= 60;
							++Min;
						}
							System.out.println(Min + ":" + Sec);
							Sec++;
					}
		        };
		        
		        int [] Weather = {1,2,3,4};
		        Weather W = new Weather(WeatherState);
		           
		        TimerTask WeatherTime = new TimerTask() {

					@Override
					public void run() {
						if(WeatherState == "Sunny"){
							W.SunnyOff(); 
						}else if(WeatherState == "Snow") {
							W.SnowOff();
						}else if(WeatherState == "Rain"){
							W.rainOff();
						}
						
						
						int randomNumber = (int) (Math.random() * 4);
						if(Weather[randomNumber] == 1) {
							WeatherState = "Sunny";
							W.SunnyOn();
						}else if(Weather[randomNumber] == 2){
							 WeatherState = "Clear";
						}else if(Weather[randomNumber] == 3){
							 WeatherState = "Rain";
							 W.rainOn();
							controller.BackGroundChangeRain();
							 
						}else {
							 WeatherState = "Snow";
							 W.SnowOn(); 
							controller.BackGroundChangeSnow();
						}
					}
		        	
		        };
		        //Timer for the Game
		        timer.scheduleAtFixedRate(task, 0, 1000);
		        //Timer event for the weather
		        timer.scheduleAtFixedRate(WeatherTime, 0, 150000); */
		
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
