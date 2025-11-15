package main;

import tile.Sprite;

public class Animation {

	Sprite[] sprites;
	Sprite currentSprite;
	int currentIndex;
	boolean isRunning = false;
	public Animation(int amountOfSprites, Sprite[] sprites) {
		if(amountOfSprites < 1) {
			System.err.println("You cant make a animation with less than one sprite ");
		}
	this.sprites = new Sprite[amountOfSprites];
	populateArray(sprites);
	currentSprite = this.sprites[0];
	currentIndex = 0;
	}
	private void populateArray(Sprite[] currentSprites) {
		if(currentSprites.length != this.sprites.length) {
			System.err.println("You have a mismatch size in your two arrays in your animation class");
		}
		for(int i = 0; i < sprites.length; i++) {
			this.sprites[i] = currentSprites[i];
		}
	}
	public void incrementSprite() {
		if(currentIndex >= sprites.length-1) {
			currentSprite = sprites[0];
			currentIndex = 0;
		}
		else {
			currentIndex++;
			currentSprite = sprites[currentIndex];
		}
	}
	public void decrementSprite() {
		if(currentIndex <= 0) {
			currentSprite = sprites[sprites.length-1];
			currentIndex = sprites.length-1;
			
		}
		else {
			currentIndex--;
			currentSprite = sprites[currentIndex];
		}
	}
	public void changeAnimation(Sprite[] animation) {
		this.sprites = animation;
	}
	

}
