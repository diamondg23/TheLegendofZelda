package rooms;

import java.awt.Point;

public class LevelData {
	 // filepath to the top level json
	public String tileData; // file path to the tile json
	public String enemyData; // file path to the enemy json
	public String itemData; // file path to the item json
	public String specialData; // file path to the special behavior json (not implemented yet)
	public enum LevelType{
		OVERWORLD(),
		CAVE(),
		SHOP(),
		LEVEL1(),
		LEVEL2(),
		LEVEL3(),
		LEVEL4(),
		LEVEL5(),
		LEVEL6(),
		LEVEL7(),
		LEVEL8(),
		LEVEL9(),
	}
	public LevelType type;
	public int ID; // ID (assigned in the editor manually for now)
	public int x; // position in the 2d array
	public int y; // position in the 2d array
    
	
	public String name;

    public LevelData() {
     
    }

    public LevelData(
            String name,
            int ID,
            LevelType type,
            String tileData,
            String enemyData,
            String itemData,
            String specialData,
            int x,
            int y
    ) {
        this.name = name;
        this.ID = ID;
        this.type = type;

        this.tileData = tileData;
        this.enemyData = enemyData;
        this.itemData = itemData;
        this.specialData = specialData;

       
        this.x = x;
        this.y = y;
    }
}

