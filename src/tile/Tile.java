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
		SHOP()
		

		
	};
	int x, y;
	int screenX,screenY;
	public int spriteIndex = 0;
	public rooms room;
	public boolean canExplode = false;
	
	public Sprite sprite;
	public boolean hasCollision = false;
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
		int canExplode = Integer.parseInt(currTile[2]);
		if(canExplode == 1)
			this.canExplode = true;
		else
			this.canExplode = false;
		return this;
	}
	private rooms findRoom(int room) {
		switch(room) {
		case 0:
			return rooms.NOROOM;
		case 1:
			return rooms.OLDMANHEART;
			
		case 2:
			return rooms.OLDWOMANMAP;
		case 3:
			return rooms.OLDWOMANSHOP;
		case 4:
			return rooms.SECRETPUNISHMENT;
		case 5:
			return rooms.SECRETREWARD;
		case 6:
			return rooms.SHOP;
		default:
			return rooms.NOROOM;
			
		}
	}

}
