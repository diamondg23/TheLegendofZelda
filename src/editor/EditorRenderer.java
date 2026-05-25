package editor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;

import javax.swing.JLabel;

import entity.Enemy;
import entity.Item;
import main.Animation;
import tile.Sprite;
import tile.Tile;
import tile.Tile.tileRooms;

public class EditorRenderer {
	public static boolean drawCollision = false;
	public static boolean drawRoom = false;
	public static boolean drawEnemies = true;
	public static boolean drawTiles = true;
	public static boolean drawItems = true;
	public static EditorPanel.currentScrollBar scrollBar = EditorPanel.currentScrollBar.TILES;
	public EditorRenderer() {
		// TODO Auto-generated constructor stub
	}
	public static void RenderTileMenu(Sprite[][] sprites, Graphics2D g2, int upBuffer, int tileSize) {
		if(sprites == null) {
			return;
		}
		if(scrollBar == EditorPanel.currentScrollBar.ENEMIES || scrollBar == EditorPanel.currentScrollBar.ITEMS) {
			return;
		}
		int x = 0;
		int y = upBuffer;
		for(int i = 0; i < sprites.length; i++) {
			for(int j = 0; j < sprites[i].length; j++) {
				if(sprites[i][j] == null) {
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
	public static void RenderEnemyMenu(Sprite[][] enemies, Graphics2D g2, int upBuffer, int tileSize) {
		if(scrollBar == EditorPanel.currentScrollBar.TILES || scrollBar == EditorPanel.currentScrollBar.ITEMS) {
			return;
		}
		if(enemies == null)
			return;
		
		int x = 0;
		int y = upBuffer;
		for(int i = 0; i < enemies.length; i++) {
			for(int j = 0; j < enemies[i].length; j++) {
				if(enemies[i][j] == null) {
					break;
				}
				g2.drawImage(enemies[i][j].image, x, y, tileSize, tileSize, null, null);
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

	public static void RenderItemMenu(Sprite[][] items, Graphics2D g2, int upBuffer, int upscale , int tileSize) {
		if(scrollBar == EditorPanel.currentScrollBar.TILES || scrollBar == EditorPanel.currentScrollBar.ENEMIES) {
			return;
		}
		if(items == null)
			return;
		
		int x = 0;
		int y = upBuffer;
		for(int i = 0; i < items.length; i++) {
			for(int j = 0; j < items[i].length; j++) {
				if(items[i][j] == null) {
					break;
				}
				g2.drawImage(items[i][j].image, x, y, items[i][j].image.getWidth()*upscale, items[i][j].image.getHeight()*upscale, null, null);
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
	
	public static void RenderCurrentItem(Graphics2D g2, Item item, int x, int y, int scale) {
		g2.drawImage(item.sprite.image, x, y, item.sprite.image.getWidth()*scale, item.sprite.image.getHeight()*scale, null,null);
	}
	public static void RenderCurrentEnemy(Graphics2D g2, Enemy enemy, int x, int y, int tileSize) {
		if(enemy.getAnimation() == null)
			return;
		g2.drawImage(enemy.getAnimation().getCurrentFrame().image, x, y, tileSize, tileSize, null,null);
	}
	public static void RenderCurrentTile(Graphics2D g2, Tile tile, int x, int y, int tileSize) {
		g2.drawImage(tile.sprite.image, x, y, tileSize, tileSize, null,null);
	}
	
	public static void RenderCurrentMap(Graphics2D g2,Tile[][] mapTiles, int tileSize,int leftBuffer, int topBuffer) {
		if(drawTiles == false)
			return;
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
	public static void RenderCurrentEnemies(Graphics2D g2,LinkedList<Enemy> enemies, int tileSize,int leftBuffer, int topBuffer) {
		if(drawEnemies == false)
			return;
		for(int i = 0; i < enemies.size(); i++) {
			g2.drawImage(enemies.get(i).getAnimation().getCurrentFrame().image, enemies.get(i).x , enemies.get(i).y + topBuffer,tileSize,tileSize, null,null);
		}
	}
	public static void RenderCurrentItems(Graphics2D g2,LinkedList<Item> items, int scale,int leftBuffer, int topBuffer) {
		if(drawItems == false)
			return;
		for(int i = 0; i < items.size(); i++) {
			g2.drawImage(items.get(i).sprite.image, items.get(i).hitbox.x , items.get(i).hitbox.y + topBuffer,items.get(i).sprite.image.getWidth()*scale,items.get(i).sprite.image.getHeight()*scale, null,null);
		}
	}

}
