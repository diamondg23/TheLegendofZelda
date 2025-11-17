package entity;

import java.util.HashMap;
import java.util.Map;

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
	public boolean isMoving = false;
	//this should be the hitbox;
	public int width;
	public int height;
	public int imageScale = 3;
	public int hitboxWidth;
	public int hitboxHeight;
	public Animation.AnimationType currentType;
	
	public Map<Animation.AnimationType, Animation> animations = new HashMap<>();
	
	public Entity(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		directionFacing = Direction.NORTH;
		this.width = width;
		this.height = height;
		this.hitboxWidth = (width*imageScale)-width/2;
		this.hitboxHeight = (height*imageScale)-height/2;
	}

	public void setAnimation(Animation.AnimationType name) {
	    currentType = name;

	    Animation anim = animations.get(name);
	    if (anim != null) {
	        anim.reset();
	    }
	}


    public Animation getAnimation() {
    	return animations.get(currentType);
    }
	public abstract void changeDirection(Direction direction);

}
