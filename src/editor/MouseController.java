package editor;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panels.GamePanel;
import tile.Sprite;
import tile.Tile;
import entity.Enemy;
import entity.Item;
import main.Animation;
import main.Animation.AnimationType;
public class MouseController implements MouseListener, MouseMotionListener{
	public int x;
	public int y;
	public EditorPanel panel;

	public MouseController(EditorPanel panel) {
		this.panel = panel;
		
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int row;
		int col;
		col = x/panel.tileSize;
		row = (y - panel.upBuffer)/panel.tileSize;
		if(MouseEvent.BUTTON1 == e.getButton() || e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK ) {
			
		
		
		System.out.println("Selected row: " + row);
		System.out.println("Selected col: " + col);
		if(col < 0 || row < 0)
			return;
		if(col >1 && row < 11) {
			System.out.println(panel.selectedTile);
			if(panel.selectedTile != null) {
				col -=2;
				
				panel.placeTile(col,row);
				return;
			}
			if(panel.selectedEnemy != null) {
				panel.placeEnemy(col,row);
			}
			if(panel.selectedItem != null) {
				panel.placeItem(col,row);
			}
			return;
		}
		if(col <= 1 && row <= 11) {
			if(panel.scrollBar == EditorPanel.currentScrollBar.TILES) {
				
			panel.selectedEnemy = null;
			panel.selectedItem = null;
		    Tile selectedTile = new Tile(panel.TileSprites[row][col]);
			selectedTile.spriteIndex = panel.TileSheet.sprites.indexOf(selectedTile.sprite);
			panel.selectedTile = selectedTile;
			}
			else if(panel.scrollBar == EditorPanel.currentScrollBar.ENEMIES){
				Enemy selectedEnemy = new Enemy(0,0,0,0,null, panel.EnemySheet.sprites.indexOf(panel.EnemySprites[row][col]));
				Sprite[] sprite = new Sprite[1];
				sprite[0] = new Sprite(panel.EnemySprites[row][col].image);
				panel.selectedTile = null;
				panel.selectedItem = null;
				selectedEnemy.addAnimation(Animation.AnimationType.PREVIEW, new Animation(sprite));
				selectedEnemy.setAnimation(AnimationType.PREVIEW);
				panel.selectedEnemy = selectedEnemy;
			}
			else {
				Item selectedItem = new Item(panel.ItemSprites[row][col], panel.itemSheetSprites.indexOf(panel.ItemSprites[row][col]));
				panel.selectedTile = null;
				panel.selectedEnemy = null;
				panel.selectedItem = selectedItem;
			}
		}
		}
		else if(MouseEvent.BUTTON2 == e.getButton() || e.getModifiersEx() == MouseEvent.BUTTON2_DOWN_MASK ) {
			System.out.println("Mouse 2 pressed");
			if(col < 0 || row < 0)
				return;
			if(col >1 && row < 11) {
				for(int i = 0; i < panel.enemies.size(); i++) {
					Enemy currEnemy = panel.enemies.get(i);
					int Erow;
					int Ecol;
					
					Ecol = currEnemy.x/panel.tileSize;
					Erow = (currEnemy.y )/panel.tileSize;
					if(row == Erow && col == Ecol) {
						panel.enemies.remove(i);
					}
				}
				for(int i = 0; i < panel.items.size(); i++) {
					Item currItem = panel.items.get(i);
					int Erow;
					int Ecol;
					
					Ecol = currItem.hitbox.x/panel.tileSize;
					Erow = (currItem.hitbox.y )/panel.tileSize;
					if(row == Erow && col == Ecol) {
						panel.items.remove(i);
					}
				}
			}
			col -=2;
			if(panel.mapTiles[row][col] == null) {
				return;
			}
			Tile currentTile = panel.mapTiles[row][col];
			
			
			
			
		}
		
		
	
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
		mouseClicked(e);
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	
		panel.updateMouse(x,y);
		
	}

}
