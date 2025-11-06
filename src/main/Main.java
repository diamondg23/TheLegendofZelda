package main;

import javax.swing.JFrame;

import editor.EditorPanel;

public class Main {
	
	public static void main(String[] args) {
		
		GamePanel gamepanel = new GamePanel();
		MyFrame frame = new MyFrame();
		frame.add(gamepanel); 
		
		//frame.pack();
		
		
		gamepanel.startGameThread();
	}
}
