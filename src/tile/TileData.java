package tile;

public class TileData {

    public int spriteIndex;
    public int room;       // still an int (enum ordinal)
    public int roomid;	//this acts as a pointer to a level ID, for cases where it isnt simply scrolling to the next level adjacently
    public boolean hasCollision;
    public boolean hasRoomCollision;
    public boolean canExplode;
    public int direction;  // still ordinal
    public boolean flammable;
    
    public TileData(int spriteIndex, int room, int roomid, boolean hasCollision,boolean hasRoomCollision, boolean canExplode, int direction, boolean flammable) {
    	this.spriteIndex = spriteIndex;
    	this.room = room;
    	this.roomid = room;
    	this.hasCollision = hasCollision;
    	this.hasRoomCollision = hasRoomCollision;
    	this.canExplode = canExplode;
    	this.direction = direction;
    	this.flammable = flammable;
    }
}
