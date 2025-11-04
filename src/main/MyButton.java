package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import editor.EditorPanel;
import editor.EditorUI;
import tile.Tile;

public class MyButton extends JButton implements ActionListener{
	 String text;
	    int x;
	    int y;
	    int width;
	    int height;
	    private static int buttonCount = 0;
	    private int buttonID;
	    private EditorPanel panel;
	    public boolean isPressed = false;
	    public MyButton(String text, int x, int y, int width, int height, EditorPanel panel){
	    	this.panel = panel;
	        this.setText(text);
	        this.setBounds(x, y, width, height);
	        this.addActionListener(this);
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        this.text = text;
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
			}
	    }
		
		public void roomDropDownMenu() {
			if(isPressed) {
				for(int i = 0; i < EditorUI.tempButtons.length; i++) {
					if(EditorUI.tempButtons[i] != null)
						panel.remove(EditorUI.tempButtons[i]);
					EditorUI.tempButtons[i] = null;
					
				}	
				isPressed = false;
				return;
			}
			System.out.println("Room button pressed");
			isPressed = true;
			for(int i = 0; i < Tile.rooms.values().length; i++) {
				String room = findRoom(i);
				System.out.println(room);
				EditorUI.tempButtons[i] = new MyButton(room,EditorUI.roomButton.getX()  , ((panel.screenHeight - EditorUI.roomButton.getHeight()) + i * panel.tileSize/2), EditorUI.roomButton.width, panel.tileSize/2, panel);
				
				EditorUI.tempButtons[i].setVisible(true);
			}
			for(int i = 0; i < EditorUI.tempButtons.length; i++) {
				if(EditorUI.tempButtons[i] != null) {
					panel.add(EditorUI.tempButtons[i]);
				}
			}
			
			panel.revalidate();
			panel.repaint();
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
