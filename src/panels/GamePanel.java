package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import events.Event;
import events.PlayerMovementEvent;
import tile.Sprite;
import tile.SpriteSheet;
import tile.TileManager;
import tile.TileRenderer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,ActionListener{
	
	
	
	final int originalTileSize = 16; //16x16 tile 
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	final int fps = 30;
	public final int maxUIScreenCol = 0;
	public final int maxUIScreenRow = 200;
	public final int maxTileScreenCol = 16; //16 tiles horizontally
	public final int maxTileScreenRow = 11;
	public final int screenWidth = (tileSize * maxTileScreenCol) + maxUIScreenCol; //768 pixels
	public final int screenHeight = (tileSize * maxTileScreenRow) + maxUIScreenRow; //576 pixels
	
	Thread gameThread;
	
	TileManager tileM = new TileManager(this);
	TileRenderer tileR = new TileRenderer();
	SpriteSheet sheet;
	public Player player = new Player(200,200,16,16);
	
	public LinkedList<Event> eventList = new LinkedList<Event>();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
	
		this.setFocusable(true);
		try {
			sheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/overworldtiles.png"))), 16, 16, 128, 1,1, 8);
		}catch(Exception e) {
			
		}
		
	}
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	@Override
	public void run() {
		double drawInterval = 1000000000/fps;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		long timer = 0;
		int drawCount = 0;
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta +=(currentTime - lastTime)/drawInterval;
			timer +=(currentTime - lastTime);
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				
				drawCount = 0;
				timer = 0;
			}
		
	}

}
	public void update() {

		while(eventList.size() != 0) {
			switch(eventList.getFirst().getEvent()) {
			case BOMBPLACED:
				break;
			case ENEMYMOVEMENT:
				break;
			case MISCMOVEMENT:
				break;
			case PLAYERMOVEMENT:
				// safely type cast it to player movement event
				PlayerMovementEvent currentEvent =(PlayerMovementEvent)eventList.getFirst();
				currentEvent.resolveEvent(player);
				eventList.remove();
				break;
			default:
				break;
			
			}
		}
		
	}
public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileR.draw(g2, sheet,tileM, tileSize,maxTileScreenCol,maxTileScreenRow,maxUIScreenCol,maxUIScreenRow);
		g2.dispose();
	}
@Override
public void actionPerformed(ActionEvent e) {
	
}
}
