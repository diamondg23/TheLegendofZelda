package controllers;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import panels.GamePanel;

public class KeyboardController extends Component implements KeyListener {
	private GamePanel panel;
	public KeyboardController(GamePanel panel) {
	this.panel = panel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
			
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
