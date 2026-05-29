package entity;

public class ItemData {
	// used to load and save from/to jsons
	public String name;
	public int x;
	public int y;
	public int width;
	public int height;
	public int id;
	
	
	public ItemData() {
		
	}
	public ItemData(String name, int x, int y, int width, int height, int ID ) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.id = ID;
		this.name = name;
	}
}