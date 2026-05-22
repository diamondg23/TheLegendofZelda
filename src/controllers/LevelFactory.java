package controllers;

import panels.GamePanel;
import rooms.Level;
import rooms.LevelData;
import rooms.OverWorldLevel;
import tile.TileManager;
import rooms.Cave;
import rooms.DungeonLevel;

public class LevelFactory {

    public static Level create(LevelData data, GamePanel gp) {

        Level level;

        switch (data.type) {

            case OVERWORLD:
                level = new OverWorldLevel();
                break;

            case CAVE:
                level = new Cave(data.type);
                break;


            default:
                level = new Level(data.type);
        }

        
        level.tileManager = new TileManager(gp);
        level.enemyManager = new EnemyManager();
        level.itemManager = new ItemManager();

        // load data
        level.tileManager.loadTileMap(data.tileData);
        level.enemyManager.load(data.enemyData);
        level.itemManager.load(data.itemData);

        return level;
    }
}