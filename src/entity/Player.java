package entity;

import java.awt.Rectangle;

import controllers.Collisionhandler;
import controllers.Inventory;
import controllers.TileManager;
import interfaces.Moveable;
import main.Animation;
import panels.GamePanel;
import rooms.Level;
import rooms.LevelData.LevelType;
import tile.Sprite;
import tile.Tile;

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
	
	
	public Player(int x, int y, int width, int height, int ID) {
		super(x, y, width, height, ID);
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
		
		if(panel.currentLevel.levelType == LevelType.OVERWORLD) {
			
		}
		switch(directionMoving) {
		
		case EAST:
			if(Collisionhandler.canMove(this, amount, offsetX,offsetY , 0, tileM, directionMoving)) {
				
				this.x += amount;
				
				this.getRectangle().x += amount;
				isMoving = true;
				
			}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0, tileM, directionMoving);
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
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0, tileM, directionMoving);
			if(room != Tile.tileRooms.NOROOM) {
			
				roomBehavior(panel, room);
			}
			break;

		case SOUTH:
			if(Collisionhandler.canMove(this, 0, amount,offsetX,offsetY, tileM, directionMoving)) {
				this.y += amount;
				
				this.getRectangle().y += amount;
				isMoving = true;
				
			}
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0, tileM, directionMoving);
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
			room = Collisionhandler.checkTileRoomCollision(this, amount, 0, tileM, directionMoving);
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
	
		
		switch(room) {
			
	
		case SCREENPANEAST:
			
			if(panel.currentLevel.east != null && this.directionFacing == EntityDirection.EAST) {
				panel.currentLevel = panel.currentLevel.east;
				panel.currentLevel.load();
				this.ChangePosition(0 + 10, this.y);
			}
		
				
			break;
		case SCREENPANNORTH:
			
			if(panel.currentLevel.north != null && this.directionFacing == EntityDirection.NORTH) {
				
				panel.currentLevel = panel.currentLevel.north;
				panel.currentLevel.load();
				this.ChangePosition( this.x, panel.baseScreenHeight - GamePanel.TILE_SIZE  - 10);
				 
			}
			break;
		case SCREENPANSOUTH:
			if(panel.currentLevel.south != null && this.directionFacing == EntityDirection.SOUTH) {
				panel.currentLevel = panel.currentLevel.south;
				panel.currentLevel.load();
				this.ChangePosition(this.x, 10 + GamePanel.UI_HEIGHT/3);
			}
			break;
		case SCREENPANWEST:
			if(panel.currentLevel.west != null && this.directionFacing == EntityDirection.WEST) {
				panel.currentLevel = panel.currentLevel.west;
				panel.currentLevel.load();
				this.ChangePosition( panel.baseScreenWidth  - GamePanel.TILE_SIZE - 10, this.y);
			}
			
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
