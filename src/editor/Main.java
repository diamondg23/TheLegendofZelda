package editor;

import main.MyFrame;
import panels.GamePanel;

public class Main {
	
	public static void main(String[] args) {
		
		EditorPanel gamepanel = new EditorPanel();
		MyFrame frame = new MyFrame();
		
		frame.add(gamepanel); 
		
		frame.pack();	
		
		
		gamepanel.startGameThread();
	}
}