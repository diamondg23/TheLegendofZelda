package tile;

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
		LEVEL9()
		

		
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
	int screenX,screenY;
	public int spriteIndex = 0;
	public rooms room;
	public boolean canExplode = false;
	
	public Sprite sprite;
	public boolean hasCollision = false;
	public boolean isVisible = true;
	public boolean isEnabled = true;
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		
	}
	
	
	
	public Tile resolveBehavior(String tileString ) {
		
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
		return this;
		
	}


}
