package tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.*;
import panels.GamePanel;

public class TileManager {
	GamePanel gp;
	public int[][] mapTileNum;
	public Tile[][] tiles;          
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		
		tiles = new Tile[gp.maxTileScreenRow][gp.maxTileScreenCol];
		
		//loadTileMap("/maps/Starting_level.json");
	}
	public void loadTileMap(String map) {
		
		if(map.equals(""))
			return;
		System.out.println(map);
		   try {
			   InputStream is = getClass().getResourceAsStream(map); 
			   BufferedReader br = new BufferedReader(new InputStreamReader(is));
		        Gson gson = new Gson();
		  

		        // Read JSON back into TileSave[][]
		        TileData[][] loadData = gson.fromJson(br, TileData[][].class);
		        br.close();
		        convertToTile(loadData);
		        
		        // read the corresponding enemies_json file in order to sync up the enemy and map data
	
		    

		    } catch (Exception e) {
		        e.printStackTrace();
		       
		    }
	}
	public void convertToTile(TileData[][] tiledata){
		for(int i = 0; i < tiledata.length; i++) {
			for(int j = 0; j < tiledata[i].length; j++) {
				TileData tileD = tiledata[i][j];
				int x = j*48 + gp.maxUIScreenCol;
				int y = i*48 +gp.maxUIScreenRow;
				tiles[i][j] = new Tile(new Sprite(null), tileD.room,tileD.hasCollision,tileD.hasRoomCollision, tileD.canExplode, tileD.direction,tileD.flammable,x,y);
				tiles[i][j].spriteIndex = tileD.spriteIndex;
			}
		}
		
	}
	

}
