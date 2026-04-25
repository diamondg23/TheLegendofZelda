package rooms;

import java.awt.Graphics2D;
import java.util.LinkedList;

import controllers.EnemyManager;
import controllers.ItemManager;
import entity.Enemy;
import entity.Item;
import panels.GamePanel;
import rooms.LevelData.LevelType;
import tile.Tile;
import tile.TileManager;

// base class level, will have things that all levels have
public class Level {

    public static int numofLevels;
    public int ID;

    public LevelType levelType;

    public LinkedList<Level> roomsConnected = new LinkedList<>();
    public Level returnLevel;

    public TileManager tileManager;
    public EnemyManager enemyManager;
    public ItemManager itemManager;

    public Level(LevelType levelType) {
        this.levelType = levelType;
        this.ID = numofLevels++;
    }

    public void setReturnLevel(Level returnLevel) {
        this.returnLevel = returnLevel;
        returnLevel.roomsConnected.add(this);
    }

    public void update() {
        if (enemyManager != null) enemyManager.update();
        if (itemManager != null) itemManager.update();
    }

    public void draw(java.awt.Graphics2D g2) {
        if (enemyManager != null) enemyManager.draw(g2);
        if (itemManager != null) itemManager.draw(g2);
    }
}

