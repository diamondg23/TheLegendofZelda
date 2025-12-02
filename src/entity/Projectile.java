package entity;

import tile.Sprite;

public class Projectile extends Entity {

	int speed;
	
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 5;
		//moves 5 pixels a frame
	}

	@Override
	public void changeDirection(Direction direction) {
		this.directionFacing = direction;
		
	}

}
