package tile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class SpriteSheet {
	Sprite sheet;
	public LinkedList<Sprite> sprites = new LinkedList<Sprite>();
	int[][] pixels = new int[16][16];
	public SpriteSheet(Sprite sprite, int spriteWidth, int spriteHeight, int numSprites, int spacingX, int spacingY, int numOfRows) {
		this.sheet = sprite;
		populateList( spriteWidth,  spriteHeight,  numSprites,  spacingX,  spacingY,  numOfRows);
	}
	public void setSheet(Sprite sheet) {
		this.sheet = sheet;
	}
	public void populateList(int spriteWidth, int spriteHeight, int numSprites, int spacingX, int spacingY, int numOfRows) {
		int currentX = 0;
		int currentY = 0; 
		
		for(int i = 0; i < numOfRows; i++) {
			for(int j = 0; j < numSprites/numOfRows; j++) {
				sprites.add(new Sprite(new BufferedImage(spriteWidth, spriteHeight, BufferedImage.TYPE_INT_ARGB)));
				assignSprite(currentY, currentX, spriteHeight,spriteWidth);
				currentX += spriteWidth + spacingX;
				
				
				//sprites.getLast().image.setRGB(0, 0, spriteWidth, spriteHeight, pixels, 0, spriteWidth);
				for (int y = 0; y < spriteHeight; y++) {
			        for (int x = 0; x < spriteWidth; x++) {
			        	sprites.getLast().image.setRGB(x, y, pixels[y][x]);
			        }
			    }
				 
			}
			currentY += spriteHeight + spacingY;
			currentX = 0;
				
		}
		System.out.println(sprites.size());
		

	}
	
	public void assignSprite(int currentY, int currentX, int spriteHeight, int spriteWidth) {
		
		int counter = 0;
		for(int i = 0 ; i < spriteHeight; i++ ) {
			for(int j = 0; j < spriteWidth; j++) {
				counter++;
				pixels[i][j] = sheet.image.getRGB(j+currentX, i+currentY);
				//pixels[j+(i*10)] = sheet.image.getRGB(j+currentX, i+currentY);
				
			}
		}
		
	}
	

}
