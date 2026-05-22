package editor;

import java.util.LinkedList;

import controllers.LevelFactory;
import entity.Enemy;
import panels.GamePanel;
import rooms.Level;
import rooms.LevelData;
import tile.Tile;

public class FileManager {

	// This class will handle ALL file behavior
	//including: saving tiles,enemies,items,special behavior to jsons
	//being able to load those from jsons as well.
	//and other stuff
	public static boolean SaveTileMap() { // this will create the json that will hold tile object data
		
	}
	public static boolean SaveEnemyMap() { // this will create the json that will hold enemy object data
		
	}
	public static boolean SaveItemMap() { // this will create the json that will hold item object data
		
	}
	public static boolean SaveLevelData() { // this will create the top level json file that will point to the other files associated with this level
		
	}
	
	public static Level loadLevel(LevelData data, GamePanel gp) { // this will load the top level json file which then points to all other files so you should be able to load all relevant data in this function
		return LevelFactory.create(data, gp);
	}
}
