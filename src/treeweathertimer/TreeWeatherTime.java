/*package treeweathertimer;

import java.util.Timer;
import java.util.TimerTask;

import treeclicker.Controller;
import treecutter.TreeCutter;


public class TreeWeatherTimer {
	public String WeatherState = "Clear";
	String weather;
	public TreeCutter tree = new TreeCutter(1, "Axe");
	
	public TreeWeatherTimer(Controller controller) {
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
	    
	       
	    TimerTask WeatherTime = new TimerTask() {
	
			@Override
			public void run() {
				if(WeatherState == "Sunny"){
					SunnyOff(); 
				}else if(WeatherState == "Snow") {
					SnowOff();
				}else if(WeatherState == "Rain"){
					rainOff();
				}
				
				
				int randomNumber = (int) (Math.random() * 4);
				if(Weather[randomNumber] == 1) {
					WeatherState = "Sunny";
					SunnyOn();
					controller.BackGroundChangeSunny();
				}else if(Weather[randomNumber] == 2){
					 WeatherState = "Clear";
					 controller.BackGroundChangeClear();
				}else if(Weather[randomNumber] == 3){
					 WeatherState = "Rain";
					 rainOn();
					 controller.BackGroundChangeRain();
					 
				}else {
					 WeatherState = "Snow";
					 SnowOn(); 
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

	public void  SnowOn() {
		tree.setDamage(tree.getDamage()/2);
		
	}
	public void  SnowOff() {
		tree.setDamage(tree.getDamage()*2);
		;
	}
	public void  rainOn() {
		tree.setDamage(tree.getDamage()/4);
		
	}
	public void  rainOff() {
		tree.setDamage(tree.getDamage()*4);
		
	}
	public void  SunnyOn() {
		tree.setDamage(tree.getDamage()*1.5);
		
	}
	public void  SunnyOff() {
		tree.setDamage(tree.getDamage()/1.5);
		
	}
}*/
	
