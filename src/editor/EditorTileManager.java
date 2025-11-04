package editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tile.Tile;
public class EditorTileManager {
	static Scanner scanner = new Scanner(System.in);
	
	public EditorTileManager() {
	
	}
	public static boolean SaveMap(Tile[][] tileMap) {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("res/maps"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		  int result = chooser.showOpenDialog(null);

          if (result == JFileChooser.APPROVE_OPTION) {
              File newfile = chooser.getSelectedFile();
              try {
      			
      			System.out.print(newfile.createNewFile());
      			FileWriter fileW;
      			fileW = new FileWriter(newfile.getPath());
      			BufferedWriter BW = new BufferedWriter(fileW);
      			String line = "";
      			for(int i = 0; i < tileMap.length; i++) {
      				for(int j = 0; j < tileMap[i].length;j++) {
      					line += tileMap[i][j].spriteIndex;
      					line += " ";
      				}
      				BW.write(line);
      				BW.newLine();
      				line = "";
      				
      			}
      			BW.close();
      		} catch (IOException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
          }
		if(!checkIfNull(tileMap)) {
			return false;
		}
		
		
	

	 
		
		
		
		
		return true;
	}
	private static boolean checkIfNull(Tile[][] tileMap) {
		for(int i = 0; i < tileMap.length; i++) {
			for(int j = 0; j < tileMap[i].length;j++) {
				if(tileMap[i][j] == null)
					return false;
			}
		}
		return true;
	}

}
