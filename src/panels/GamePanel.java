package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controllers.AudioPlayer;
import controllers.Camera;
import controllers.LevelFactory;
import controllers.TileManager;
import entity.Enemy;
import entity.Entity.EntityDirection;
import entity.Player;
import events.Event;
import events.PlayerMovementEvent;
import main.Animation;
import rooms.Cave;
import rooms.DungeonLevel;
import rooms.Level;
import rooms.OverWorldLevel;
import rooms.Shop;
import tile.Sprite;
import tile.SpriteSheet;
import tile.TileRenderer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable,ActionListener, KeyListener{
	
	
	
	public static final int TILE_SIZE = 16;

	final int fps = 60;
	public Camera camera = new Camera();
	public final int maxUIScreenCol = 0;
	public static final int UI_HEIGHT = 200;
	public final int maxTileScreenCol = 16; //16 tiles horizontally
	public final int maxTileScreenRow = 11;
	public final int baseScreenWidth =
		    (TILE_SIZE * maxTileScreenCol)
		    + maxUIScreenCol;

		public final int baseScreenHeight =
		    (TILE_SIZE * maxTileScreenRow)
		    + UI_HEIGHT/3;
		
	
		
	boolean upHeld, downHeld, leftHeld, rightHeld;
	
	boolean isActive = false;
	Thread gameThread;

	TileRenderer tileR = new TileRenderer();
	public SpriteSheet openWorldTileSheet;
	public SpriteSheet greenLinkTileSheet;
	public SpriteSheet greenLinkSwordSheet;
	public Player player =
		    new Player(
		        baseScreenWidth / 2,
		        baseScreenHeight - (TILE_SIZE * 4),
		        TILE_SIZE,
		        TILE_SIZE,
		        1000
		    );
	
	public OverWorldLevel[][] overworldMap;
	public DungeonLevel[][] Dungeon1Map;
	public DungeonLevel[][] Dungeon2Map;
	public DungeonLevel[][] Dungeon3Map;
	public DungeonLevel[][] Dungeon4Map;
	public DungeonLevel[][] Dungeon5Map;
	public DungeonLevel[][] Dungeon6Map;
	public DungeonLevel[][] Dungeon7Map;
	public DungeonLevel[][] Dungeon8Map;
	public DungeonLevel[][] Dungeon9Map;
	public HashMap<Integer, Cave> caveLevels;
	public HashMap<Integer, Shop> shopLevels;
	
	public Level currentLevel = null; 	

	
	public static LinkedList<Event> eventList = new LinkedList<Event>();
	public LinkedList<KeyEvent> keysPressed = new LinkedList<KeyEvent>();

	public GamePanel() {

		overworldMap = new OverWorldLevel[8][16];
		Dungeon1Map = new DungeonLevel[8][8];
		Dungeon2Map = new DungeonLevel[8][8];
		Dungeon3Map = new DungeonLevel[8][8];
		Dungeon4Map = new DungeonLevel[8][8];
		Dungeon5Map = new DungeonLevel[8][8];
		Dungeon6Map = new DungeonLevel[8][8];
		Dungeon7Map = new DungeonLevel[8][8];
		Dungeon8Map = new DungeonLevel[8][8];
		Dungeon9Map = new DungeonLevel[8][8];
		LevelFactory.levelInitizer(this);
		currentLevel = overworldMap[7][7];
		
		
		
		this.setPreferredSize(
			    new Dimension(
			        (int)(baseScreenWidth * camera.zoom),
			        (int)(baseScreenHeight * camera.zoom)
			    )
			);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		
	
		this.setFocusable(true);
		try {
			openWorldTileSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/overworldtiles.png"))), 16, 16, 128, 1,1, 8);
			greenLinkTileSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResource("/spritesheet/legendofzelda_link_sheet_green.png"))),16,16,12,14,14,3);
			greenLinkSwordSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/link_green_sword_sheet.png"))), 16, 16, 128, 1,1, 8);
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

        currentLevel.load();
     
        
        for(int i = 0; i < overworldMap.length; i++) {

            for(int j = 0; j < overworldMap[i].length; j++) {

                if(overworldMap[i][j] != null) {

                    OverWorldLevel north = null;
                    OverWorldLevel south = null;
                    OverWorldLevel east = null;
                    OverWorldLevel west = null;

                    if(i > 0) {
                        north = overworldMap[i - 1][j];
                    }

                    if(i < overworldMap.length - 1) {
                        south = overworldMap[i + 1][j];
                    }

                    if(j < overworldMap[i].length - 1) {
                        east = overworldMap[i][j + 1];
                    }

                    if(j > 0) {
                        west = overworldMap[i][j - 1];
                    }

                    overworldMap[i][j]
                        .setAdjacency(north, south, east, west);
                }
            }
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
		player.isMoving =false;
		player.getAnimation().isRunning = false;
		if(keysPressed.isEmpty()) {
			

		}
		else {
			
		
		switch(keysPressed.getFirst().getKeyCode()) {
		case KeyEvent.VK_W:
			player.isMoving =true;
			player.getAnimation().isRunning = true;
			GamePanel.eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, EntityDirection.NORTH, 1));
			break;
		case KeyEvent.VK_S:
			player.isMoving =true;
			player.getAnimation().isRunning = true;
			GamePanel.eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, EntityDirection.SOUTH, 1));
			break;
		case KeyEvent.VK_D:
			player.isMoving =true;
			player.getAnimation().isRunning = true;
			GamePanel.eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, EntityDirection.EAST, 1));
			break;
		case KeyEvent.VK_A:
			player.isMoving =true;
			player.getAnimation().isRunning = true;
			GamePanel.eventList.add(new PlayerMovementEvent(Event.events.PLAYERMOVEMENT, EntityDirection.WEST, 1));
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
				PlayerMovementEvent currentEvent =(PlayerMovementEvent)GamePanel.eventList.getFirst();
				currentEvent.resolveEvent(player, currentLevel.tileManager, maxUIScreenCol, UI_HEIGHT, this);
				
				eventList.remove();
				break;
			default:
				break;
			
			}
		}
		
		 player.getAnimation().update();
		 
		 //player.getAnimation().reset();
		
		
		// need a second for loop here to deal with potential projectiles
		
	}
	@Override
	public void paintComponent(Graphics g) {

	    super.paintComponent(g);

	    Graphics2D g2 = (Graphics2D) g.create();

	    g2.scale(camera.zoom, camera.zoom);
	    

	    currentLevel.draw(g2);
	    
	    g2.setColor(Color.BLUE);

	
	    g2.drawImage(
	        player.getAnimation().getCurrentFrame().image,
	        player.x,
	        player.y ,
	        player.width,
	        player.height,
	        null
	    );
	    
	    g2.setColor(Color.RED);

	    g2.drawRect(
	        player.getRectangle().x,
	        player.getRectangle().y ,
	        player.getRectangle().width,
	        player.getRectangle().height
	    );

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
