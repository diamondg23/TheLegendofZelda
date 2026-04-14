package rooms;

import panels.GamePanel;
import tile.Tile.tileRooms;
// This class will handle a level that is inside of a dungeon
//may have unique behaviors not associated with all levels
public class DungeonLevel extends Level{

	
	
	
	public DungeonLevel(String enemyData, String itemData, String tileData, tileRooms roomType) {
		super(enemyData, itemData, tileData, roomType);
		// TODO Auto-generated constructor stub
	}
	public static DungeonLevel[][] generateDungeonOneMap(GamePanel panel){
		DungeonLevel[][] levels = new DungeonLevel[5][5];
		return levels;
	}

}
