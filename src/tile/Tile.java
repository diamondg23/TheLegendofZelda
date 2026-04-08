package tile;

import java.awt.Rectangle;

public class Tile {
	
	// DO NOT CHANGE ORDERING OF THE ROOMS BECAUSE IT WILL CHANGE THE RESULTING INT IN THE FILE, IF YOU CHANGE ORDERINGS YOU HAVE TO REMAKE ALL THE FILES
	//if you want to add more rooms in the future add them at the bottom.
	public enum tileRooms{
		
		NOROOM(), 
		OLDMANHEART(),
		OLDWOMANMAP(),
		SECRETREWARD(),
		SECRETPUNISHMENT(),
		OLDWOMANSHOP(),
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
		SCREENPANNORTH(),
		SCREENPANSOUTH(),
		SCREENPANWEST(),
		SCREENPANEAST(),
		OLDMANSWORD(),
		OLDMANWHITESWORD()
		
		
	};
	public enum specialBehavior{
		NOBEHEAVIOR()
		
	}
	public enum tileMoveDirection{
		NODIRECTION(),
		NORTH(),
		SOUTH(),
		EAST(),
		WEST()
	};
	public specialBehavior behavior;
	public tileMoveDirection direction = tileMoveDirection.NODIRECTION;
	public boolean isFlammable = false;
	int x, y;

	public int spriteIndex = 0;
	public tileRooms room;
	public boolean canExplode = false;
	
	public Sprite sprite;
	public boolean hasCollision = false;
	public boolean hasRoomCollision = false;
	public boolean isVisible = true;
	public boolean isEnabled = true;
	public Rectangle collisionHitbox;
	public Rectangle roomHitbox;
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		
	}
    public Tile(Tile other) {
        this.sprite = other.sprite;            
        this.room = other.room;
        this.canExplode = other.canExplode;
        this.hasCollision = other.hasCollision;
        this.hasRoomCollision = other.hasRoomCollision;
    }
    public Tile(Sprite sprite, int room, boolean hasCollision, boolean hasRoomCollision, boolean canExplode, int direction, boolean isFlammable,int x, int y) {
    	this.sprite = sprite;
    	this.room = tileRooms.values()[room];
    	this.hasCollision = hasCollision;
    	this.hasRoomCollision = hasRoomCollision;
    	this.canExplode = canExplode;
    	this.direction = tileMoveDirection.values()[direction];
    	this.isFlammable = isFlammable;
    	this.x = x;
    	this.y = y;
    	determineCollisionHitBox();
    	determineRoomHitBox();
    }
	
	private void determineCollisionHitBox() {
		// if it has collision it will make the collision box unique for whatever sprite it is 
		if(hasCollision) {
			switch(spriteIndex) {
			case 54:
				collisionHitbox = new Rectangle(this.x+5,this.y,43,25);
				break;
			case 56:
				collisionHitbox = new Rectangle(this.x,this.y,36,40);
				break;
			default:
				collisionHitbox = new Rectangle(this.x,this.y,48,48);
			}
		}
	}
	private void determineRoomHitBox() {
		switch(this.room) {
		case SCREENPANEAST:
			this.roomHitbox = new Rectangle(this.x+20, this.y, 48,48);
			break;
		case SCREENPANNORTH:
			this.roomHitbox = new Rectangle(this.x, this.y, 48,20);
			break;
		case SCREENPANSOUTH:
			this.roomHitbox = new Rectangle(this.x, this.y+20, 48,48);
			break;
		case SCREENPANWEST:
			this.roomHitbox = new Rectangle(this.x, this.y, 20,48);
			break;
		default:
			this.roomHitbox = new Rectangle(this.x, this.y, 48,48);
		
		}
	}
	


}
