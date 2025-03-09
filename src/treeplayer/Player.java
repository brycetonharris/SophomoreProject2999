package treeplayer;

import treecutter.TreeCutter;

public class Player {
	
	private static Player instance;
	
	private int points = 0;
	
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
	
	    // earn points method
	public void earnPoints(int pointsEarned) {
			
			points += pointsEarned;
			
			System.out.println("Earned points: " + pointsEarned + " points. Total points: " + points);			
			
	}
		
	public int getPoints() {
			
		    return points;
	}
		
	public TreeCutter getTreeCutter() {
			
			return treecutter;
	}

}
