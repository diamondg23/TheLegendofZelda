package editor;

import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;

import editor.EditorPanel.currentScrollBar;

public class KeyboardController extends Component implements KeyListener {
	EditorPanel panel;
	public KeyboardController(EditorPanel panel) {
		this.panel = panel;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			panel.selectedTile = null;
			panel.selectedEnemy = null;
			System.out.println("Escape pressed");
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			panel.scrollTilesDown();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			panel.scrollTilesUp();
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			System.out.println( EditorTileManager.SaveMap(panel.mapTiles));
		}
		if(e.getKeyChar() == KeyEvent.VK_2) {
			
		}
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			panel.currTile = null;
		}
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
		
			if(panel.scrollBar == currentScrollBar.Enemies)
			{
				panel.scrollBar = currentScrollBar.Tiles;
				EditorRenderer.scrollBar = currentScrollBar.Tiles;
				System.out.println("Tiles showing");
			}
			else {
				panel.scrollBar = currentScrollBar.Enemies;
				EditorRenderer.scrollBar = currentScrollBar.Enemies;
				System.out.println("Enemies showing");
			}
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	

}
