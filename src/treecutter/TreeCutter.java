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
}
