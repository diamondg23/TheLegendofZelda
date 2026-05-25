package editor;

import components.MyButton;
import components.MyLabel;
import tile.Tile;


public class EditorUI {
	static EditorPanel panel;
	public MyButton downButton;
	public MyButton upButton;
	public MyButton[] buttons = new MyButton[20];
	public MyButton roomButton;
	public MyButton[] roomOptions = new MyButton[Tile.tileRooms.values().length];
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
			    () -> Epanel.scrollTilesDown()
			);
		upButton = new MyButton(
			    "",
			    0,
			    0,
			    panel.tileSize * 2,
			    panel.screenHeight / 30,
			    () -> Epanel.scrollTilesUp()
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

		collisionButton = new MyButton(
			    "No Collision",
			    panel.screenWidth/2 - panel.screenWidth/20,
			    panel.screenHeight - panel.screenHeight / 20,
			    panel.tileSize*3,
			    panel.screenHeight/20,
			    () -> Epanel.collisionButton()
			);
		collisionButton.setFocusable(false);
		
		explodeButton = new MyButton(
				"No Explosion",
				 panel.screenWidth/2 + panel.screenWidth/5,
				 panel.screenHeight - panel.screenHeight / 20,
			     panel.tileSize*3,
				 panel.screenHeight/20,
			     () -> Epanel.explodeButton()
					);
		explodeButton.setFocusable(false);
		
		buttons[0] = downButton;
		buttons[1] = upButton;
		buttons[2] = roomButton;
		buttons[3] = collisionButton;
		buttons[4] = explodeButton;
	}
	public void disableTileButtons() {
		buttons[2].setEnabled(false);
		buttons[3].setEnabled(false);
		buttons[4].setEnabled(false);
		buttons[2].setVisible(false);
		buttons[3].setVisible(false);
		buttons[4].setVisible(false);
	}
	public void enableTileButtons() {
		buttons[2].setEnabled(true);
		buttons[3].setEnabled(true);
		buttons[4].setEnabled(true);
		buttons[2].setVisible(true);
		buttons[3].setVisible(true);
		buttons[4].setVisible(true);
	}
}
