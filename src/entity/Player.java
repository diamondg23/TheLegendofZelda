package entity;

import interfaces.Moveable;
import main.Animation;
import tile.Sprite;

public class Player extends Entity implements Moveable{
	
	public int currentHealth;
	public int maxHealth;
	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Player(int x, int y, Animation sprites) {
		super(x, y, sprites);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Move(Direction directionMoving, int amount) {
		//if the button is held down, this function should be called every frame
		// this should create a event and stack it onto a queue to be processed.
		
	}

}
