package entity;

import tile.Sprite;

public class Item {

	public Sprite sprite;
	public String name;
	public enum items {
		BOOMERANG(),
		REDCANDLE(),
		BOMB()
		
	}
	public items currentItem;
	public Item(Sprite sprite, String name) {
	this.sprite = sprite;
	this.name = name;
	switch(name) {
		case "Boomerang":
			currentItem = items.BOOMERANG;
			break;
		case "Bomb":
			currentItem = items.BOMB;
			break;
		case "Red Candle":
			currentItem = items.REDCANDLE;
			break;
		default:
			System.err.println("UNKNOWN ITEM INPUTTED ERROR OCCURED");
	}
	}
	
	public static void resolveItemBehavior(items item) {
		
	}
}
