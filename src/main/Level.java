package main;

import entity.Enemy;
import entity.Item;
import tile.TileManager;

public class Level {
	String enemyData;
	String itemData;
	String tileData; // path to jsons that stores these 
	public Level northLevel;
	public Level southLevel;
	public Level eastLevel;
	public Level westLevel;
	
	Enemy[] enemies; // list of alive enemies left in this level. (will get reset after you have visited x amount of rooms
	Item[] items; // list of items uncollected in this level.
	      
	public Level(String enemyData, String itemData, String tileData) {
		this.enemyData = enemyData;
		this.itemData = itemData;
		this.tileData = tileData;
	}
	
	
	public void reloadEnemies(Enemy[] enemies) {
		this.enemies = enemies;
		
	}
	public void setLevelAdjacency(Level northLevel, Level southLevel, Level eastLevel, Level westLevel) {
		this.northLevel = northLevel;
		this.southLevel = southLevel;
		this.eastLevel = eastLevel;
		this.westLevel = westLevel;
	}
	public void loadLevel(TileManager tileManager) {
		// called when you first move into a level (will spawn in tiles, enemies items etc)
		tileManager.loadTileMap(tileData);
	}

}
