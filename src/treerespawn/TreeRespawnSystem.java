package treerespawn;
import java.lang.Math;

public class TreeRespawnSystem {
	
	// attributes
	private String full = "full";
	
	private String stump = "stump";	
	
	private String currentState;
	
	private int health;

	// constructor 
	public TreeRespawnSystem() {
		
		currentState = full;
		health = 7; // lucky trees!
	}
	
	public void hitTree() {
		
		if (currentState.equals(full)) {
			
            health--; // decrement health to 0.

            if (health <= 0) {
            	
                currentState = stump; // change from full to stump.
                
                System.out.println("Tree has been chopped down to a stump!");
                
            } else {
            	
                System.out.println("Tree is being cut... Health: " + health);
            }
        } 
        else {
        	
            System.out.println("Tree is already a stump.");
        }		
	}
	
	public void respawnTree() {
		
	    if (currentState.equals(stump)) {
	    	
	        System.out.println("Tree regrowing...");

	        try {
	        	
	            Thread.sleep(200); // Wait 0.2 seconds.
	            
	        } catch (InterruptedException e) {  // This happens if the thread is interrupted while sleeping.
	        	
	            e.printStackTrace(); // Print the stack trace for debugging.
	        }

	        if(Math.random()<0.1) {
	        	double rand = Math.random();
	        	if(rand<0.1) {
	        		currentState = "gold";
	        		System.out.println("A gold tree is growing!");
	        	} else if(rand<0.4) {
	        		currentState = "kauri";
	        		System.out.println("A kauri tree is growing!");
	        	} else {
	        		currentState = "cherry";
	        		System.out.println("A cherry tree is growing!");
	        	}
	        }
	        
	        System.out.println("The tree has regrown!");
	        
	    } 
	}
	
	public String getCurrentState() {
        return currentState;
    }
}
