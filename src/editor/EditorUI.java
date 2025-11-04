package editor;

import main.MyButton;
import main.MyLabel;


public class EditorUI {
	static EditorPanel panel;
	public static MyButton downButton;
	public static MyButton upButton;
	public static MyButton[] buttons = new MyButton[20];
	public static MyButton roomButton;
	public static MyButton[] tempButtons = new MyButton[10];
	
	public static void InitializeElements(EditorPanel Epanel) {
		panel = Epanel;
		
		downButton = new MyButton(panel);
		downButton.setSize(panel.tileSize*2, panel.screenHeight/30);
		downButton.setLocation(0, panel.screenHeight-downButton.getHeight());
		upButton = new MyButton(panel);
		upButton.setSize(panel.tileSize*2, panel.screenHeight/30);
		upButton.setLocation(0, 0 );
		downButton.setFocusable(false);
		upButton.setFocusable(false);
		roomButton = new MyButton(panel);
		roomButton.setSize(panel.tileSize*3, panel.screenHeight/20);
		roomButton.setLocation(panel.screenWidth/3, panel.screenHeight- roomButton.getHeight());
		roomButton.setFocusable(false);
		roomButton.setText("No Room");
		buttons[0] = downButton;
		buttons[1] = upButton;
		buttons[2] = roomButton;
	}
}
