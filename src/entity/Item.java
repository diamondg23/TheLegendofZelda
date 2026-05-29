package entity;

import java.awt.Rectangle;

import tile.Sprite;

public class Item {

	public Sprite sprite;
	public String name;

	public enum items {
		GREENSWORD(),
		WHITESWORD(),
		BOOMERANG(),
		REDCANDLE(),
		BOMB()
		
	}
	public Rectangle hitbox = new Rectangle();
	public items thisItem;
	public int ID;
	public Item(Sprite sprite, String name) {
	this.sprite = sprite;
	this.name = name;
	switch(name) {
		case "Green Sword":
			thisItem = items.GREENSWORD;
			break;
		case "White Sword":
			thisItem = items.WHITESWORD;
		case "Boomerang":
			thisItem = items.BOOMERANG;
			break;
		case "Bomb":
			thisItem = items.BOMB;
			break;
		case "Red Candle":
			thisItem = items.REDCANDLE;
			break;
		default:
			System.err.println("UNKNOWN ITEM INPUTTED ERROR OCCURED");
	}
	}
	public Item(Sprite sprite, int id) {
		this.sprite = sprite;
		this.ID = id;
	}
	public Item(int x, int y, int height, int width, int id, Sprite sprite) {
		this.hitbox.x = x;
		this.hitbox.y = y;
		this.hitbox.height = height;
		this.hitbox.width = width;
		this.ID = id;
		this.sprite = sprite;
	}
	public Item(String name, int x, int y, int height, int width, int id, Sprite sprite) {
		this.hitbox.x = x;
		this.hitbox.y = y;
		this.hitbox.height = height;
		this.hitbox.width = width;
		this.ID = id;
		this.sprite = sprite;
		this.name = name;
	}

	public static void resolveItemBehavior(items item) {
		
	}
}
