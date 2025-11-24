package controllers;

import tile.Tile;
import tile.TileManager;

import java.util.List;

import entity.Entity;
import entity.Entity.Direction;
import entity.Player;
import interfaces.Moveable;
import java.awt.Rectangle;
public class Collisionhandler {
	// would need to change this if the tile size changes
	final static int tileSize = 48;
	public Collisionhandler() {
		// TODO Auto-generated constructor stub
	}
	public static boolean canMove(Moveable m, int futureX, int futureY,int offsetX, int offsetY, TileManager tileM, Direction direction) {
		 // 1. Check tile collision
        if (checkTileCollision(m,futureX,futureY,offsetX,offsetY,tileM, direction))
            return true;
       
        return false;
	}
	private static boolean checkTileCollision(Moveable m, int amountX, int amountY,int offsetX,int offsetY, TileManager tileM, Direction direction) {

		
		int hitLeft = m.getX() -offsetX;
	    int hitRight = hitLeft + 48;
	    int hitTop = m.getY() - offsetY;
	    int hitBottom = hitTop + 48;

	    int futureLeft = hitLeft + amountX;
	    int futureRight = hitRight + amountX;
	    int futureTop = hitTop + amountY;
	    int futureBottom = hitBottom + amountY;

	    int leftCol = futureLeft / tileSize;
	    int rightCol = futureRight / tileSize;
	    int topRow = futureTop / tileSize;
	    int bottomRow = futureBottom / tileSize ;
	    
	    Tile tile1, tile2;
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
	    System.out.println("Top row: " + topRow);
	    System.out.println("Bottom row: " + bottomRow);
	    System.out.println("Left Col: " + leftCol);
	    System.out.println("Right Col: " + rightCol);
	    // TODO FIX GETTING STUCK ON WALLS BECAUSE IT IS OVERLAPPING INCORRECTLY
	 if(tile1.hasCollision) {
		
		 if(touchesOrIntersects(m.getRectangle(), tile1.collisionHitbox)) {
			 System.out.println("collision registered");
			 return false;
		 }
	 }
	 if(tile2.hasCollision) {
		 
		 if(touchesOrIntersects(m.getRectangle(), tile2.collisionHitbox)) {
			 return false;
		 }
	 }
	 
	 return true;
	}
	// Returns true if rectangles touch or overlap
    public static boolean touchesOrIntersects(Rectangle r1, Rectangle r2) {
        return r1.x <= r2.x + r2.width &&
               r1.x + r1.width >= r2.x &&
               r1.y <= r2.y + r2.height &&
               r1.y + r1.height >= r2.y;
    }

    public static boolean roomHitBox() {
    	//TODO add boundary check for roomhitboxes then assign behavior based on which room it is.
    	return false;
    }



}
