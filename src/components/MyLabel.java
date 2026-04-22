package components;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyLabel extends JLabel{

	int x;
    int y;
    int width;
    int height;
    public MyLabel(int x, int y, int width, int height, String text){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setBounds(x, y, width, height);
        this.setText(text);
        this.setLayout(null);
    }
    public MyLabel(int x, int y, int width, int height, ImageIcon img){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setBounds(x, y, width, height);
        this.setIcon(img);
        this.setLayout(null);
        
    }
    public MyLabel(int x, int y, int width, int height){
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.setVisible(true);
        this.setBounds(x, y, width, height);
        
        this.setLayout(null);
    }
	public MyLabel() {
		this.setVisible(false);
		this.setLayout(null);
		
	}

}
