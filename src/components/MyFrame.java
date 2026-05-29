package components;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
public class MyFrame extends JFrame {

      
  
    public MyFrame()  {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("2D Game");
        this.setResizable(true);
		
        this.setVisible(true);
        this.setLocationRelativeTo(null);
}

  
}
