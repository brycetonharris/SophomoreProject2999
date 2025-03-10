package treeplayer;

import treecutter.TreeCutter;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import treeclicker.Controller;


public class Player {
	
	private static Player instance;
	
	private double points = 0.0;
	private double totalPoints = 0.0;
	private int luckyCloverCount = 0;
    private int autoLJackCount = 0;
    private int energyDrinkCount = 0;
    private Timer lumberjackTimer;
	
	private TreeCutter treecutter;
	
	private Player() {
		
		this.treecutter = new TreeCutter(points, "Axe");
	}
	
	public static Player getInstance() {
		
		if (instance == null) {
			
			instance = new Player();
		}
		
		return instance;
	}
	
	public void addLumberjack() {
	    autoLJackCount++;
	    System.out.println("Lumberjack power-up added! Total Lumberjacks: " + autoLJackCount);

	    treecutter.setDamage(autoLJackCount * 0.1);  // Stack damage by 0.1 for each Lumberjack
	    if (lumberjackTimer == null) {
	        lumberjackTimer = new Timer();
	        lumberjackTimer.scheduleAtFixedRate(new TimerTask() {
	            @Override
	            public void run() {
	                // Earn points based on the current lumberjack count
	                earnPoints(autoLJackCount * 0.1);
	                
	                // Update the UI on the JavaFX Application Thread
	                Platform.runLater(() -> {
	                	Controller currentController = Controller.getInstance();
	                    if (currentController != null) {
	                        currentController.updatePointsDisplay();
	                    } else {
	                        System.out.println("Controller instance not available!");
	                    }
	                });
	            }
	        }, 0, 3000);  // Add points every 3 seconds
	    }
	}

	
	public boolean isLumberjackActive() {
		
        return autoLJackCount > 0;
    }
	
	public void stopLumberjackTimer() {
		
	    if (lumberjackTimer != null) {
	    	
	        lumberjackTimer.cancel();
	        lumberjackTimer = null;
	    }
	}
	
	    
	public int getLuckyCloverCount() {
		
		return luckyCloverCount;
	}
	
	public void addLuckyClover() {
		
		luckyCloverCount++;
	}
	
	public int getAutoLJackCount() {
		
		return autoLJackCount;
	}
	
	public void addAutoLJackCount() {
		
		autoLJackCount++;
	}
	
	public int getEnergyDrinkCount() {
		
		return energyDrinkCount;
	}
	
	public void addEnergyDrinkCount() {
		
		energyDrinkCount++;
	}
	    // earn points method
	public void earnPoints(double pointsEarned) {
			
			points += pointsEarned;
			totalPoints += pointsEarned;
			
			System.out.println("Earned wood: " + pointsEarned + " points. Total wood: " + points);			
			
	}
	
	public double getTotalPoints() {
		
	    return totalPoints;
}
		
	public double getPoints() {
			
		    return points;
	}
		
	public TreeCutter getTreeCutter() {
			
			return treecutter;
	}

}
