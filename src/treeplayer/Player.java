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
    private Timer energydrinkTimer;	
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
	
	public void addEnergydrink() {
		
		if (isEnergydrinkActive()) {
			
			System.out.println("Energy drink is already active! Wait until it wears off");
			return;
		}
		
		energyDrinkCount = 1;
		System.out.println("Energy drink power-up activated for 10 seconds.");
		
		energydrinkTimer = new Timer();
		energydrinkTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					energyDrinkCount = 0;
					System.out.println("Energy drink effect ended.");
					energydrinkTimer.cancel();
					energydrinkTimer = null;					
				});
			}
		}, 10000); //10 seconds
		
	}
	
    public boolean isEnergydrinkActive() {
		
        return energyDrinkCount > 0;
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
	    // earn points method
	public void earnPoints(double pointsEarned) {
		
		if (isEnergydrinkActive()) {
			pointsEarned *= 1.5;
		}
			
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
