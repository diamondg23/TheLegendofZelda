package controllers;

import tile.Tile;
import tile.TileManager;

import java.util.List;

import entity.Entity;
import entity.Entity.Direction;
import interfaces.Moveable;
public class Collisionhandler {
	// would need to change this if the tile size changes
	final static int tileSize = 48;
	public Collisionhandler() {
		// TODO Auto-generated constructor stub
	}
	public static boolean canMove(Moveable m, int futureX, int futureY, TileManager tileM, Direction direction) {
		 // 1. Check tile collision
        if (checkTileCollision(m,futureX,futureY,tileM, direction))
            return true;
       
        return false;
	}
	private static boolean checkTileCollision(Moveable m, int amountX, int amountY, TileManager tileM, Direction direction) {

		//TODO ITS COMPLETELY BROKEN I DONT KNOW WHY I THINK ITS BECAUSE POTENTIALLY OF THE OFFSET OF THE DRAWING SINCE WHEN DRAWING TILES THERE IS A OFFSET OF WHERE TO DRAW
		int hitLeft = m.getX() + m.getRectangle().x;
	    int hitRight = hitLeft + m.getRectangle().width;
	    int hitTop = m.getY() + m.getRectangle().y;
	    int hitBottom = hitTop + m.getRectangle().height;

	    int futureLeft = hitLeft + amountX;
	    int futureRight = hitRight + amountX;
	    int futureTop = hitTop + amountY;
	    int futureBottom = hitBottom + amountY;

	    int leftCol = futureLeft / tileSize;
	    int rightCol = futureRight / tileSize;
	    int topRow = futureTop / tileSize - 4;
	    int bottomRow = futureBottom / tileSize - 4;

	    Tile tile1, tile2;
	    System.out.println("top row: " + topRow);
	    if (amountY < 0) {              // NORTH
	        tile1 = tileM.tiles[topRow][leftCol];
	        tile2 = tileM.tiles[topRow][rightCol];
	    } 
	    else if (amountY > 0) {         // SOUTH
	        tile1 = tileM.tiles[bottomRow][leftCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
	    }
	    else if (amountX > 0) {         // EAST
	        tile1 = tileM.tiles[topRow][rightCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
	    }
	    else {                           // WEST
	        tile1 = tileM.tiles[topRow][leftCol];
	        tile2 = tileM.tiles[bottomRow][leftCol];
	    }
	    return !(tile1.hasCollision || tile2.hasCollision);
	}


}
