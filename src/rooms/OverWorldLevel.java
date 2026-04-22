package rooms;

import panels.GamePanel;
import rooms.LevelData.LevelType;
import tile.Tile;
import tile.Tile.tileRooms;
// this class will handle any level that is in the overworld
// this may have unique behavior not associated with other level types


import tile.Tile;

public class OverWorldLevel extends Level {

    public OverWorldLevel north;
    public OverWorldLevel south;
    public OverWorldLevel east;
    public OverWorldLevel west;

    public int worldX;
    public int worldY;

    public OverWorldLevel() {
        super(LevelType.OVERWORLD);
    }

    public void setAdjacency(
            OverWorldLevel north,
            OverWorldLevel south,
            OverWorldLevel east,
            OverWorldLevel west
    ) {
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
    }
}

