package rooms;

import java.util.LinkedList;

import entity.Enemy;
import entity.Item;
import panels.GamePanel;
import tile.Tile;
import tile.TileManager;

// base class level, will have things that all levels have
public class Level {
	String enemyData;
	String itemData;
	String tileData; // path to jsons that stores these 
	public static int numofLevels;
	public int ID;

	Tile.tileRooms roomType;
	public LinkedList<Level> roomsConnected = new LinkedList<Level>();
	public Level returnLevel; // if its a room itll be the room it is connected to (can be used for logic reasons)
	
	Enemy[] enemies; // list of alive enemies left in this level. (will get reset after you have visited x amount of rooms
	Item[] items; // list of items uncollected in this level.
	      
	public Level(String enemyData, String itemData, String tileData, Tile.tileRooms roomType) {
		this.enemyData = enemyData;
		this.itemData = itemData;
		this.tileData = tileData;
		this.roomType = roomType;
		this.ID = numofLevels;
		numofLevels++;
	}
	
	
	public void reloadEnemies(Enemy[] enemies) {
		this.enemies = enemies;
		
	}

	public void setReturnLevel(Level returnLevel) {
		this.returnLevel = returnLevel;
		returnLevel.roomsConnected.add(this);
	}
	public void loadLevel(TileManager tileManager) {
		// called when you first move into a level (will spawn in tiles, enemies items etc)
		tileManager.loadTileMap(tileData);
	}




}
