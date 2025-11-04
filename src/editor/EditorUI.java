package editor;

import main.MyButton;
import main.MyLabel;


public class EditorUI {
	static EditorPanel panel;
	public static MyButton downButton;
	public static MyButton upButton;
	
	
	public static void InitializeElements(EditorPanel Epanel) {
		panel = Epanel;
		
		downButton = new MyButton();
		downButton.setSize(panel.tileSize*2, panel.screenHeight/30);
		downButton.setLocation(0, panel.screenHeight-downButton.getHeight());
		upButton = new MyButton();
		upButton.setSize(panel.tileSize*2, panel.screenHeight/30);
		upButton.setLocation(0, 0 );
		downButton.setFocusable(false);
		upButton.setFocusable(false);
	}
}
