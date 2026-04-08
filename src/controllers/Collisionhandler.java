package controllers;

import tile.Tile;
import tile.TileManager;

import java.util.List;

import entity.Entity;
import entity.Entity.EntityDirection;
import entity.Player;
import interfaces.Moveable;
import java.awt.Rectangle;
public class Collisionhandler {
	// would need to change this if the tile size changes
	final static int tileSize = 48;
	public Collisionhandler() {
		// TODO Auto-generated constructor stub
	}
	public static boolean canMove(Moveable m, int futureX, int futureY,int offsetX, int offsetY, TileManager tileM, EntityDirection direction) {
		 // 1. Check tile collision
        if (checkTileCollision(m,futureX,futureY,offsetX,offsetY,tileM, direction))
            return true;
       
        return false;
	}
	private static boolean checkTileRoomCollision(Moveable m, int amountX, int amountY,int offsetX,int offsetY, TileManager tileM, EntityDirection direction) {
		// amount x would be negative if going left and amount y would be negative going up
		// amount x is the amount of movement in the x axis 
		
		
		// COLLISION FIXED!!
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
	    switch(direction) {
		case EAST:
			tile1 = tileM.tiles[topRow][rightCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
			break;
		case NORTH:
			tile1 = tileM.tiles[topRow][leftCol]; 
	        tile2 = tileM.tiles[topRow][rightCol];
			break;
		case SOUTH:
			  tile1 = tileM.tiles[bottomRow][leftCol];
		        tile2 = tileM.tiles[bottomRow][rightCol];
			break;
		case WEST:
			tile1 = tileM.tiles[topRow][leftCol];
	        tile2 = tileM.tiles[bottomRow][leftCol];
			break;
		default:
			tile1 = tileM.tiles[topRow][rightCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
	        System.err.println("SOMETHING REALLY BAD HAPPENED IN COLLISION CHECKER");
			break;
	    
	    	
	    }
	    switch(direction) {
		case EAST:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.y +=5;
				entity_rectangle2.y -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.y +=5;
					entity_rectangle2.y -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case NORTH:
			if(tile1.hasCollision) {
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.x +=5;
				entity_rectangle2.x -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.x +=5;
					entity_rectangle2.x -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case SOUTH:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.x +=5;
				entity_rectangle2.x -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.x +=5;
					entity_rectangle2.x -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case WEST:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.y +=5;
				entity_rectangle2.y -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.y +=5;
					entity_rectangle2.y -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		default:
			break;
	    
	    }
	
	    
	 
	 
	 return true;
	}
	private static boolean checkTileCollision(Moveable m, int amountX, int amountY,int offsetX,int offsetY, TileManager tileM, EntityDirection direction) {
		// amount x would be negative if going left and amount y would be negative going up
		// amount x is the amount of movement in the x axis 
		
		
		// COLLISION FIXED!!
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
	    switch(direction) {
		case EAST:
			tile1 = tileM.tiles[topRow][rightCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
			break;
		case NORTH:
			tile1 = tileM.tiles[topRow][leftCol]; 
	        tile2 = tileM.tiles[topRow][rightCol];
			break;
		case SOUTH:
			  tile1 = tileM.tiles[bottomRow][leftCol];
		        tile2 = tileM.tiles[bottomRow][rightCol];
			break;
		case WEST:
			tile1 = tileM.tiles[topRow][leftCol];
	        tile2 = tileM.tiles[bottomRow][leftCol];
			break;
		default:
			tile1 = tileM.tiles[topRow][rightCol];
	        tile2 = tileM.tiles[bottomRow][rightCol];
	        System.err.println("SOMETHING REALLY BAD HAPPENED IN COLLISION CHECKER");
			break;
	    
	    	
	    }
	    switch(direction) {
		case EAST:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.y +=5;
				entity_rectangle2.y -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.y +=5;
					entity_rectangle2.y -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case NORTH:
			if(tile1.hasCollision) {
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.x +=5;
				entity_rectangle2.x -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.x +=5;
					entity_rectangle2.x -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case SOUTH:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.x +=5;
				entity_rectangle2.x -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.x +=5;
					entity_rectangle2.x -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		case WEST:
			if(tile1.hasCollision) {
				
				Rectangle entity_rectangle = new Rectangle(m.getRectangle());
				Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
				entity_rectangle.y +=5;
				entity_rectangle2.y -=5;
				
				 if(touchesOrIntersects(entity_rectangle, tile1.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile1.collisionHitbox)) {
					
					 return false;
				 }
			 }
			 if(tile2.hasCollision) {
				 
				 Rectangle entity_rectangle = new Rectangle(m.getRectangle());
					Rectangle entity_rectangle2 = new Rectangle(m.getRectangle());
					entity_rectangle.y +=5;
					entity_rectangle2.y -=5;
					
					 if(touchesOrIntersects(entity_rectangle, tile2.collisionHitbox) && touchesOrIntersects(entity_rectangle2, tile2.collisionHitbox)) {
						
						 return false;
					 }
			 }
			break;
		default:
			break;
	    
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
