package entity;

import java.awt.Rectangle;

import controllers.Collisionhandler;
import controllers.Inventory;
import interfaces.Moveable;
import main.Animation;
import main.Level;
import panels.GamePanel;
import tile.Sprite;
import tile.Tile;
import tile.TileManager;

public class Player extends Entity implements Moveable{
	
	public int currentHealth;
	public int maxHealth;
	public boolean isAttacking = false;
	public int weaponWidth = 0;
	public int weaponHeight = 0;
	public Inventory inventory;
	public int lastx;
	public int lasty;
	
	// whenever link attacks depending on the weapon his actual sprite extends out by x pixels in the direction he attacks. This hitbox needs to be treated separately
	//the sword hitbox should check if it hits enemies and deals damage to enemies
	//the actual body hitbox, link will take damage
	
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		lastx = x;
		lasty = y;
		inventory = new Inventory();
		
		
	}
	

	

	@Override
	public void Move(EntityDirection directionMoving, int amount,int offsetX,int offsetY, TileManager tileM, GamePanel panel) {
		if(directionMoving != this.directionFacing) {
			changeDirection(directionMoving);
		}
		Tile.tileRooms room;
		switch(directionMoving) {
		
		case EAST:
			if(Collisionhandler.canMove(this, amount, 0,offsetX,offsetY, tileM, directionMoving)) {
				this.x += amount;
				
				this.getRectangle().x += amount;
				isMoving = true;
				
			}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0,offsetX,offsetY, tileM, directionMoving);
			if(room != Tile.tileRooms.NOROOM) {
				roomBehavior(panel, room);
			}
			
			break;
			
		case NORTH:
			if(Collisionhandler.canMove(this, 0, -amount,offsetX,offsetY, tileM, directionMoving)) {
				this.y -= amount;
				this.getRectangle().y -=amount;
				isMoving = true;
				
			}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0,offsetX,offsetY, tileM, directionMoving);
			if(room != Tile.tileRooms.NOROOM) {
				System.out.println("room detected");
				roomBehavior(panel, room);
			}
			break;

		case SOUTH:
			if(Collisionhandler.canMove(this, 0, amount,offsetX,offsetY, tileM, directionMoving)) {
				this.y += amount;
				
				this.getRectangle().y += amount;
				isMoving = true;
				
			}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0,offsetX,offsetY, tileM, directionMoving);
			if(room != Tile.tileRooms.NOROOM) {
				roomBehavior(panel, room);
			}
			break;

		case WEST:
			if(Collisionhandler.canMove(this, -amount, 0,offsetX,offsetY, tileM, directionMoving)) {
				this.x -= amount;
				this.getRectangle().x -= amount;
				isMoving = true;
			
		}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0,offsetX,offsetY, tileM, directionMoving);
			if(room != Tile.tileRooms.NOROOM) {
				roomBehavior(panel, room);
			}
			break;
		default:
			System.err.println("ERROR ERROR WRONG DIRECTION GIVEN");
			
			break;
		
		}
	
		//if the button is held down, this function should be called every frame
		// this should create a event and stack it onto a queue to be processed.
		//will need to check bounds check and also if there is collision
	}
	private void roomBehavior(GamePanel panel, Tile.tileRooms room) {
		//this will be called when a player triggeres a room hitbox
		switch(room) {
		case LEVEL1:
			break;
		case LEVEL2:
			break;
		case LEVEL3:
			break;
		case LEVEL4:
			break;
		case LEVEL5:
			break;
		case LEVEL6:
			break;
		case LEVEL7:
			break;
		case LEVEL8:
			break;
		case LEVEL9:
			break;
		case NOROOM:
			break;
		case OLDMANHEART:
			break;
		case OLDMANSWORD:
			if(panel.currentLevel != null) {
				panel.currentOverworldLevel.loadLevel(panel.tileM);
				panel.currentLevel = null;
				this.ChangePosition(lastx, lasty);
			}
			else {
				lastx = this.x;
				lasty = this.y;
				panel.currentLevel = new Level(null, null, "/maps/OldMan_Level.json");
				panel.currentLevel.loadLevel(panel.tileM);
				this.ChangePosition(panel.screenWidth/2, panel.screenHeight-panel.tileSize*4);
			}
			break;
		case OLDMANWHITESWORD:
			break;
		case OLDWOMANMAP:
			break;
		case OLDWOMANSHOP:
			break;
		case SCREENPANEAST:
			if(panel.currentOverworldLevel.eastLevel != null && this.directionFacing == EntityDirection.EAST) {
				panel.currentOverworldLevel = panel.currentOverworldLevel.eastLevel;
				panel.currentOverworldLevel.loadLevel(panel.tileM);
				this.ChangePosition(0 + 10, this.y);
			}
		
				
			break;
		case SCREENPANNORTH:
			
			if(panel.currentOverworldLevel.northLevel != null && this.directionFacing == EntityDirection.NORTH) {
				panel.currentOverworldLevel = panel.currentOverworldLevel.northLevel;
				panel.currentOverworldLevel.loadLevel(panel.tileM);
				this.ChangePosition(this.x, panel.screenHeight - 50);
				 
			}
			break;
		case SCREENPANSOUTH:
			if(panel.currentOverworldLevel.southLevel != null && this.directionFacing == EntityDirection.SOUTH) {
				panel.currentOverworldLevel = panel.currentOverworldLevel.southLevel;
				panel.currentOverworldLevel.loadLevel(panel.tileM);
				this.ChangePosition(this.x, panel.maxUIScreenRow + 10);
			}
			break;
		case SCREENPANWEST:
			if(panel.currentOverworldLevel.westLevel != null && this.directionFacing == EntityDirection.WEST) {
				panel.currentOverworldLevel = panel.currentOverworldLevel.westLevel;
				panel.currentOverworldLevel.loadLevel(panel.tileM);
				this.ChangePosition(panel.screenWidth - 60, this.y);
			}
			
			break;
		case SECRETPUNISHMENT:
			break;
		case SECRETREWARD:
			break;
		case SHOP:
			break;
		default:
			System.err.println("Something happened in the room checking logic");
			break;
		
		}
	}
	@Override
	public void changeDirection(EntityDirection direction) {
		// this will change the direction the player is facing. This should change the sprite of the player as well to the corresponding one of the correct direction.
		 this.directionFacing = direction;
		 switch(direction) {
		case EAST:
			this.setAnimation(Animation.AnimationType.WALK_EAST);
			break;
		case NORTH:
			this.setAnimation(Animation.AnimationType.WALK_NORTH);
			break;
		case SOUTH:
			this.setAnimation(Animation.AnimationType.WALK_SOUTH);
			break;
		case WEST:
			this.setAnimation(Animation.AnimationType.WALK_WEST);
			break;
		default:
			break;
		 
		 }
		
	}
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}
	public Rectangle getRectangle() {
		return solidArea;
	}


}
