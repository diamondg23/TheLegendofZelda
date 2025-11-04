package editor;

import main.GamePanel;
import main.MyFrame;

public class Main {
	
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		EditorPanel gamepanel = new EditorPanel();
		
		frame.add(gamepanel); 
		
		frame.pack();	
		
		
		gamepanel.startGameThread();
	}
}