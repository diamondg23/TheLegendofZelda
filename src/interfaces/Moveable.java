package interfaces;

import entity.Entity.Direction;

public interface Moveable {
	
	public void Move(Direction directionMoved, int amount);
}
