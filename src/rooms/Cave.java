package rooms;

import rooms.LevelData.LevelType;
import tile.Tile;
import tile.Tile.tileRooms;
// This class will handle the one off rooms that are scattered around the overworld
// technically not part of the overworld so it will have some unique behaviors associated with it.


public class Cave extends Level {

    public String text; // used to display text to the screen (if it exists)
   

    public Cave(int id, String name , String tilePath, String enemyPath, String itemPath, String specialPath) {
        super(LevelType.CAVE, id, name , tilePath,enemyPath,itemPath,specialPath);
        
    }
}