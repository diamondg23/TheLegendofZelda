package rooms;

import panels.GamePanel;
import rooms.LevelData.LevelType;
import tile.Tile;
import tile.Tile.tileRooms;
// this class will handle any level that is in the overworld
// this may have unique behavior not associated with other level types


import tile.Tile;

public class OverWorldLevel extends Level {



    public int worldX;
    public int worldY;

    public OverWorldLevel(int id, String name , String tilePath, String enemyPath, String itemPath, String specialPath , int x, int y) {
        super(LevelType.OVERWORLD, id, name , tilePath,enemyPath,itemPath,specialPath);
        this.worldX = x;
        this.worldY = y;
    }

 
}

