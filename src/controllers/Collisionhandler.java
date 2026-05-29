package controllers;

import tile.Tile;

import java.util.List;

import entity.Entity;
import entity.Entity.EntityDirection;
import entity.Player;
import interfaces.Moveable;
import panels.GamePanel;

import java.awt.Rectangle;
public class Collisionhandler {
	// would need to change this if the tile size changes
	final static int tileSize = 16;
	public Collisionhandler() {
		// TODO Auto-generated constructor stub
	}
	public static boolean canMove(Moveable m, int futureX, int futureY,int offsetX, int offsetY, TileManager tileM, EntityDirection direction) {
		 // 1. Check tile collision
        if (checkTileCollision(m,futureX,futureY,tileM, direction))
            return true;
       
        return false;
	}
	public static Tile.tileRooms checkTileRoomCollision(
	        Moveable m,
	        int amountX,
	        int amountY,
	        TileManager tileM,
	        EntityDirection direction
	) {

	    Rectangle futureHitbox = new Rectangle(m.getRectangle());

	    // convert screen-space -> world-space
	    futureHitbox.y -= GamePanel.UI_HEIGHT / 3;

	    // simulate future movement
	    futureHitbox.x += amountX;
	    futureHitbox.y += amountY;

	    int leftCol =
	            futureHitbox.x / tileSize;

	    int rightCol =
	            (futureHitbox.x + futureHitbox.width - 1) / tileSize;

	    int topRow =
	            futureHitbox.y / tileSize;

	    int bottomRow =
	            (futureHitbox.y + futureHitbox.height - 1) / tileSize;

	    // bounds protection
	    if (leftCol < 0 ||
	        rightCol >= tileM.tiles[0].length ||
	        topRow < 0 ||
	        bottomRow >= tileM.tiles.length) {

	        return Tile.tileRooms.NOROOM;
	    }

	    // check ALL touched tiles
	    for (int row = topRow; row <= bottomRow; row++) {

	        for (int col = leftCol; col <= rightCol; col++) {

	            Tile tile = tileM.tiles[row][col];

	            if (tile == null) {
	                continue;
	            }

	            if (!tile.hasRoomCollision) {
	                continue;
	            }

	            if (tile.roomHitbox == null) {
	                continue;
	            }

	            if (futureHitbox.intersects(tile.roomHitbox)) {
	                return tile.room;
	            }
	        }
	    }

	    return Tile.tileRooms.NOROOM;
	}


	
	
	
	private static boolean checkTileCollision(
	        Moveable m,
	        int amountX,
	        int amountY,
	        TileManager tileM,
	        EntityDirection direction) {

	    Rectangle futureHitbox = new Rectangle(m.getRectangle());

	    // convert from screen-space to world-space
	    futureHitbox.y -= GamePanel.UI_HEIGHT / 3;

	    // simulate future movement
	    futureHitbox.x += amountX;
	    futureHitbox.y += amountY;

	    int leftCol =
	            futureHitbox.x / tileSize;

	    int rightCol =
	            (futureHitbox.x + futureHitbox.width - 1) / tileSize;

	    int topRow =
	            futureHitbox.y / tileSize;

	    int bottomRow =
	            (futureHitbox.y + futureHitbox.height - 1) / tileSize;

	    // bounds protection
	    if (leftCol < 0 ||
	        rightCol >= tileM.tiles[0].length ||
	        topRow < 0 ||
	        bottomRow >= tileM.tiles.length) {

	        return false;
	    }

	    // check ALL tiles touching the future hitbox
	    for (int row = topRow; row <= bottomRow; row++) {

	        for (int col = leftCol; col <= rightCol; col++) {

	            Tile tile = tileM.tiles[row][col];

	            if (tile == null) {
	                continue;
	            }

	            if (!tile.hasCollision) {
	                continue;
	            }

	            if (tile.collisionHitbox == null) {
	                continue;
	            }

	            if (futureHitbox.intersects(tile.collisionHitbox)) {
	                return false;
	            }
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
