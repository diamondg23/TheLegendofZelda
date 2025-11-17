package entity;

import interfaces.Moveable;
import main.Animation;
import tile.Sprite;

public class Player extends Entity implements Moveable{
	
	public int currentHealth;
	public int maxHealth;
	public boolean isAttacking = false;
	public int weaponWidth = 0;
	public int weaponHeight = 0;
	
	// whenever link attacks depending on the weapon his actual sprite extends out by x pixels in the direction he attacks. This hitbox needs to be treated separately
	//the sword hitbox should check if it hits enemies and deals damage to enemies
	//the actual body hitbox, link will take damage
	
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}
	public void addAnimation(Animation.AnimationType type , Animation animation) {
		this.animations.put(type, animation);
	}

	

	@Override
	public void Move(Direction directionMoving, int amount) {
		if(directionMoving != this.directionFacing) {
			changeDirection(directionMoving);
		}
		switch(directionMoving) {
		case EAST:
			this.x += amount;
			isMoving = true;
			break;
		case NORTH:
			this.y -= amount;
			isMoving = true;
			break;
		case SOUTH:
			this.y += amount;
			isMoving = true;
			break;
		case WEST:
			this.x -= amount;
			isMoving = true;
			break;
		default:
			System.err.println("ERROR ERROR WRONG DIRECTION GIVEN");
			break;
		
		}
		//if the button is held down, this function should be called every frame
		// this should create a event and stack it onto a queue to be processed.
		//will need to check bounds check and also if there is collision
		
	}
	@Override
	public void changeDirection(Direction direction) {
		// this will change the direction the player is facing. This should change the sprite of the player as well to the corresponding one of the correct direction.
		 this.directionFacing = direction;
		 switch(direction) {
		case EAST:
			this.setAnimation(Animation.AnimationType.WALK_EAST);
			break;
		case NORTH:
			this.setAnimation(Animation.AnimationType.WALK_NORTH);
			break;
		case SOUTH:
			this.setAnimation(Animation.AnimationType.WALK_SOUTH);
			break;
		case WEST:
			this.setAnimation(Animation.AnimationType.WALK_WEST);
			break;
		default:
			break;
		 
		 }
		
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return this.hitboxWidth;
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return this.hitboxWidth;
	}



}
