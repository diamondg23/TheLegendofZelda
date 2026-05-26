package rooms;

import rooms.LevelData.LevelType;
import tile.Tile;
import tile.Tile.tileRooms;
// This class will handle the one off rooms that are scattered around the overworld
// technically not part of the overworld so it will have some unique behaviors associated with it.


public class Cave extends Level {

    public String text;
   

    public Cave(LevelType type) {
        super(LevelType.CAVE);
        
    }
}