package entity;

import tile.Sprite;

public class Projectile extends Entity {

	int speed;
	
	public Projectile(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
		speed = 5;
		//moves 5 pixels a frame
	}

	@Override
	public void changeDirection(EntityDirection direction) {
		this.directionFacing = direction;
		
	}

}
