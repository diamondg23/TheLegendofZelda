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
		// TODO Auto-generated constructor stub
	}

	public Player(int x, int y, int width, int height, Animation sprites) {
		super(x, y, width, height, sprites);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Move(Direction directionMoving, int amount) {
		if(directionMoving != this.directionFacing) {
			changeDirection(directionMoving);
		}
		//if the button is held down, this function should be called every frame
		// this should create a event and stack it onto a queue to be processed.
		
	}

	@Override
	public void changeDirection(Direction direction) {
		// this will change the direction the player is facing. This should change the sprite of the player as well to the corresponding one of the correct direction.
		
		
	}

}
