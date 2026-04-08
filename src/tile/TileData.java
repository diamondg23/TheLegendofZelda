package tile;

public class TileData {

    public int spriteIndex;
    public int room;       // still an int (enum ordinal)
    public boolean hasCollision;
    public boolean hasRoomCollision;
    public boolean canExplode;
    public int direction;  // still ordinal
    public boolean flammable;
    
    public TileData(int spriteIndex, int room, boolean hasCollision,boolean hasRoomCollision, boolean canExplode, int direction, boolean flammable) {
    	this.spriteIndex = spriteIndex;
    	this.room = room;
    	this.hasCollision = hasCollision;
    	this.hasRoomCollision = hasRoomCollision;
    	this.canExplode = canExplode;
    	this.direction = direction;
    	this.flammable = flammable;
    }
}
