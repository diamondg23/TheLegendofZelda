package controllers;

import tile.TileManager;

import java.util.List;

import entity.Entity;
import interfaces.Moveable;
public class Collisionhandler {

	public Collisionhandler() {
		// TODO Auto-generated constructor stub
	}
	public boolean canMove(Moveable m, int futureX, int futureY, List<Entity> allEntities, TileManager tileM) {
		 // 1. Check tile collision
        if (checkTileCollision(m,futureX,futureY,tileM))
            return false;

        // 2. Check other entity collision
        for (Entity e : allEntities) {
            // TODO FIGURE OUT HOW TO CHECK IF HIT ANY ENEMTIES
        }

        return true;
	}
	private boolean checkTileCollision(Moveable m, int futureX, int futureY, TileManager tileM) {
		// TODO FIGURE OUT HOW TO FREAKING DO THE COLLISION CHECK
		return false;
	}

}
