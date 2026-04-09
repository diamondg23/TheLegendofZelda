package editor;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import main.MyFrame;
import panels.GamePanel;
import tile.Sprite;
import tile.Tile;
import tile.TileData;

public class Main {
	
	public static void main(String[] args) {
		
		EditorPanel gamepanel = new EditorPanel();
		MyFrame frame = new MyFrame();
		

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		JMenuItem openItem = new JMenuItem("Open Map");
		JMenuItem saveItem = new JMenuItem("Save Map");

		fileMenu.add(openItem);
		fileMenu.add(saveItem);

		menuBar.add(fileMenu);

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
		
		
		frame.pack();	
		
		
		gamepanel.startGameThread();
	}

	
}