package rooms;

import panels.GamePanel;
import tile.Tile;
import tile.Tile.tileRooms;
// this class will handle any level that is in the overworld
// this may have unique behavior not associated with other level types
public class OverWorldLevel extends Level{
	public static OverWorldLevel map[][] = new OverWorldLevel[8][16]; // will be the overworldlevel 2d array
	public OverWorldLevel northLevel;
	public OverWorldLevel southLevel;
	public OverWorldLevel eastLevel;
	public OverWorldLevel westLevel;
	
	public OverWorldLevel(String enemyData, String itemData, String tileData, tileRooms roomType) {
		super(enemyData, itemData, tileData, roomType);

		// TODO Auto-generated constructor stub
	}
	public OverWorldLevel[][] returnMap() {
		return map;
	}
	public static OverWorldLevel[][] generateOverworldLevelMap(GamePanel panel){
		
		 OverWorldLevel currLevel = new  OverWorldLevel( null, null ,"/maps/Starting_level.json", Tile.tileRooms.NOROOM);
		map[7][8] = currLevel;
		currLevel = new  OverWorldLevel(null,null, "/maps/North_level.json", Tile.tileRooms.NOROOM);
		map[6][8] = currLevel;
		currLevel = new  OverWorldLevel(null,null, "/maps/East_level.json", Tile.tileRooms.NOROOM);
		map[7][9] = currLevel;
		currLevel = new  OverWorldLevel(null,null, "/maps/Startinglevel2.json", Tile.tileRooms.NOROOM);
		map[7][7] = currLevel;
		map = assignAdjacencies(map);
		return map;
	}
	private static OverWorldLevel[][] assignAdjacencies(OverWorldLevel[][] levels) {
	    for(int i = 0; i < levels.length; i++) {
	        for(int j = 0; j < levels[i].length; j++) {

	            if(levels[i][j] != null) {

	            	OverWorldLevel north = null;
	            	OverWorldLevel south = null;
	            	OverWorldLevel east = null;
	            	OverWorldLevel west = null;

	                if(i > 0 && levels[i-1][j] != null) {
	                    north = levels[i-1][j];
	                }

	                if(i < levels.length - 1 && levels[i+1][j] != null) {
	                    south = levels[i+1][j];
	                }

	                if(j > 0 && levels[i][j-1] != null) {
	                    west = levels[i][j-1];
	                }

	                if(j < levels[i].length - 1 && levels[i][j+1] != null) {
	                    east = levels[i][j+1];
	                }

	                levels[i][j].setLevelAdjacency(north, south, east, west);
	            }
	        }
	    }

	    return levels;
	}
	public void setLevelAdjacency(OverWorldLevel northLevel, OverWorldLevel southLevel, OverWorldLevel eastLevel, OverWorldLevel westLevel) {
		this.northLevel = northLevel;
		this.southLevel = southLevel;
		this.eastLevel = eastLevel;
		this.westLevel = westLevel;
	}

}
