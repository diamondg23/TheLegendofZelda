package editor;

import java.io.File;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import components.MyFrame;
import controllers.AudioPlayer;
import panels.GamePanel;
import tile.Sprite;
import tile.Tile;
import tile.TileData;

public class Main {
	public static AudioPlayer music = new AudioPlayer();
	public static void main(String[] args) {
		
		EditorPanel gamepanel = new EditorPanel();
		MyFrame frame = new MyFrame();
		
		music.playMusicLoop("/music/overworldmusic.wav");
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu viewMenu = new JMenu("View");

		JMenuItem openItem = new JMenuItem("Open Map");
		JMenuItem saveItem = new JMenuItem("Save Map");
		JCheckBoxMenuItem showCollision = new JCheckBoxMenuItem("Show Collision");
		JCheckBoxMenuItem showRoom = new JCheckBoxMenuItem("Show Room");
		JCheckBoxMenuItem showEnemies = new JCheckBoxMenuItem("Show Enemies");
		JCheckBoxMenuItem showTiles = new JCheckBoxMenuItem("Show Tiles");

		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		viewMenu.add(showCollision);
		viewMenu.add(showRoom);
		viewMenu.add(showEnemies);
		viewMenu.add(showTiles);
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);

		frame.setJMenuBar(menuBar);
		frame.add(gamepanel); 
		openItem.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setCurrentDirectory(new File("res/maps"));
		    
		    int result = chooser.showOpenDialog(frame);

		    if(result == JFileChooser.APPROVE_OPTION) {
		        File file = chooser.getSelectedFile();

		        // load your map here
		        TileData[][] tiles  = EditorTileManager.loadMap(file);
		        EditorTileManager.convertToTile(tiles, gamepanel.mapTiles, gamepanel);
		        
		        System.out.println(file.getAbsolutePath());
		    }
		});
		saveItem.addActionListener(e -> {
			System.out.println( EditorTileManager.SaveMap(gamepanel.mapTiles));
		});
		showCollision.addActionListener(e -> {
		    EditorRenderer.drawCollision = showCollision.isSelected();
		    
		});
		showRoom.addActionListener(e ->{
			   EditorRenderer.drawRoom = showRoom.isSelected();
			
		});
		showEnemies.addActionListener(e ->{
			   EditorRenderer.drawEnemies = showEnemies.isSelected();
			
		});
		showTiles.addActionListener(e ->{
			   EditorRenderer.drawTiles = showTiles.isSelected();
			
		});
		frame.pack();	
		
		
		gamepanel.startGameThread();
	}

	
}