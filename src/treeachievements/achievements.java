package treeAchievements;

public class achievements {
	private double wood1;
	private double wood2;
	private double wood3;
	private double ljack;
	private double wood1test;
	private double wood2test;
	private double wood3test;
	private double ljacktest;
	private double w1t;
	private double w2t;
	private double w3t;
	private double ljt;
	
	
	public achievements(double wood1, int ljack){
		this.wood1 = wood1;
		this.wood2 = wood2;
		this.wood3 = wood3;
		this.ljack = ljack;

	}
	
	
	public void checkAchievements() {
		int i=1;
		wood1test = 1;
		wood2test = 1;
		wood3test = 1;
		ljacktest = 1;
		
		w1t = wood1/(10);
		w2t = wood2/(10);
		w3t = wood3/(10);
		ljt = ljack/(10);

		while (i < 10) {
			
			if (wood1 >= (Math.pow(10,i))) {wood1test++; w1t = wood1/(Math.pow(10, (i+1)));}
			if (wood2 >= (Math.pow(10,i))) {wood2test++; w2t = wood2/(Math.pow(10, (i+1)));}
			if (wood3 >= (Math.pow(10,i))) {wood3test++; w3t = wood3/(Math.pow(10, (i+1)));}
			if (ljack >= (i*10)) {ljacktest++; ljt = (ljack)/(10*i);}
			
				i++;
		}
		System.out.println(wood1 + " total all time");
		System.out.println(w1t + " progress");
		System.out.println(Math.pow(10, wood1test) + " num");
		
	}
	
	public double progressBarW1() {
		return(w1t);
	}
	
	public double progressBarW2() {
		return(w2t);
	}
	
	public double progressBarW3() {
		return(w3t);
	}
	
	public double progressBarLJ() {
		System.out.println(ljt);
		return(ljt);
	}
	
	public double NumW1() {
		
		return(Math.pow(10, wood1test));
	}
	
	public double NumW2() {
		return(Math.pow(10, wood2test));
	}
	
	public double NumW3() {
		return(Math.pow(10, wood3test));
	}
	
	public double NumLJ() {
		System.out.println(ljacktest*10);
		return(ljacktest*10);
	}
	


	
}

