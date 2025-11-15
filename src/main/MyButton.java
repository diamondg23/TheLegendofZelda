package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import editor.EditorPanel;
import editor.EditorUI;
import tile.Tile;
import tile.Tile.rooms;

public class MyButton extends JButton implements ActionListener{

	

	    private static int buttonCount = 0;
	    private int buttonID;
	    private EditorPanel panel;
	    public boolean isPressed = false;
	    public rooms room = Tile.rooms.NOROOM;
	    public boolean collision = false;
	    public boolean explosion = false;
	    public MyButton(String text, int x, int y, int width, int height, EditorPanel panel){
	    	this.panel = panel;
	        this.setText(text);
	        this.setBounds(x, y, width, height);
	        this.addActionListener(this);

	
	       
	        this.setVisible(true);
	        buttonCount++;
	        buttonID = buttonCount;
	        
	        
	    }

	    public MyButton(EditorPanel panel) {
	    	this.panel = panel;
	    	this.addActionListener(this);
	    	   this.setVisible(true);
	    	    buttonCount++;
	    	    buttonID = buttonCount;
		}

		@Override
	    public void actionPerformed(ActionEvent e) {
			
			switch(buttonID) {
			case 1:
				scrollDown();
				break;
			case 2:
				scrollUp();
				break;
			case 3:
				roomDropDownMenu();
				break;
			case 4:
				collisionButton();
				break;
			case 5:
				explodeButton();
			}
	    }
		public void collisionButton() {
			if(this.getText() == "No Collision") {
				this.setText("Collision On");
				this.collision = true;
			}
			else {
				this.setText("No Collision");
				this.collision = false;
			}
		}
		public void explodeButton() {
			if(this.getText() == "No Explosion") {
				this.setText("Explosion on");
				this.explosion = true;
			}
			else {
				this.setText("No Explosion");
				this.explosion = false;
			}
		}
		public void roomDropDownMenu() {
			
			if(isPressed) {
				for(int i = 0; i < EditorUI.tempRoomButtons.length; i++) {
					if(EditorUI.tempRoomButtons[i] != null)
						panel.remove(EditorUI.tempRoomButtons[i]);
					EditorUI.tempRoomButtons[i] = null;
					
				}	
				
				panel.repaint();
				panel.getParent().revalidate();
				panel.getParent().repaint();
				isPressed = false;
				return;
			}
			System.out.println("Room button pressed");
			isPressed = true;
			
			for(int i = 0; i < Tile.rooms.values().length; i++) {
				String room = findRoom(i);
				
				EditorUI.tempRoomButtons[i] = new MyButton(room,EditorUI.roomButton.getX()  , ((panel.screenHeight - EditorUI.roomButton.getHeight()) - (i+1) * panel.tileSize/2), EditorUI.roomButton.getWidth(), panel.tileSize/2, panel);
				EditorUI.tempRoomButtons[i].setToolTipText(room);
				EditorUI.tempRoomButtons[i].setVisible(true);
				EditorUI.tempRoomButtons[i].room = Tile.rooms.values()[i];
				EditorUI.tempRoomButtons[i].addActionListener(e -> {
					MyButton clicked = (MyButton) e.getSource();
				    MyButton mainB = EditorUI.roomButton;
				    	mainB.setText(clicked.getText());
				    	mainB.setBackground(clicked.getBackground());
				    	mainB.setForeground(clicked.getForeground());
				    	mainB.room = clicked.room;
				    	mainB.setFont(clicked.getFont());
				    	mainB.setIcon(clicked.getIcon());
				    
				        // Update the main room button’s text
				        roomDropDownMenu();
				    });
			}
			for(int i = 0; i < EditorUI.tempRoomButtons.length; i++) {
				if(EditorUI.tempRoomButtons[i] != null) {
					panel.add(EditorUI.tempRoomButtons[i]);
					System.out.println(EditorUI.tempRoomButtons[i].getText());
				}
			}
			
			panel.repaint();
			panel.getParent().revalidate();
			panel.getParent().repaint();
		}
		public String findRoom(int i) {
			switch(i) {
			case 0:
				return "No Room";
			case 1:
				return "Old Man Heart Room";
			case 2:
				return "Old Woman Map Room";
			case 3:
				return "Old Woman Shop Room";
			case 4:
				return "Secret Punishment Room";
			case 5:
				return "Secret Reward Room";
			case 6:
				return "Shop Room";
			case 7:
				return "Level 1";
			case 8:
				return "Level 2";
			case 9:
				return "Level 3";
			case 10:
				return "Level 4";
			case 11:
				return "Level 5";
			case 12:
				return "Level 6";
			case 13:
				return "Level 7";
			case 14:
				return "Level 8";
			case 15:
				return "Level 9";
					
			}
			return "Error";
		}
		public void scrollDown() {
			int lastSpriteIndex = panel.sheet.sprites.indexOf(panel.sprites[panel.sprites.length-1][1]);
			if(lastSpriteIndex+2 > panel.sheet.sprites.size()) {
				return;
			}
			for(int i = 0; i < panel.sprites.length-1; i++) {
				for(int j = 0; j < panel.sprites[i].length;j++) {
					panel.sprites[i][j] = panel.sprites[i+1][j];
				}
				
			}
			
			panel.sprites[panel.sprites.length-1][0] =  panel.sheet.sprites.get(lastSpriteIndex + 1);
			panel.sprites[panel.sprites.length-1][1] =  panel.sheet.sprites.get(lastSpriteIndex + 2);
		}
		public void scrollUp() {
			int lastSpriteIndex = panel.sheet.sprites.indexOf(panel.sprites[0][0]);
			if(lastSpriteIndex - 2 <0) {
				return;
			}
			for(int i = panel.sprites.length-1; i >0; i--) {
				for(int j = 0; j < panel.sprites[i].length;j++) {
					panel.sprites[i][j] = panel.sprites[i-1][j];
				}
				
			}
			
			
			panel.sprites[0][0] = panel.sheet.sprites.get(lastSpriteIndex - 2);
			panel.sprites[0][1] = panel.sheet.sprites.get(lastSpriteIndex - 1);
		
}
}
