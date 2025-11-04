package tile;

public class Tile {
	enum rooms{
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
	public int room = 0;
	public boolean canExplode = false;
	
	public Sprite sprite;
	public boolean hasCollision = false;
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		// 0 means no special room
		this.room = 0;
	}
	public Tile(Sprite sprite, int room) {
		this.sprite = sprite;
		this.room = room;
	}
	public Tile resolveBehavior(String tileString ) {
		//This takes a string and will have switch statement for each behavior it will have.
		String[] currTile = tileString.split(",");
		int spriteIndex = Integer.parseInt(currTile[0]);
		this.spriteIndex = spriteIndex;
		int roomNum = Integer.parseInt(currTile[1]);
		this.room = roomNum;
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

}
