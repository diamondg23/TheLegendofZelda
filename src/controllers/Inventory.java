package controllers;
import java.util.LinkedList;

import entity.Item;
public class Inventory {
	LinkedList<Item> itemList = new LinkedList<Item>();
	Item currentSword = null;
	Item currentSecondaryItem = null;
	
	
	
	public void addItem(Item item) {
		itemList.add(item);
	}
	public Item getItem(Item.items item) {
		for(Item currItem : itemList) {
			if(currItem.currentItem.equals(item)) {
				return currItem;
			}
		}
		return null;
	}
	public boolean hasItem(Item item) {
		return itemList.contains(item);
	}
}
