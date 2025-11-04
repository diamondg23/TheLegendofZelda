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
	int room;
	
	public Sprite sprite;
	boolean hasCollision = false;
	public Tile(Sprite sprite) {
		this.sprite = sprite;
		// 0 means no special room
		this.room = 0;
	}
	public Tile(Sprite sprite, int room) {
		this.sprite = sprite;
		this.room = room;
	}
	public void resolveBehavior(String tileString) {
		//This takes a string and will have switch statement for each behavior it will have.
	}

}
