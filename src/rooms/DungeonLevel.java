package rooms;

import panels.GamePanel;
import rooms.LevelData.LevelType;
import tile.Tile.tileRooms;
// This class will handle a level that is inside of a dungeon
//may have unique behaviors not associated with all levels


public class DungeonLevel extends Level {

    public DungeonLevel(LevelType type) {
        super(LevelType.DUNGEON);
    }
}