package rooms;

import java.awt.Point;

public class LevelData {
	public String tileData;
	public String enemyData;
	public String itemData;
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

