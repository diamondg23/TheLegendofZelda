package interfaces;

import entity.Entity.Direction;

public interface Moveable {
    int getX();
    int getY();
    int getWidth();
    int getHeight();

	public void Move(Direction directionMoved, int amount);
}
