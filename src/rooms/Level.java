package rooms;

import java.awt.Graphics2D;
import java.util.LinkedList;

import controllers.EnemyManager;
import controllers.ItemManager;
import controllers.TileManager;
import entity.Enemy;
import entity.Item;
import panels.GamePanel;
import rooms.LevelData.LevelType;
import tile.Tile;

// base class level, will have things that all levels have
public class Level {

   
    public int ID;
    public String name;

    public LevelType levelType;

  

    public TileManager tileManager;
    public EnemyManager enemyManager;
    public ItemManager itemManager;
    
	public String tileData; // file path to the tile json
	public String enemyData; // file path to the enemy json
	public String itemData; // file path to the item json
	public String specialData; // file path to the special behavior json (not implemented yet)
	
    public Level north;
    public Level south;
    public Level east;
    public Level west;

    public Level(LevelType levelType, int ID, String name , String tilePath, String enemyPath, String itemPath, String specialPath) {
        this.levelType = levelType;
        this.ID = ID;
        this.name = name;
        this.tileData = tilePath;
        this.enemyData = enemyPath;
        this.itemData = itemPath;
        this.specialData = specialPath;
    }
    public void load() {
    	if (tileManager != null) tileManager.load(tileData);
    	  if (enemyManager != null) enemyManager.load(enemyData);
          if (itemManager != null) itemManager.load(itemData);
    }

 
    public void update() {
        if (enemyManager != null) enemyManager.update();
        if (itemManager != null) itemManager.update();
    }

    public void draw(java.awt.Graphics2D g2) {
    	if(tileManager != null) tileManager.draw(g2);
        if (enemyManager != null) enemyManager.draw(g2);
        if (itemManager != null) itemManager.draw(g2);
    }
    public void setAdjacency(
            Level north,
            Level south,
            Level east,
            Level west
    ) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
}

