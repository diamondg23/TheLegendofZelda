package editor;

import main.MyButton;
import main.MyLabel;
import tile.Tile;


public class EditorUI {
	static EditorPanel panel;
	public MyButton downButton;
	public MyButton upButton;
	public MyButton[] buttons = new MyButton[20];
	public MyButton roomButton;
	public MyButton[] roomOptions = new MyButton[Tile.rooms.values().length];
	public MyButton collisionButton;
	public MyButton explodeButton;
	public void InitializeElements(EditorPanel Epanel) {
		panel = Epanel;
		
		downButton = new MyButton(
			    "",
			    0,
			    panel.screenHeight - (panel.screenHeight / 30),
			    panel.tileSize * 2,
			    panel.screenHeight / 30,
			    () -> Epanel.scrollDown()
			);
		upButton = new MyButton(
			    "",
			    0,
			    0,
			    panel.tileSize * 2,
			    panel.screenHeight / 30,
			    () -> Epanel.scrollUp()
			);
		downButton.setFocusable(false);
		upButton.setFocusable(false);
		roomButton = new MyButton(
			    "No Room",
			    panel.screenWidth/5,
			    panel.screenHeight-  panel.screenHeight / 20,
			    panel.tileSize*3,
			    panel.screenHeight/20,
			    () -> Epanel.toggleRoomMenu()
			);
	
		roomButton.setFocusable(false);

		collisionButton = new MyButton(panel);
		collisionButton.setSize(panel.tileSize*3, panel.screenHeight/20);
		collisionButton.setLocation(panel.screenWidth/2 - panel.screenWidth/20, panel.screenHeight - collisionButton.getHeight());
		collisionButton.setFocusable(false);
		collisionButton.setText("No Collision");
		
		explodeButton = new MyButton(panel);
		explodeButton.setSize(panel.tileSize*3, panel.screenHeight/20);
		explodeButton.setLocation(panel.screenWidth/2 + panel.screenWidth/5, panel.screenHeight - explodeButton.getHeight());
		explodeButton.setFocusable(false);
		explodeButton.setText("No Explosion");

		
		buttons[0] = downButton;
		buttons[1] = upButton;
		buttons[2] = roomButton;
		buttons[3] = collisionButton;
		buttons[4] = explodeButton;
	}
}
