package entity;

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
	public Sprite sprite;
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		directionFacing = Direction.NORTH;
	}
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		directionFacing = Direction.NORTH;
	}

}
