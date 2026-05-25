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
			LevelWriter.save(panel);
			System.out.println("saved");
		}
		if(e.getKeyChar() == KeyEvent.VK_2) {
			
		}
		if(e.getKeyChar() == KeyEvent.VK_ESCAPE) {
			panel.currTile = null;
		}
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
		
			if(panel.scrollBar == currentScrollBar.ENEMIES)
			{
				panel.scrollBar = currentScrollBar.ITEMS;
				EditorRenderer.scrollBar = currentScrollBar.ITEMS;
				System.out.println("Items showing");
			}
			else if(panel.scrollBar == currentScrollBar.ITEMS){
				panel.scrollBar = currentScrollBar.TILES;
				EditorRenderer.scrollBar = currentScrollBar.TILES;
				System.out.println("Tiles showing");
			}
			else {
				panel.scrollBar = currentScrollBar.ENEMIES;
				EditorRenderer.scrollBar = currentScrollBar.ENEMIES;
				System.out.println("Enemies showing");
			}
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	

}
