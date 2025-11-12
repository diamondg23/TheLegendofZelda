package entity;

import interfaces.Moveable;
import tile.Sprite;

public class Player extends Entity implements Moveable{

	public Player(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public Player(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Move(Direction directionMoving, int amount) {
		// this should create a event and stack it onto a queue to be processed.
		
	}

}
