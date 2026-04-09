package editor;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import main.Animation;
import tile.Sprite;
import tile.Tile;
import tile.Tile.tileRooms;

public class EditorTileRenderer {
	public static boolean drawCollision = false;
	public static boolean drawRoom = false;
	public EditorTileRenderer() {
		// TODO Auto-generated constructor stub
	}
	public static void RenderTileMenu(Sprite[][] sprites, Graphics2D g2, int upBuffer, int tileSize) {
		if(sprites == null) {
			return;
		}
		int x = 0;
		int y = upBuffer;
		for(int i = 0; i < sprites.length; i++) {
			for(int j = 0; j < sprites[i].length; j++) {
				if(sprites[i] == null) {
					break;
				}
				g2.drawImage(sprites[i][j].image, x, y, tileSize, tileSize, null, null);
				if(x >= tileSize) {
					x = 0;
					y += tileSize;
				}
				else {
					x += tileSize;
				}
			}
			
			
		}
		
	}
	public static void RenderEnemyMenu(Animation[][] enemies, Graphics2D g2, int upBuffer, int tileSize) {
		if(enemies == null)
			return;
		int x = 0;
		int y = upBuffer;
		for(int i = 0; i < enemies.length; i++) {
			for(int j = 0; j < enemies[i].length; j++) {
				if(enemies[i] == null) {
					break;
				}
				g2.drawImage(enemies[i][j].getCurrentFrame().image, x, y, tileSize, tileSize, null, null);
				if(x >= tileSize) {
					x = 0;
					y += tileSize;
				}
				else {
					x += tileSize;
				}
			}
			
			
		}
	}
	public static void RenderCurrentEnemy() {
		
	}
	public static void RenderCurrentTile(Graphics2D g2, Tile tile, int x, int y, int tileSize) {
		g2.drawImage(tile.sprite.image, x, y, tileSize, tileSize, null,null);
	}
	
	public static void RenderCurrentMap(Graphics2D g2,Tile[][] mapTiles, int tileSize,int leftBuffer, int topBuffer) {
		for(int row = 0; row < mapTiles.length; row++) {
			for(int col =0; col < mapTiles[row].length;col++) {
				if(mapTiles[row][col] != null) {
					
				
					g2.drawImage(mapTiles[row][col].sprite.image, col*tileSize + leftBuffer, row*tileSize + topBuffer,tileSize,tileSize, null,null);
				if(mapTiles[row][col].hasCollision && drawCollision) {
	                g2.setColor(Color.red);
	                g2.drawRect(
	                    mapTiles[row][col].collisionHitbox.x + leftBuffer,
	                    mapTiles[row][col].collisionHitbox.y + topBuffer,
	                    mapTiles[row][col].collisionHitbox.width - 1,
	                    mapTiles[row][col].collisionHitbox.height - 1
	                );
	                
	            }
				if(mapTiles[row][col].hasRoomCollision && drawCollision) {
	                g2.setColor(Color.green);
	                g2.drawRect(
	                    mapTiles[row][col].roomHitbox.x + leftBuffer,
	                    mapTiles[row][col].roomHitbox.y + topBuffer,
	                    mapTiles[row][col].roomHitbox.width - 1,
	                    mapTiles[row][col].roomHitbox.height - 1
	                );
	                
	            }
				if(mapTiles[row][col].room != tileRooms.NOROOM && drawRoom) {
					g2.setColor(Color.green);
					g2.drawString(String.valueOf(mapTiles[row][col].room.ordinal()), (col*tileSize + leftBuffer + 20), (row*tileSize + topBuffer + 15));
				}
				}
			}
		}
	}
	public static void RenderCurrentEnemies() {
		
	}

}
