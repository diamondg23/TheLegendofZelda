package interfaces;

import java.awt.Rectangle;

import controllers.TileManager;
import entity.Entity.EntityDirection;
import panels.GamePanel;

public interface Moveable {
    int getX();
    int getY();
    Rectangle getRectangle(); // this is the hitbox of the entity

	public void Move(EntityDirection directionMoved, int amount,int offsetX, int offsetY, TileManager tileM, GamePanel panel);
}
