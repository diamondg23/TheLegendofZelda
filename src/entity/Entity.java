package entity;

import tile.Sprite;

public abstract class Entity {
	public int x;
	public int y;
	public Sprite sprite;
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Entity(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

}
