package entity;

import main.Animation;
import tile.Sprite;

public abstract class Entity {
	public static enum Direction{
		NORTH(),
		WEST(),
		SOUTH(),
		EAST()
	}
	public Direction directionFacing;
	public int x;
	public int y;
	public Animation sprites;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		directionFacing = Direction.NORTH;
	}
	public Entity(int x, int y, Animation animation) {
		this.x = x;
		this.y = y;
		this.sprites = animation;
		directionFacing = Direction.NORTH;
	}

}
