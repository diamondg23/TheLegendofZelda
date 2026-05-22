package editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tile.Sprite;
import tile.Tile;
import tile.TileData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class EditorTileManager {
	static Scanner scanner = new Scanner(System.in);

	public EditorTileManager() {
	
	}
	public static TileData[][] loadMap(File file){
		  try {
		        Gson gson = new Gson();

		        FileReader reader = new FileReader(file);

		        TileData[][] tileData = gson.fromJson(reader, TileData[][].class);

		        reader.close();

		        return tileData;

		    } catch(Exception e) {
		        e.printStackTrace();
		    }

		    return null;
	}

	public static boolean SaveMap(Tile[][] tileMap) {
		TileData[][] tileData = new TileData[tileMap.length][tileMap[0].length];
		if(!checkIfNull(tileMap)) {
			return false;
		}
		else {
			
			for(int i = 0; i < tileData.length; i++) {
				for(int j = 0; j < tileData[i].length; j++) {
					Tile tile = tileMap[i][j];
					tileData[i][j] = new TileData(tile.spriteIndex,tile.room.ordinal(),tile.hasCollision,tile.hasRoomCollision, tile.canExplode, tile.direction.ordinal(),tile.isFlammable);
					
				}
			}
		}
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("res"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		  int result = chooser.showOpenDialog(null);

          if (result == JFileChooser.APPROVE_OPTION) {
              File file = chooser.getSelectedFile();
              try {
                  Gson gson = new GsonBuilder().setPrettyPrinting().create();

                  FileWriter writer = new FileWriter(file);
                  gson.toJson(tileData, writer);
                  writer.close();

                  return true;

              } catch (IOException e) {
                  e.printStackTrace();
                  return false;
              }
          }

		
		return true;
	}
	public static void convertToTile(TileData[][] tiledata, Tile[][]tiles, EditorPanel gp){
		for(int i = 0; i < tiledata.length; i++) {
			for(int j = 0; j < tiledata[i].length; j++) {
				TileData tileD = tiledata[i][j];
				int x = j*48;
				int y = i*48;
				tiles[i][j] = new Tile(new Sprite(null), tileD.room,tileD.hasCollision,tileD.hasRoomCollision, tileD.canExplode, tileD.direction,tileD.flammable,x,y);
				tiles[i][j].spriteIndex = tileD.spriteIndex;
				tiles[i][j].sprite = gp.TileSheet.sprites.get(tileD.spriteIndex);
			}
		}
		
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
