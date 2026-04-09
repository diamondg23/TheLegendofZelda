package main;

import javax.swing.JFrame;
 
import editor.EditorPanel;
import panels.GamePanel;
import panels.MainPanel;

public class Main {
	
	public static void main(String[] args) {
		
		MainPanel mainPanel = new MainPanel();
		MyFrame frame = new MyFrame();
		frame.add(mainPanel); 
		
		frame.pack();
		
		
		
	}
}
