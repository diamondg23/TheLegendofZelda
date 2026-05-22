package rooms;

import java.awt.Point;

public class LevelData {
	public String levelData; // filepath to the top level json
	public String tileData; // file path to the tile json
	public String enemyData; // file path to the enemy json
	public String itemData; // file path to the item json
	public enum LevelType{
		SHOP(),
		OVERWORLD(),
		CAVE(),
		DUNGEON()
	}
	public LevelType type;
	public int ID;
    public int returnX;
    public int returnY;
	public String specialData;
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
            int returnX,
            int returnY
    ) {
        this.name = name;
        this.ID = ID;
        this.type = type;

        this.tileData = tileData;
        this.enemyData = enemyData;
        this.itemData = itemData;
        this.specialData = specialData;

        this.returnX = returnX;
        this.returnY = returnY;
    }
}

