package entity;

public class EnemyData {
	// used to load and save from/to jsons
	public String name;
	public int x;
	public int y;
	public int width;
	public int height;
	public Enemy.states state;
	public int id;
	
	
	public EnemyData() {
		
	}
	public EnemyData(String name, int x, int y, int width, int height, Enemy.states state, int ID ) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.state = state;
		this.id = ID;
		this.name = name;
	}
}
