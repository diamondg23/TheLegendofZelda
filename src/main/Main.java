package main;

import javax.swing.JFrame;

import editor.EditorPanel;

public class Main {
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		GamePanel gamepanel = new GamePanel();
		
		frame.add(gamepanel); 
		
		frame.pack();
		
		
		gamepanel.startGameThread();
	}
}
