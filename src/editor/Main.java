package editor;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

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
		JMenu positionMenu = new JMenu("Position");
		JMenu LevelTypeMenu = new JMenu("Level Type");
		
		JRadioButtonMenuItem OverWorld =
			    new JRadioButtonMenuItem("OverWorld");

			JRadioButtonMenuItem Level1 =
			    new JRadioButtonMenuItem("Level1");

			JRadioButtonMenuItem Cave =
			    new JRadioButtonMenuItem("Cave");
			JRadioButtonMenuItem Shop =
				    new JRadioButtonMenuItem("Shop");
			JRadioButtonMenuItem Level2 =
				    new JRadioButtonMenuItem("Level2");
			JRadioButtonMenuItem Level3 =
				    new JRadioButtonMenuItem("Level3");
			JRadioButtonMenuItem Level4 =
				    new JRadioButtonMenuItem("Level4");
			JRadioButtonMenuItem Level5 =
				    new JRadioButtonMenuItem("Level5");
			JRadioButtonMenuItem Level6 =
				    new JRadioButtonMenuItem("Level6");
			JRadioButtonMenuItem Level7 =
				    new JRadioButtonMenuItem("Level7");
			JRadioButtonMenuItem Level8 =
				    new JRadioButtonMenuItem("Level8");
			JRadioButtonMenuItem Level9 =
				    new JRadioButtonMenuItem("Level9");
			ButtonGroup LevelGroupButtons = new ButtonGroup();
			LevelGroupButtons.add(OverWorld);
			LevelGroupButtons.add(Cave);
			LevelGroupButtons.add(Shop);
			LevelGroupButtons.add(Level1);
			LevelGroupButtons.add(Level2);
			LevelGroupButtons.add(Level3);
			LevelGroupButtons.add(Level4);
			LevelGroupButtons.add(Level5);
			LevelGroupButtons.add(Level6);
			LevelGroupButtons.add(Level7);
			LevelGroupButtons.add(Level8);
			LevelGroupButtons.add(Level9);
			
			LevelTypeMenu.add(OverWorld);
			LevelTypeMenu.add(Cave);
			LevelTypeMenu.add(Shop);
			LevelTypeMenu.add(Level1);
			LevelTypeMenu.add(Level2);
			LevelTypeMenu.add(Level3);
			LevelTypeMenu.add(Level4);
			LevelTypeMenu.add(Level5);
			LevelTypeMenu.add(Level6);
			LevelTypeMenu.add(Level7);
			LevelTypeMenu.add(Level8);
			LevelTypeMenu.add(Level9);
			
		JMenuItem openItem = new JMenuItem("Open Map");
		JMenuItem saveItem = new JMenuItem("Save Map");
		JCheckBoxMenuItem showCollision = new JCheckBoxMenuItem("Show Collision");
		JCheckBoxMenuItem showRoom = new JCheckBoxMenuItem("Show Room");
		JCheckBoxMenuItem showEnemies = new JCheckBoxMenuItem("Show Enemies");
		JCheckBoxMenuItem showTiles = new JCheckBoxMenuItem("Show Tiles");

		SpinnerNumberModel XPos = new SpinnerNumberModel(
			    0,  
			    0,  
			    100, 
			    1    
			);
		JSpinner XMapPosition = new JSpinner(XPos);
		JPanel Xpanel = new JPanel();
		JLabel XLabel = new JLabel("X Pos");
		
		SpinnerNumberModel YPos = new SpinnerNumberModel(
			    0,  
			    0,  
			    100, 
			    1    
			);
		JSpinner YMapPosition = new JSpinner(YPos);
		JPanel Ypanel = new JPanel();
		JLabel YLabel = new JLabel("Y Pos");
		
		XPos.addChangeListener(e ->{
			int x = (Integer)XPos.getValue();
			gamepanel.roomXPosition = x;
			System.out.println("X position is now: " + x);
		});
		YPos.addChangeListener(e ->{
			int y = (Integer)YPos.getValue();
			gamepanel.roomYPosition = y;
			System.out.println("Y position is now: " + y);
		});
		
		Xpanel.add(XLabel);
		Xpanel.add(XMapPosition);
		Ypanel.add(YLabel);
		Ypanel.add(YMapPosition);
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		
		viewMenu.add(showCollision);
		viewMenu.add(showRoom);
		viewMenu.add(showEnemies);
		viewMenu.add(showTiles);
		
		positionMenu.add(Xpanel);
		positionMenu.add(Ypanel);
		
		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(positionMenu);
		menuBar.add(LevelTypeMenu);

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
		
		
		ActionListener LevelSelected = e -> {
		    JRadioButtonMenuItem selected =
		        (JRadioButtonMenuItem)e.getSource();

		    String mode = selected.getText();
		    gamepanel.levelSelected(mode);
		   
		};
		OverWorld.addActionListener(LevelSelected);
		Cave.addActionListener(LevelSelected);
		Shop.addActionListener(LevelSelected);
		Level1.addActionListener(LevelSelected);
		Level2.addActionListener(LevelSelected);
		Level3.addActionListener(LevelSelected);
		Level4.addActionListener(LevelSelected);
		Level5.addActionListener(LevelSelected);
		Level6.addActionListener(LevelSelected);
		Level7.addActionListener(LevelSelected);
		Level8.addActionListener(LevelSelected);
		Level9.addActionListener(LevelSelected);
		LevelGroupButtons.setSelected(OverWorld.getModel(), true);
		
		frame.pack();	
		
		
		gamepanel.startGameThread();
	}

	
}