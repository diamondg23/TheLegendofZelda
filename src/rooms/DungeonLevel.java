package rooms;

import rooms.LevelData.LevelType;

public class DungeonLevel extends Level{
	// unfinished need to work on this later
	 public int dungeonX;
	 public int dungeonY;
	    public DungeonLevel(LevelType type, int id, String name , String tilePath, String enemyPath, String itemPath, String specialPath , int x, int y) {
	        super(type, id, name , tilePath,enemyPath,itemPath,specialPath);
	        this.dungeonX = x;
	        this.dungeonY = y;
	        
	    }

}
