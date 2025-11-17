package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import panels.GamePanel;

public class TileManager {
	GamePanel gp;
	public int[][] mapTileNum;
	public Tile[][] tiles;
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		
		tiles = new Tile[gp.maxTileScreenRow][gp.maxTileScreenCol];
		
		loadMap("/maps/start.txt");
	}
	public void loadMap(String map) {
		System.out.println(map);
		try {
			InputStream is = getClass().getResourceAsStream(map); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			for(int i = 0; i < tiles.length; i ++) {
				String line = br.readLine();
				String intTiles[] = line.split(" ");
				for(int j = 0; j < intTiles.length; j++) {
					Tile currTile = new Tile(new Sprite(null));
					int x = j*48 + gp.maxUIScreenCol;
					int y = i*48 + gp.maxUIScreenRow;
					tiles[i][j] = currTile.resolveBehavior(intTiles[j], x,y);
				}
				
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}

}
