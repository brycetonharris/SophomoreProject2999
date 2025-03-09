package treecutter;

public class TreeCutter {
	
	// attributes.
	private double damage = 1.0;
	
	private String cutter = "Axe";	
	
	public TreeCutter(double damage, String cutter) {
		
		this.damage = damage;
		
		this.cutter = cutter;
		
	}
	
	public TreeCutter(String cutter) {
		
		this.damage = 1.0;
		
		this.cutter = "Axe";
	}
	
	public double getDamage() {
		
		return damage;
		
	}
	
	public void setDamage(double damage) {
		
		this.damage = damage;
	}
	
	public String getCutter() {
		
		return cutter;
	}
	
	public void setCutter(String cutter) {
		
		this.cutter = cutter;
	}
	
	/* players can upgrade their cutting tool.
	public void upgradeCutter() {
		
		// upgrade axe to chainsaw.
		if(points >= 10000 && cutter.equals("Axe")) {
			this.cutter = "Chainsaw";
			
			this.damage = 1.25;
			
			points -= 10000;
			
			System.out.println("Upgraded to Chainsaw! Remaining points: " + points);
			
		} else if(points >= 50000 && cutter.equals("Chainsaw")) { // upgrade chainsaw to laser.
			this.cutter = "Laser";
			
			this.damage = 1.50;
						
			points -= 50000;
			
			System.out.println("Upgraded to Laser! Remaining points: " + points);
			
		} else {
			
			System.out.println("Not enough points to upgrade or already upgraded to highest tool");
		}
		
	} */
	
}
