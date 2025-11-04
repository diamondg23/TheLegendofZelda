package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public int[][] mapTileNum;
	public Tile[][] tiles;
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		
		tiles = new Tile[gp.maxScreenCol][gp.maxScreenRow];
		
		loadMap("/maps/test6.txt");
	}
	public void loadMap(String map) {
		System.out.println(map);
		try {
			InputStream is = getClass().getResourceAsStream(map); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				String line = br.readLine();
			
				while(col < gp.maxScreenCol) {
					String intTiles[] = line.split(" ");
					Tile CurrTile = new Tile(null);
					tiles[col][row] = CurrTile.resolveBehavior(intTiles[col]);
					col++;
					
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	public void collisionCheck(int col, int row) {
		switch(tiles[col][row].spriteIndex) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
			case 14:
				break;
			case 15:
				break;
			case 16:
				break;
			case 17:
				break;
			case 18:
				break;
			case 19:
				break;
			case 20:
				break;
			case 21:
				break;
			case 22:
				break;
			case 23:
				break;
			case 24:
				break;
			case 25:
				break;
			case 26:
				break;
			case 27:
				break;
			case 28:
				break;
			case 29:
				break;
		}
	}
}
