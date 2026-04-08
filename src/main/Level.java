package main;

import entity.Enemy;
import entity.Item;

public class Level {
	String enemyData;
	String itemData;
	String tileData; // path to jsons that stores these 
	String northLevel;
	String southLevel;
	String eastLevel;
	String westLevel;
	
	Enemy[] enemies; // list of alive enemies left in this level. (will get reset after you have visited x amount of rooms
	Item[] items; // list of items uncollected in this level.
	
	public Level(String enemyData, String itemData, String tileData) {
		this.enemyData = enemyData;
		this.itemData = itemData;
		this.tileData = tileData;
	}
	
	
	Enemy[] reloadEnemies() {
		// called to spawn in enemies after youve visited a certain amount of other rooms
		return null;
		
	}
	void loadLevel() {
		// called when you first move into a level (will spawn in tiles, enemies items etc)
	}
}
