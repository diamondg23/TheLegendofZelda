package main;

import entity.Entity;
import tile.Sprite;

public class Animation {
	public enum AnimationType{
        IDLE_NORTH,
        IDLE_SOUTH,
        IDLE_EAST,
        IDLE_WEST,
        WALK_NORTH,
        WALK_SOUTH,
        WALK_EAST,
        WALK_WEST,
        ATTACK_NORTH,
        ATTACK_SOUTH,
        ATTACK_EAST,
        ATTACK_WEST
	}

	Sprite[] sprites;

	int frame = 0;
	int frameDelay = 10;
	int counter = 0;
	public boolean isRunning = false;
	public Animation(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
    public Sprite getCurrentFrame() {
        return sprites[frame];
    }
    public void IncrementFrame() {
    	this.frame++;
    }

    public void update() {
        counter++;
        if (counter >= frameDelay && isRunning) {
            counter = 0;
            frame = (frame + 1) % sprites.length;
        }
    }

    public void reset() {
        frame = 0;
        counter = 0;
    }
    public static Animation addAnimation(Entity entity, Sprite[] array ) {
    	// this function is to abstract away the animation handling so you dont have to see it
    	entity.addAnimation(null, null);
    	return null;
    	
    }

}
