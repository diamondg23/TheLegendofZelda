package interfaces;

import java.awt.Rectangle;

import entity.Entity.EntityDirection;
import panels.GamePanel;
import tile.TileManager;

public interface Moveable {
    int getX();
    int getY();
    Rectangle getRectangle(); // this is the hitbox of the entity

	public void Move(EntityDirection directionMoved, int amount,int offsetX, int offsetY, TileManager tileM, GamePanel panel);
}
