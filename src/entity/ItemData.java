package entity;

public class ItemData {
	// used to load and save from/to jsons
	String name;
	int x;
	int y;
	int width;
	int height;
	int id;
	
	
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