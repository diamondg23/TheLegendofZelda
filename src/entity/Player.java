package entity;

import java.awt.Rectangle;

import controllers.Collisionhandler;
import interfaces.Moveable;
import main.Animation;
import tile.Sprite;
import tile.TileManager;

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
	public void Move(Direction directionMoving, int amount,int offsetX,int offsetY, TileManager tileM) {
		if(directionMoving != this.directionFacing) {
			changeDirection(directionMoving);
		}
		switch(directionMoving) {
		case EAST:
			if(Collisionhandler.canMove(this, amount, 0,offsetX,offsetY, tileM, directionMoving)) {
				this.x += amount;
				this.getRectangle().x += amount;
				isMoving = true;
				break;
			}
			break;
			
		case NORTH:
			if(Collisionhandler.canMove(this, 0, -amount,offsetX,offsetY, tileM, directionMoving)) {
				this.y -= amount;
				this.getRectangle().y -=amount;
				isMoving = true;
				break;
			}
			break;

		case SOUTH:
			if(Collisionhandler.canMove(this, 0, amount,offsetX,offsetY, tileM, directionMoving)) {
				this.y += amount;
				this.getRectangle().y += amount;
				isMoving = true;
				break;
			}
			break;

		case WEST:
			if(Collisionhandler.canMove(this, -amount, 0,offsetX,offsetY, tileM, directionMoving)) {
				this.x -= amount;
				this.getRectangle().x -= amount;
				isMoving = true;
				break;
		}
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
	public Rectangle getRectangle() {
		return solidArea;
	}


}
