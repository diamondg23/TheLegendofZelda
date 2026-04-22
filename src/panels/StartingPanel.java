package panels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import components.MyButton;

@SuppressWarnings("serial")
public class StartingPanel extends JPanel{

	 public StartingPanel(Runnable onStartPressed) {
	        MyButton startButton = new MyButton("Start Game" ,onStartPressed);

	        

	        add(startButton);
	    }
	 

}
