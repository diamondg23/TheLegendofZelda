package main;

import java.util.LinkedList;

import entity.Enemy;
import entity.Item;
import panels.GamePanel;
import tile.Tile;
import tile.TileManager;

public class Level {
	String enemyData;
	String itemData;
	String tileData; // path to jsons that stores these 
	public static int numofLevels;
	public int ID;
	public Level northLevel;
	public Level southLevel;
	public Level eastLevel;
	public Level westLevel;
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
	public void setLevelAdjacency(Level northLevel, Level southLevel, Level eastLevel, Level westLevel) {
		this.northLevel = northLevel;
		this.southLevel = southLevel;
		this.eastLevel = eastLevel;
		this.westLevel = westLevel;
	}
	public void setReturnLevel(Level returnLevel) {
		this.returnLevel = returnLevel;
		returnLevel.roomsConnected.add(this);
	}
	public void loadLevel(TileManager tileManager) {
		// called when you first move into a level (will spawn in tiles, enemies items etc)
		tileManager.loadTileMap(tileData);
	}
	public static Level[][] generateDungeonOneMap(GamePanel panel){
		Level[][] levels = new Level[5][5];
		return levels;
	}
	public static Level[][] generateOverworldLevelMap(GamePanel panel){
		Level[][] levels = new Level[8][16];
		Level currLevel = new Level( null, null ,"/maps/Starting_level.json", Tile.tileRooms.NOROOM);
		levels[7][8] = currLevel;
		currLevel = new Level(null,null, "/maps/North_level.json", Tile.tileRooms.NOROOM);
		levels[6][8] = currLevel;
		currLevel = new Level(null,null, "/maps/East_level.json", Tile.tileRooms.NOROOM);
		levels[7][9] = currLevel;
		currLevel = new Level(null,null, "/maps/Startinglevel2.json", Tile.tileRooms.NOROOM);
		levels[7][7] = currLevel;
		levels = assignAdjacencies(levels);
		return levels;
	}
	private static Level[][] assignAdjacencies(Level[][] levels) {
	    for(int i = 0; i < levels.length; i++) {
	        for(int j = 0; j < levels[i].length; j++) {

	            if(levels[i][j] != null) {

	                Level north = null;
	                Level south = null;
	                Level east = null;
	                Level west = null;

	                if(i > 0 && levels[i-1][j] != null) {
	                    north = levels[i-1][j];
	                }

	                if(i < levels.length - 1 && levels[i+1][j] != null) {
	                    south = levels[i+1][j];
	                }

	                if(j > 0 && levels[i][j-1] != null) {
	                    west = levels[i][j-1];
	                }

	                if(j < levels[i].length - 1 && levels[i][j+1] != null) {
	                    east = levels[i][j+1];
	                }

	                levels[i][j].setLevelAdjacency(north, south, east, west);
	            }
	        }
	    }

	    return levels;
	}

}
