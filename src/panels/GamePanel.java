package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Enemy;
import entity.Entity.Direction;
import entity.Player;
import events.Event;
import events.PlayerMovementEvent;
import main.Animation;
import tile.Sprite;
import tile.SpriteSheet;
import tile.TileManager;
import tile.TileRenderer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,ActionListener, KeyListener{
	
	
	
	final int originalTileSize = 16; //16x16 tile 
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	final int fps = 60;
	public final int maxUIScreenCol = 0;
	public final int maxUIScreenRow = 200;
	public final int maxTileScreenCol = 16; //16 tiles horizontally
	public final int maxTileScreenRow = 11;
	public final int screenWidth = (tileSize * maxTileScreenCol) + maxUIScreenCol; //768 pixels
	public final int screenHeight = (tileSize * maxTileScreenRow) + maxUIScreenRow; //576 pixels
	boolean upHeld, downHeld, leftHeld, rightHeld;
	
	boolean isActive = false;
	Thread gameThread;
	
	TileManager tileM = new TileManager(this);
	TileRenderer tileR = new TileRenderer();
	SpriteSheet openWorldTileSheet;
	SpriteSheet greenLinkTileSheet;
	public Player player = new Player(screenWidth/2,screenHeight-tileSize*4,48,48);
	
	public LinkedList<Event> eventList = new LinkedList<Event>();
	public LinkedList<KeyEvent> keysPressed = new LinkedList<KeyEvent>();
	public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
	
		this.setFocusable(true);
		try {
			openWorldTileSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/overworldtiles.png"))), 16, 16, 128, 1,1, 8);
			greenLinkTileSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResource("/spritesheet/legendofzelda_link_sheet_green.png"))),16,16,12,14,14,3);
		}catch(Exception e) {
			
		}
		Sprite[] northWalking = new Sprite[2];
		Sprite[] eastWalking = new Sprite[2];
		Sprite[] southWalking = new Sprite[2];
		Sprite[] westWalking = new Sprite[2];
		northWalking[0] = greenLinkTileSheet.sprites.get(2);
		northWalking[1] = greenLinkTileSheet.sprites.get(6);
		player.addAnimation(Animation.AnimationType.WALK_NORTH, new Animation(northWalking));
		eastWalking[0] = greenLinkTileSheet.sprites.get(3);
		eastWalking[1] = greenLinkTileSheet.sprites.get(7);
		player.addAnimation(Animation.AnimationType.WALK_EAST, new Animation(eastWalking));

		westWalking[0] = greenLinkTileSheet.sprites.get(1);
		westWalking[1] = greenLinkTileSheet.sprites.get(5);
		player.addAnimation(Animation.AnimationType.WALK_WEST, new Animation(westWalking));
		
		southWalking[0] = greenLinkTileSheet.sprites.get(0);
		southWalking[1] = greenLinkTileSheet.sprites.get(4);
		player.addAnimation(Animation.AnimationType.WALK_SOUTH, new Animation(southWalking));
		player.setAnimation(Animation.AnimationType.WALK_SOUTH);
		startGameThread();
		setFocusable(true);  
       
        addKeyListener(this);
        for(int i =0; i < tileM.tiles.length; i++) {
			for(int j = 0; j < tileM.tiles[i].length; j++) {
				System.out.print(tileM.tiles[i][j].spriteIndex + " ");
			}
			System.out.println();
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
			
			if(delta >= 1 && isActive) {
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
		if(keysPressed.isEmpty())
			player.isMoving =false;
		else {
			
		
		switch(keysPressed.getFirst().getKeyCode()) {
		case KeyEvent.VK_W:
			eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, Direction.NORTH, 5));
			break;
		case KeyEvent.VK_S:
			eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, Direction.SOUTH, 5));
			break;
		case KeyEvent.VK_D:
			eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, Direction.EAST, 5));
			break;
		case KeyEvent.VK_A:
			eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, Direction.WEST, 5));
			break;
		
		}
		}
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
				currentEvent.resolveEvent(player, tileM, maxUIScreenCol, maxUIScreenRow);
				eventList.remove();
				break;
			default:
				break;
			
			}
		}
		if (player.isMoving) {
		    player.getAnimation().update();
		} else {
		    player.getAnimation().reset();
		}
		for(Enemy enemy : enemies) {
			// check which enemy each enemy is and resolve behavior.
		}
		// need a second for loop here to deal with potential projectiles
		
	}
public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileR.draw(g2, openWorldTileSheet,tileM, tileSize,maxTileScreenCol,maxTileScreenRow,maxUIScreenCol,maxUIScreenRow);
		g2.drawImage(
                player.getAnimation().getCurrentFrame().image,
                player.x,
                player.y,
                player.width,
                player.height,
                null
            );
		g.setColor(Color.RED);
		g.drawRect(
		    player.getRectangle().x ,
		    player.getRectangle().y ,
		    player.getRectangle().width,
		    player.getRectangle().height
		);
		for(int i = 0; i < tileM.tiles.length; i++){
			for(int j = 0; j < tileM.tiles[i].length; j++) {
				if(tileM.tiles[i][j].hasCollision) {
					g.drawRect(tileM.tiles[i][j].collisionHitbox.x, tileM.tiles[i][j].collisionHitbox.y, tileM.tiles[i][j].collisionHitbox.width, tileM.tiles[i][j].collisionHitbox.height);
				}
			}
		}
		g2.dispose();
	}
@Override
public void actionPerformed(ActionEvent e) {
	
}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyPressed(KeyEvent e) {
	int keyCode = e.getKeyCode();
	for(int i = 0; i < keysPressed.size(); i++) {
		if(keysPressed.get(i).getKeyCode() == keyCode) {
			keysPressed.remove(i);
			
		}
	}
	keysPressed.addFirst(e);

}
@Override
public void keyReleased(KeyEvent e) {
	int keyCode = e.getKeyCode();
	for(int i = 0; i < keysPressed.size(); i++) {
		if(keysPressed.get(i).getKeyCode() == keyCode) {
			keysPressed.remove(i);
			break;
			
		}
	}
	
}
}
