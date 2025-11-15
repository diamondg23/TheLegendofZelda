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
	
	//this should be the hitbox;
	public int width;
	public int height;
	
	
	public Animation sprites;
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		directionFacing = Direction.NORTH;
		this.width = width;
		this.height = height;
	}
	public Entity(int x, int y,int width, int height, Animation animation) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprites = animation;
		directionFacing = Direction.NORTH;
	}
	public abstract void changeDirection(Direction direction);
	public abstract void changeSprites(Animation animation);

}
