package tile;

import java.awt.Graphics2D;

import main.GamePanel;

public class TileRenderer {
	GamePanel gp;
	public Tile[] tile;
	int amountOfTiles = 6;

	public TileRenderer(GamePanel gp) {
		this.gp = gp;
	}
	public void draw(Graphics2D g2, SpriteSheet sheet, TileManager tileM) {
		
		int screenCol = 0;
		int screenRow = 0;
		int tileIndex = 0;

		while(screenCol < gp.maxTileScreenCol && screenRow < gp.maxTileScreenRow) {
			
			 tileIndex = tileM.tiles[screenCol][screenRow].spriteIndex;
		
				g2.drawImage(sheet.sprites.get(tileIndex).image, (screenCol*gp.tileSize) + gp.maxUIScreenCol , (screenRow*gp.tileSize) + gp.maxUIScreenRow ,gp.tileSize,gp.tileSize,null);
			
			
				
			screenCol++;
			if(screenCol == gp.maxTileScreenCol) { 
				screenCol = 0;
				
				screenRow++;
				
			}
			
		}
	}

}
