package tile;

import java.awt.Graphics2D;

import panels.GamePanel;

public class TileRenderer {

	public TileRenderer() {
		
	}
	
    public void draw(Graphics2D g2, SpriteSheet sheet, TileManager tileM, 
                     int tileSize, int cols, int rows, int offsetX, int offsetY) {

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {

                Tile tile = tileM.tiles[col][row];
                int index = tile.spriteIndex;

                g2.drawImage(
                    sheet.sprites.get(index).image,
                    col * tileSize + offsetX,
                    row * tileSize + offsetY,
                    tileSize,
                    tileSize,
                    null
                );
            }
        }
    }
}
