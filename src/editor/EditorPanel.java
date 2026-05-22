package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import components.MyButton;
import entity.Enemy;
import main.Animation;
import main.Animation.AnimationType;
import tile.Sprite;
import tile.SpriteSheet;
import tile.Tile;

public class EditorPanel extends JPanel implements Runnable{
	enum currentScrollBar{
		Tiles(),
		Enemies()
	}
	Thread gameThread;
	public int fps = 30;
	final int originalTileSize = 16; //16x16 tile 
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	
	public final int maxScreenCol = 18; //18 tiles horizontally
	public final int maxScreenRow = 11;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow + (tileSize * 2); //576 pixels
	
	public SpriteSheet TileSheet;
	public SpriteSheet EnemySheet;
	MouseController mouseController = new MouseController(this);
	KeyboardController keyboardController = new KeyboardController(this);
	Sprite currTile = null;
	int leftBuffer;
	public int upBuffer;
	public Enemy selectedEnemy;
	public Tile selectedTile;
	public Sprite[][] TileSprites = new Sprite[12][2];
	public Sprite[][] EnemySprites = new Sprite[12][2];
	public Tile[][] mapTiles = new Tile[11][16];
	public LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	EditorUI editorUI = new EditorUI();
	public currentScrollBar scrollBar;
	
	public boolean roomMenuOpen = false;
	
