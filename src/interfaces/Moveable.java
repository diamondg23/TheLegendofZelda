package interfaces;

import java.awt.Rectangle;

import entity.Entity.Direction;
import tile.TileManager;

public interface Moveable {
    int getX();
    int getY();
    Rectangle getRectangle();

	public void Move(Direction directionMoved, int amount, TileManager tileM);
}
