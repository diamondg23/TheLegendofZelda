package rooms;

import tile.Tile.tileRooms;
// This class will handle the one off rooms that are scattered around the overworld
// technically not part of the overworld so it will have some unique behaviors associated with it.
public class Cave extends Level {
	String text; // if this doesnt have text itll just be ""
	
	
	public Cave(String enemyData, String itemData, String tileData, tileRooms roomType, String text) {
		super(enemyData, itemData, tileData, roomType);
		this.text = text;
	}

}
