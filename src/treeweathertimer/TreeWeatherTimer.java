package treeweathertimer;
package treeweathertimer;

import java.util.Timer;
import java.util.TimerTask;
import treeclicker.Controller;



public class TreeWeatherTimer {
	public String WeatherState = "Clear";
	String weather;
	
	public TreeWeatherTimer(Controller controller) {
		//timer
	    Timer timer = new Timer();
	    TimerTask task = new TimerTask() {
	    	int Sec = 0;
	    	int Min = 0;
			@Override
			public void run() {
				if(Sec == 60) {
					Sec -= 60;
					++Min;
				}
				//Controller.getInstance().SetTimer(Min, Sec);
				//System.out.println(Min + ":" + Sec);
				Sec++;
			}
	    };
	    
	    int [] Weather = {1,2,3,4};
	    
	       
	    TimerTask WeatherTime = new TimerTask() {
	
			@Override
			public void run() {
				if(WeatherState == "Sunny"){
					WeatherOff(); 
				}else if(WeatherState == "Snow") {
					WeatherOff();
				}else if(WeatherState == "Rain"){
					WeatherOff();
				}
				
				
				int randomNumber = (int) (Math.random() * 4);
				if(Weather[randomNumber] == 1) {
					WeatherState = "Sunny";
					WeatherOn();
					controller.BackGroundChangeSunny();
				}else if(Weather[randomNumber] == 2){
					 WeatherState = "Clear";
					 controller.BackGroundChangeClear();
				}else if(Weather[randomNumber] == 3){
					 WeatherState = "Rain";
					 WeatherOn();
					 controller.BackGroundChangeRain();
					 
				}else {
					 WeatherState = "Snow";
					 WeatherOn(); 
					 controller.BackGroundChangeSnow();
				}
			}
	    	
	    };
	    //Timer for the Game
	    timer.scheduleAtFixedRate(task, 0, 1000);
	    //Timer event for the weather
	    //150000
	    timer.scheduleAtFixedRate(WeatherTime, 0, 150000);
	}
	public void  WeatherOff() {
		Controller.getInstance().undoPointsEarnedWeather(WeatherState);
	}
	public void  WeatherOn() {
		Controller.getInstance().setPointsEarnedWeather(WeatherState);
	}
}
