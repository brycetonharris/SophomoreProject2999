package treestore;

public class TreeStore {	
	
	private String name;
	
	private String type;
	
	private int price;
	
	
	
	public TreeStore(String name, int price, String type) {
		
		this.name = name;
		
		this.type = type;
		
		this.price = price;
	}
	
	public String getName() {
		
		return name;
	}
	
    public String getType() {
		
		return type;
	}
	
	public int getPrice() {
		
		return price;
	}
	
}