	public EditorPanel() {
		scrollBar = currentScrollBar.Tiles;
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addMouseListener(mouseController);
		this.addMouseMotionListener(mouseController);
		this.addKeyListener(keyboardController);
		this.setLayout(null);
		
		this.setFocusable(true);
		this.requestFocus();
		try {
			TileSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/overworldtiles.png"))), 16, 16, 128, 1,1, 8);
		}catch(Exception e) {
			
		}
		try {
			EnemySheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/previewEnemySpriteSheet.png"))), 16, 16, 27, 0,0, 1);
		}catch(Exception e) {
			
		}
		editorUI.InitializeElements(this);
		
		
		leftBuffer = editorUI.upButton.getWidth();
		upBuffer = editorUI.upButton.getHeight();
		
		for(int i = 0; i < TileSprites.length; i++) {
			for(int j = 0; j < TileSprites[i].length; j++) {
				TileSprites[i][j] = TileSheet.sprites.get(i*2+j);
			}
			
		}
		for(int i = 0; i < EnemySprites.length; i++) {
			for(int j = 0; j < EnemySprites[i].length; j++) {
				if(i*2+j > EnemySheet.sprites.size()) {
					break;
				}
				EnemySprites[i][j] = EnemySheet.sprites.get(i*2+j);
			}
			
		}
		
		for(int i = 0; i < editorUI.buttons.length; i++) {
			if(editorUI.buttons[i] != null)
				this.add(editorUI.buttons[i]);
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
		public void update(){
		}
		public void updateMouse(int x, int y) {
			if(currTile == null) {
				return;
			}
			paintComponent(this.getGraphics());
		}
		public void paintComponent(Graphics g) {
			
			
			
			super.paintComponent(g);
			
			EditorRenderer.RenderTileMenu(this.TileSprites, (Graphics2D)g, upBuffer, tileSize);
			EditorRenderer.RenderEnemyMenu(this.EnemySprites, (Graphics2D)g, upBuffer, tileSize);
			EditorRenderer.RenderCurrentMap((Graphics2D)g, mapTiles, tileSize, leftBuffer, upBuffer);
			EditorRenderer.RenderCurrentEnemies((Graphics2D)g, enemies, tileSize, leftBuffer, upBuffer);
			if(selectedTile != null)
				EditorRenderer.RenderCurrentTile((Graphics2D)g, selectedTile, mouseController.x, mouseController.y, tileSize);
			if(selectedEnemy != null)
				EditorRenderer.RenderCurrentEnemy((Graphics2D)g, selectedEnemy, mouseController.x, mouseController.y, tileSize);
			
		}
		
		public void scrollEnemiesDown() {
			int howManyMore = 0;
			int lastSpriteIndex = EnemySheet.sprites.indexOf(EnemySprites[EnemySprites.length-1][1]);
			if(lastSpriteIndex+1 >= EnemySheet.sprites.size()) {
				return;
			}
		
			
			for(int i = 0; i < EnemySprites.length-1; i++) {
				for(int j = howManyMore; j < TileSprites[i].length;j++) {
					EnemySprites[i][j] = EnemySprites[i+1][j];
				}
				
			}
		
			EnemySprites[EnemySprites.length-1][0] =  EnemySheet.sprites.get(lastSpriteIndex + 1);
			try {
				EnemySprites[EnemySprites.length-1][1] =  EnemySheet.sprites.get(lastSpriteIndex + 2);
			}catch(Exception E) {
				
			}
		
		}
		public void scrollEnemiesUp() {
			int lastSpriteIndex = EnemySheet.sprites.indexOf(EnemySprites[0][0]);
			if(lastSpriteIndex - 2 <0) {
				return;
			}
			if(lastSpriteIndex - 1 <0) {
				return;
			}
			for(int i = TileSprites.length-1; i >0; i--) {
				for(int j = 0; j < EnemySprites[i].length;j++) {
					EnemySprites[i][j] = EnemySprites[i-1][j];
				}
				
			}
			
			
			EnemySprites[0][0] = EnemySheet.sprites.get(lastSpriteIndex - 2);
			EnemySprites[0][1] = EnemySheet.sprites.get(lastSpriteIndex - 1);
		}
		public void scrollTilesDown() {
			if(scrollBar == currentScrollBar.Enemies) {
				scrollEnemiesDown();
				return;
			}
			int lastSpriteIndex = TileSheet.sprites.indexOf(TileSprites[TileSprites.length-1][1]);
			if(lastSpriteIndex+2 > TileSheet.sprites.size()) {
				return;
			}
			for(int i = 0; i < TileSprites.length-1; i++) {
				for(int j = 0; j < TileSprites[i].length;j++) {
					TileSprites[i][j] = TileSprites[i+1][j];
				}
				
			}
			
			TileSprites[TileSprites.length-1][0] =  TileSheet.sprites.get(lastSpriteIndex + 1);
			TileSprites[TileSprites.length-1][1] =  TileSheet.sprites.get(lastSpriteIndex + 2);
		}
		public void scrollTilesUp() {
			if(scrollBar == currentScrollBar.Enemies) {
				scrollEnemiesUp();
				return;
			}
			int lastSpriteIndex = TileSheet.sprites.indexOf(TileSprites[0][0]);
			if(lastSpriteIndex - 2 <0) {
				return;
			}
			for(int i = TileSprites.length-1; i >0; i--) {
				for(int j = 0; j < TileSprites[i].length;j++) {
					TileSprites[i][j] = TileSprites[i-1][j];
				}
				
			}
			
			
			TileSprites[0][0] = TileSheet.sprites.get(lastSpriteIndex - 2);
			TileSprites[0][1] = TileSheet.sprites.get(lastSpriteIndex - 1);
		}
		public void placeTile(int col, int row) {
			selectedTile.room = editorUI.roomButton.room;
			selectedTile.canExplode = editorUI.explodeButton.explosion;
			selectedTile.hasCollision = editorUI.collisionButton.collision;
			
			if(selectedTile.room != Tile.tileRooms.NOROOM)
				selectedTile.hasRoomCollision = true;
			else
				selectedTile.hasRoomCollision = false;
			Tile currentTile = new Tile(selectedTile);
			currentTile.x = col * 48;
			currentTile.y = row * 48;
			currentTile.determineCollisionHitBox();
			currentTile.determineRoomHitBox();
			currentTile.spriteIndex = selectedTile.spriteIndex;
			if(row < mapTiles.length && col <mapTiles[row].length) {
				mapTiles[row][col] = currentTile;
			}
			for(int i = 0; i < mapTiles.length; i ++) {
				for(int j = 0; j < mapTiles[i].length; j++) {
					if(mapTiles[i][j] == null) 
						System.out.print("null ");
					
					else
						System.out.print(mapTiles[i][j].spriteIndex + "," + mapTiles[i][j].room.ordinal() + " ");
				}
				System.out.println();
			}
		
		}
		public void placeEnemy(int col, int row) {
			int x = col*tileSize;
			int y = row*tileSize;
			int width = tileSize;
			int height = tileSize;
			for(int i = 0; i < enemies.size(); i ++) {
				if(enemies.get(i).x == x && enemies.get(i).y == y) {
					return;
				}
			}
			
			Enemy enemy = new Enemy(x,y,width,height,null, selectedEnemy.ID);
			
			enemy.addAnimation(Animation.AnimationType.PREVIEW, selectedEnemy.getAnimation());
			enemy.setAnimation(AnimationType.PREVIEW);
			enemies.add(enemy);
			System.out.println("ID OF NEW ENEMY IS: " + enemy.ID);
		}
		public void toggleRoomMenu() {
	        if (roomMenuOpen) {
	            closeRoomMenu();
	        } else {
	            openRoomMenu();
	        }
	    }
	    private void openRoomMenu() {

	        int buttonHeight = tileSize / 2;

	        for (int i = 0; i < Tile.tileRooms.values().length; i++) {

	            Tile.tileRooms roomType = Tile.tileRooms.values()[i];
	            String roomName = roomType.toString();

	            int x = editorUI.roomButton.getX();
	            int y = editorUI.roomButton.getY() - ((i+1) * buttonHeight);
	            int w = editorUI.roomButton.getWidth();
	            int h = buttonHeight;
	            final int index = i;
	            editorUI.roomOptions[i] = new MyButton(
	                roomName,
	                x, y, w, h,
	                () -> selectRoom(index)
	            );

	            add(editorUI.roomOptions[i]);
	        }

	        roomMenuOpen = true;
	        revalidate();
	        repaint();
	    }
	    private void selectRoom(int index) {

	        Tile.tileRooms selected = Tile.tileRooms.values()[index];

	        editorUI.roomButton.setText(selected.toString());

	        // Save it for the editor’s current tile placement
	        editorUI.roomButton.room = selected;

	        closeRoomMenu();
	    }
	    private void closeRoomMenu() {

	        for (int i = 0; i < editorUI.roomOptions.length; i++) {
	            if (editorUI.roomOptions[i] != null) {
	                remove(editorUI.roomOptions[i]);
	                editorUI.roomOptions[i] = null;
	            }
	        }

	        roomMenuOpen = false;
	        revalidate();
	        repaint();
	    }
	    public void collisionButton() {
			if(editorUI.collisionButton.getText() == "No Collision") {
				editorUI.collisionButton.setText("Collision On");
				editorUI.collisionButton.collision = true;
			}
			else {
				editorUI.collisionButton.setText("No Collision");
				editorUI.collisionButton.collision = false;
			}
		}
		public void explodeButton() {
			if(editorUI.explodeButton.getText() == "No Explosion") {
				editorUI.explodeButton.setText("Explosion on");
				editorUI.explodeButton.explosion = true;
			}
			else {
				editorUI.explodeButton.setText("No Explosion");
				editorUI.explodeButton.explosion = false;
			}
		}
	}





