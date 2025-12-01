package tile;

import java.awt.Rectangle;

public class Tile {
	
	// DO NOT CHANGE ORDERING OF THE ROOMS BECAUSE IT WILL CHANGE THE RESULTING INT IN THE FILE, IF YOU CHANGE ORDERINGS YOU HAVE TO REMAKE ALL THE FILES
	//if you want to add more rooms in the future add them at the bottom.
	public enum rooms{
		
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
	public enum directionMove{
		NODIRECTION(),
		NORTH(),
		SOUTH(),
		EAST(),
		WEST()
	};
	public specialBehavior behavior;
	public directionMove direction = directionMove.NODIRECTION;
	public boolean isFlammable = false;
	int x, y;

	public int spriteIndex = 0;
	public rooms room;
	public boolean canExplode = false;
	
	public Sprite sprite;
	public boolean hasCollision = false;
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
    }
    public Tile(Sprite sprite, int room, boolean hasCollision, boolean canExplode, int direction, boolean isFlammable,int x, int y) {
    	this.sprite = sprite;
    	this.room = rooms.values()[room];
    	this.hasCollision = hasCollision;
    	this.canExplode = canExplode;
    	this.direction = directionMove.values()[direction];
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
				collisionHitbox = new Rectangle(x+5,y,43,25);
				break;
			case 56:
				collisionHitbox = new Rectangle(x,y,36,40);
				break;
			default:
				collisionHitbox = new Rectangle(x,y,48,48);
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
	public Tile resolveBehavior(String tileString, int x, int y) {
		
		//This takes a string and will have switch statement for each behavior it will have.
		String[] currTile = tileString.split(",");
		int spriteIndex = Integer.parseInt(currTile[0]);
		this.spriteIndex = spriteIndex;
		int roomNum = Integer.parseInt(currTile[1]);
		this.room = rooms.values()[roomNum];
		
		int hasCollision = Integer.parseInt(currTile[2]);
		if(hasCollision == 1)
			this.hasCollision = true;
		else
			this.hasCollision = false;
		int canExplode = Integer.parseInt(currTile[3]);
		if(canExplode == 1)
			this.canExplode = true;
		else
			this.canExplode = false;
		int direction = Integer.parseInt(currTile[4]);
		switch(direction) {
		case 0:
			this.direction =directionMove.NODIRECTION;
			break;
		case 1:
			this.direction = directionMove.NORTH;
			break;
		case 2:
			this.direction = directionMove.SOUTH;
			break;
		case 3:
			this.direction = directionMove.EAST;
			break;
		case 4:
			this.direction = directionMove.WEST;
			break;
		default:
			this.direction = directionMove.NODIRECTION;
		}
		int flammable = Integer.parseInt(currTile[5]);
		if(flammable == 0) {
			this.isFlammable = false;
		}
		else {
			this.isFlammable = true;
		}
		this.x = x;
		this.y = y;
		determineCollisionHitBox();
		determineRoomHitBox();
		return this;
		
	}


}
