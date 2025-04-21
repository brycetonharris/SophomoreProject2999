package treeachievements;

public class achievements {
	private double wood;
	private double luckyclovers;
	private double energydrinks;
	private double ljack;
	private double wood1test;
	private double luckycloverstest;
	private double energydrinkstest;
	private double ljacktest;
	private double w1t;
	private double luckyclovers2t;
	private double energydrinks3t;
	private double ljt;
	
	
	public achievements(double wood1, int luckyclovers, int ljack, int energydrinks){
		this.wood = wood1 +.01;
		this.luckyclovers = luckyclovers+.01;
		this.energydrinks = energydrinks+.01;
		this.ljack = ljack+.01;

	}
	
	
	public void checkAchievements() {
		int i=1;
		wood1test = 1;
		luckycloverstest = 1;
		energydrinkstest = 1;
		ljacktest = 1;
		
		w1t = wood/(10);
		luckyclovers2t = luckyclovers/(10);
		energydrinks = energydrinks/(10);
		ljt = ljack/(10);

		while (i < 10) {
			
			if (wood >= (Math.pow(10,i))) {wood1test++; w1t = wood/(Math.pow(10, (i+1)));}
			if (luckyclovers >= (Math.pow(10,i))) {luckycloverstest++; luckyclovers2t = luckyclovers/(Math.pow(10, (i+1)));}
			if (energydrinks >= (Math.pow(10,i))) {energydrinkstest++; energydrinks3t = energydrinks/(Math.pow(10, (i+1)));}
			if (ljack >= (Math.pow(10,i))) {ljacktest++; ljt = (ljack)/Math.pow(10, (i+1));}
			
				i++;
		}
		//System.out.println(wood1 + " total all time");
		//System.out.println(w1t + " progress");
		//System.out.println(Math.pow(10, wood1test) + " num");
		
	}
	
	public double progressBarW1() {
		return(w1t);
	}
	
	public double progressBarW2() {
		return(luckyclovers2t);
	}
	
	public double progressBarW3() {
		return(energydrinks3t);
	}
	
	public double progressBarLJ() {
		return(ljt);
	}
	
	public double NumW1() {
		
		return(Math.pow(10, wood1test));
	}
	
	public double NumW2() {
		return(Math.pow(10, luckycloverstest));
	}
	
	public double NumW3() {
		return(Math.pow(10, energydrinkstest));
	}
	
	public double NumLJ() {
		
		return(Math.pow(10, ljacktest));
	}
	
	public boolean updatewood(double d) {
		
		boolean updt = false;
		
		wood = d;
		
		double temp;
		temp = wood1test;
		
		int i=1;
		wood1test = 0;

		while (i < 10) {
			
			if (wood >= (Math.pow(10,i))) {wood1test++;}
			
				i++;
		}
		
		if (temp < wood1test) {
			updt = true;
		}
		
		
		return(updt);
	}
	
	public boolean updateEnergyDrinks(double d) {
	    boolean updt = false;
	    energydrinks = d;
	    
	    double temp = energydrinkstest;
	    
	    int i = 1;
	    energydrinkstest = 0;

	    while (i < 10) {
	        if (energydrinks >= (Math.pow(10, i))) {
	            energydrinkstest++;
	        }
	        i++;
	    }

	    if (temp < energydrinkstest) {
	        updt = true;
	    }

	    return updt;
	}

	public boolean updateLuckyClovers(double d) {
	    boolean updt = false;
	    luckyclovers = d;

	    double temp = luckycloverstest;

	    int i = 1;
	    luckycloverstest = 0;

	    while (i < 10) {
	        if (luckyclovers >= (Math.pow(10, i))) {
	            luckycloverstest++;
	        }
	        i++;
	    }

	    if (temp < luckycloverstest) {
	        updt = true;
	    }

	    return updt;
	}

	public boolean updateLumberjacks(double d) {
	    boolean updt = false;
	    ljack = d;

	    double temp = ljacktest;

	    int i = 1;
	    ljacktest = 0;

	    while (i < 10) {
	        if (ljack >= (i * 10)) {
	            ljacktest++;
	        }
	        i++;
	    }

	    if (temp < ljacktest) {
	        updt = true;
	    }

	    return updt;
	}

	public String achievementBoxWood() {
		
		return("congrats on getting " + Math.pow(10,wood1test) + " wood");
	}
	public String achievementBoxEnergyDrink() {
		
		return("congrats on using " + Math.pow(10,energydrinkstest) + " energy drinks");

	}
	public String achievementBox() {
	
		return("congrats on getting " + Math.pow(10,luckycloverstest) + " lucky clovers");

}
	public String achievementBoxLJ() {
		
		return("congrats on getting " + Math.pow(10,ljacktest) + " lumberjacks");

	}
	


	
}

