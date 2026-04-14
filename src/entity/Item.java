package entity;

import tile.Sprite;

public class Item {

	public Sprite sprite;
	public String name;
	public boolean isInInventory = false;
	public enum items {
		GREENSWORD(),
		WHITESWORD(),
		BOOMERANG(),
		REDCANDLE(),
		BOMB()
		
	}
	public items thisItem;
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
	
	public static void resolveItemBehavior(items item) {
		
	}
}
