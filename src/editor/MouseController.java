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
			return;
		}
		if(col <= 1 && row <= 11) {
		    Tile selectedTile = new Tile(panel.sprites[row][col]);
			selectedTile.spriteIndex = panel.sheet.sprites.indexOf(selectedTile.sprite);
			panel.selectedTile = selectedTile;
		}
		}
		else if (MouseEvent.BUTTON2 == e.getButton()) {
			if(col < 2 || row < 0)
				return;
			if(col-2 > panel.maxScreenCol || row > panel.maxScreenRow) {
				return;
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
