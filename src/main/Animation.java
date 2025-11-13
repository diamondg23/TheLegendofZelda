package main;

import tile.Sprite;

public class Animation {

	Sprite[] sprites;
	Sprite currentSprite;
	int currentIndex;
	boolean isRunning = false;
	public Animation(int amountOfSprites) {
	sprites = new Sprite[amountOfSprites];
	currentSprite = sprites[0];
	currentIndex = 0;
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
