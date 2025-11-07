package editor;

import main.MyButton;
import main.MyLabel;


public class EditorUI {
	static EditorPanel panel;
	public static MyButton downButton;
	public static MyButton upButton;
	public static MyButton[] buttons = new MyButton[20];
	public static MyButton roomButton;
	public static MyButton[] tempRoomButtons = new MyButton[10];
	public static MyButton collisionButton;
	public static MyButton explodeButton;
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
		roomButton.setLocation(panel.screenWidth/5, panel.screenHeight- roomButton.getHeight());
		roomButton.setFocusable(false);
		roomButton.setText("No Room");
		
		collisionButton = new MyButton(panel);
		collisionButton.setSize(panel.tileSize*3, panel.screenHeight/20);
		collisionButton.setLocation(panel.screenWidth/2 - panel.screenWidth/20, panel.screenHeight - collisionButton.getHeight());
		collisionButton.setFocusable(false);
		collisionButton.setText("No Collision");
		
		explodeButton = new MyButton(panel);
		explodeButton.setSize(panel.tileSize*3, panel.screenHeight/20);
		explodeButton.setLocation(panel.screenWidth/2 + panel.screenWidth/5, panel.screenHeight - explodeButton.getHeight());
		explodeButton.setFocusable(false);
		explodeButton.setText("No Explode");

		
		buttons[0] = downButton;
		buttons[1] = upButton;
		buttons[2] = roomButton;
		buttons[3] = collisionButton;
		buttons[4] = explodeButton;
	}
}
