package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MyButton extends JButton implements ActionListener{
	 String text;
	    int x;
	    int y;
	    int width;
	    int height;
	    public static int buttonID;
	    public MyButton(String text, int x, int y, int width, int height){
	        this.setText(text);
	        this.setBounds(x, y, width, height);
	        this.addActionListener(this);
	        this.x = x;
	        this.y = y;
	        this.width = width;
	        this.height = height;
	        this.text = text;
	        this.setVisible(true);
	        buttonID++;
	        
	    }

	    public MyButton() {
	    	this.addActionListener(this);
	    	   this.setVisible(true);
	    	    buttonID++;
		}

		@Override
	    public void actionPerformed(ActionEvent e) {
			
	    }
}
