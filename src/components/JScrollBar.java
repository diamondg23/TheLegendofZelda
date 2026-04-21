package components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JComponent;

import interfaces.Renderable;

// This class will contain a generic list of array and extends jcomponent to be able to be added to containers.
public class JScrollBar extends JComponent{
	LinkedList<ArrayList<Renderable>> list;

	private int totalHeight; // this will be the amount of pixels high this component will be.
	private int totalWidth; // this will be the amount of pixels wide this component will be
	private int cols; //this will be how many columns the list will be able to hold (cant change this once you set it).
	int rows;
	int visibleRows; // will be the amount of total visible rows possible (defined when you create the object).
	
	int firstVisibleRow; // this will be the first visible row (as you scroll through it this will change).

	
	
	public JScrollBar(int x, int y, int widthOfElement, int heightOfElement, int cols, int visibleRows) {
		super();
	
		this.totalHeight = heightOfElement * visibleRows;
		this.totalWidth = widthOfElement * cols;
		this.cols = cols;
		this.rows = 0;
		this.visibleRows = visibleRows;
		this.setBounds(x, y, totalWidth, totalHeight);
		firstVisibleRow = 0;
	
		list = new LinkedList<>();
		setPreferredSize(new Dimension(totalWidth, totalHeight));
		
	}
	public int getCols() {
		return cols;
	}
	public void addRow(ArrayList<Renderable> array) {
		
		list.add(array);
		rows++;
	
	}
	// helper method to get the last visible row
	private int getLastVisibleRow() {
	    return Math.min(firstVisibleRow + visibleRows - 1, rows - 1);
	}

	public ArrayList<Renderable> getFirst() {
		return list.getFirst();
	}
	public ArrayList<Renderable> getLast(){
		return list.getLast();
	}
	public void removeFirst(){
		list.remove();
		rows --;
	
	}
	public void removeLast() {
		list.removeLast();
		rows --;

	}
	public ArrayList<Renderable> getAtIndex(int i){
		return list.get(i);
	}
	// this returns the first element of the i arraylist in the scrollbar.
	public Renderable getElementAtIndex(int i) {
		return list.get(i).get(0);
	}
	// this returns the col element of the row arraylist in the scrollbar.
	public Renderable getElementAtIndex(int row, int col) {
		return list.get(row).get(col);
	}
	// this will scroll the bar up by one row, for example the current top row will become the second top row after.
	
	public boolean scrollUp() {
	    if (firstVisibleRow <= 0) {
	        return false; 
	    }

	    firstVisibleRow--;
	    repaint(); 
	    return true;
	}
	// this will scroll the bar down by one for example the current top row will disappear and be replaced with the second top row.
	
	public boolean scrollDown() {
	    if (firstVisibleRow + visibleRows >= rows) {
	        return false;
	    }

	    firstVisibleRow++;
	    repaint();
	    return true;
	}
	@Override
	protected void paintComponent(Graphics g) {
		
	    super.paintComponent(g);

	    if (list == null || list.isEmpty()) {
	        return;
	    }

	    Graphics2D g2 = (Graphics2D) g;

	    int topOffset = 30;     // space for up button
	    int bottomOffset = 30;  // space for down button (maybe theres a better way to do this)

	    int width = getWidth();
	    int height = getHeight();

	    int usableHeight = height - topOffset - bottomOffset;
	    

	    int rowHeight = usableHeight / Math.max(visibleRows, 1);
	    int colWidth = width / Math.max(cols, 1);

	  
	    for (int i = 0; i < visibleRows; i++) {

	        int rowIndex = firstVisibleRow + i;

	        if (rowIndex >= list.size()) {
	            break;
	        }

	        ArrayList<Renderable> row = list.get(rowIndex);

	        int y = topOffset + i * rowHeight;
	        for (int col = 0; col < cols && col < row.size(); col++) {

	            Renderable item = row.get(col);

	            int x = col * colWidth;
	            item.render(g2, x, y, rowIndex, colWidth);
	        }

	    
	        g2.drawLine(0, y, width, y);
	    }

	   
	    
	}
}
