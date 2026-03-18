package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import editor.EditorPanel;
import editor.EditorUI;
import tile.Tile;
import tile.Tile.tileRooms;

public class MyButton extends JButton implements ActionListener{
	    public boolean isPressed = false;
	    public tileRooms room = Tile.tileRooms.NOROOM;
	    public boolean collision = false;
	    public boolean explosion = false;
	   	    public MyButton(String text, int x, int y, int width, int height, Runnable action){
	    	this.addActionListener(e -> action.run());
	        this.setText(text);
	        this.setBounds(x, y, width, height);
	
	        this.setVisible(true);
	        
	        
	        
	    }
	    public MyButton(String text, Runnable action) {
	    	super(text);
	    	this.addActionListener(e -> action.run());
	    }

		

		@Override
	    public void actionPerformed(ActionEvent e) {
			
	    }
		
		
	
}
