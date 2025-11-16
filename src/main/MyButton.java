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
	    public MyButton(String text, int x, int y, int width, int height, Runnable action){
	    	this.addActionListener(e -> action.run());
	        this.setText(text);
	        this.setBounds(x, y, width, height);
	
	        this.setVisible(true);
	      
	        
	        
	    }

	    public MyButton(EditorPanel panel) {
	    	this.panel = panel;
	    	this.addActionListener(this);
	    	   this.setVisible(true);
	    	    buttonCount++;
	    	    buttonID = buttonCount;
		}
	    public MyButton(String text, Runnable action) {
	    	super(text);
	    	this.addActionListener(e -> action.run());
	    }

		public MyButton(String string) {
			this.setText(string);
		}

		@Override
	    public void actionPerformed(ActionEvent e) {
			
			switch(buttonID) {
			case 1:
				//scrollDown();
				break;
			case 2:
				//scrollUp();
				break;
			case 3:
				//roomDropDownMenu();
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
		
	
}
