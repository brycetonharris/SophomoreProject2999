package treeweathertimer; 


import java.util.Timer;
import java.util.TimerTask;

/*
public class TreeWeatherTime {
	
	 //timer 
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
						 
					}else {
						 WeatherState = "Snow";
						 W.SnowOn(); 
					}
				}
	        	
	        };
	        //Timer for the Game
	        timer.scheduleAtFixedRate(task, 0, 1000);
	        //Timer event for the weather
	        timer.scheduleAtFixedRate(WeatherTime, 0, 150000); 

} */
