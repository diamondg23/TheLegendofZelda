package editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controllers.KeyboardController;
import controllers.MouseController;
import tile.Sprite;
import tile.SpriteSheet;
import tile.Tile;

public class EditorPanel extends JPanel implements Runnable{
	Thread gameThread;
	public int fps = 30;
	final int originalTileSize = 16; //16x16 tile 
	final int scale = 3;
	public final int tileSize = originalTileSize*scale;
	
	public final int maxScreenCol = 18; //18 tiles horizontally
	public final int maxScreenRow = 11;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow + (tileSize * 2); //576 pixels
	
	public SpriteSheet sheet;
	MouseController mouseController = new MouseController(this);
	KeyboardController keyboardController = new KeyboardController(this);
	Sprite currTile = null;
	int leftBuffer;
	public int upBuffer;
	
	public Tile selectedTile;
	public Sprite[][] sprites = new Sprite[12][2];
	public Tile[][] mapTiles = new Tile[11][16];
	
	public EditorPanel() {
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
			sheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/overworldtiles.png"))), 16, 16, 128, 1,1, 8);
		}catch(Exception e) {
			
		}
		EditorUI.InitializeElements(this);
		
		
		leftBuffer = EditorUI.upButton.getWidth();
		upBuffer = EditorUI.upButton.getHeight();
		
		for(int i = 0; i < sprites.length; i++) {
			for(int j = 0; j < sprites[i].length; j++) {
				sprites[i][j] = sheet.sprites.get(i*2+j);
			}
			
		}
		
		for(int i = 0; i < EditorUI.buttons.length; i++) {
			if(EditorUI.buttons[i] != null)
				this.add(EditorUI.buttons[i]);
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
			
			EditorTileRenderer.RenderTileMenu(this.sprites, (Graphics2D)g, upBuffer, tileSize);
			
			EditorTileRenderer.RenderCurrentMap((Graphics2D)g, mapTiles, tileSize, leftBuffer, upBuffer);
			if(selectedTile != null)
				EditorTileRenderer.RenderCurrentTile((Graphics2D)g, selectedTile, mouseController.x, mouseController.y, tileSize);
	
		}
		
		public void scrollDown() {
			int lastSpriteIndex = sheet.sprites.indexOf(sprites[sprites.length-1][1]);
			if(lastSpriteIndex+2 > sheet.sprites.size()) {
				return;
			}
			for(int i = 0; i < sprites.length-1; i++) {
				for(int j = 0; j < sprites[i].length;j++) {
					sprites[i][j] = sprites[i+1][j];
				}
				
			}
			
			sprites[sprites.length-1][0] =  sheet.sprites.get(lastSpriteIndex + 1);
			sprites[sprites.length-1][1] =  sheet.sprites.get(lastSpriteIndex + 2);
		}
		public void scrollUp() {
			int lastSpriteIndex = sheet.sprites.indexOf(sprites[0][0]);
			if(lastSpriteIndex - 2 <0) {
				return;
			}
			for(int i = sprites.length-1; i >0; i--) {
				for(int j = 0; j < sprites[i].length;j++) {
					sprites[i][j] = sprites[i-1][j];
				}
				
			}
			
			
			sprites[0][0] = sheet.sprites.get(lastSpriteIndex - 2);
			sprites[0][1] = sheet.sprites.get(lastSpriteIndex - 1);
		}
		public void placeTile(int col, int row) {
			selectedTile.room = EditorUI.roomButton.room;
			selectedTile.canExplode = EditorUI.explodeButton.explosion;
			selectedTile.hasCollision = EditorUI.collisionButton.collision;
			
			Tile currentTile = selectedTile;
			
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

}
