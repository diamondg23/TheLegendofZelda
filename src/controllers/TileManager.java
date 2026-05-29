package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.gson.*;

import interfaces.Manager;
import panels.GamePanel;
import tile.Sprite;
import tile.SpriteSheet;
import tile.Tile;
import tile.TileData;

public class TileManager implements Manager{
	GamePanel gp;
	
	public Tile[][] tiles;          
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		
		tiles = new Tile[gp.maxTileScreenRow][gp.maxTileScreenCol];
		
		//loadTileMap("/maps/Starting_level.json");
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		int cols = gp.maxTileScreenCol;
		int rows = gp.maxTileScreenRow;
		int offsetX = 0;
		int offsetY = (int)(GamePanel.UI_HEIGHT / gp.camera.zoom);
		SpriteSheet sheet = gp.openWorldTileSheet;
		
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {

                Tile tile = tiles[row][col];
                int index = tile.spriteIndex;

                g2.drawImage(
                    sheet.sprites.get(index).image,
                    col * GamePanel.TILE_SIZE + offsetX,
                    row * GamePanel.TILE_SIZE  + offsetY,
                    GamePanel.TILE_SIZE ,
                    GamePanel.TILE_SIZE ,
                    null
                );
            }
        }
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++) {
				if(tiles[i][j].hasCollision) {
					g2.setColor(Color.red);
					g2.drawRect(tiles[i][j].collisionHitbox.x +  offsetX, tiles[i][j].collisionHitbox.y + offsetY, tiles[i][j].collisionHitbox.width, tiles[i][j].collisionHitbox.height);
				}
				else if(tiles[i][j].hasRoomCollision) {
					g2.setColor(Color.green);
					g2.drawRect(tiles[i][j].roomHitbox.x  +  offsetX, tiles[i][j].roomHitbox.y + offsetY, tiles[i][j].roomHitbox.width, tiles[i][j].roomHitbox.height);
				}
			}
		}
	}

	@Override
	public void load(String path) {
		Gson gson = new Gson();
		FileReader reader;
		try {
			reader = new FileReader(path);
			TileData[][] tiledata = gson.fromJson(reader, TileData[][].class);
			for(int i = 0; i < tiledata.length; i++) {
				for(int j = 0; j < tiledata[i].length; j++) {
					TileData tileD = tiledata[i][j];
					int x = j*GamePanel.TILE_SIZE;
					int y = i*GamePanel.TILE_SIZE;
					tiles[i][j] = new Tile(new Sprite(null), tileD.room, tileD.roomid,tileD.hasCollision,tileD.hasRoomCollision, tileD.canExplode, tileD.direction,tileD.flammable,x,y);
					tiles[i][j].spriteIndex = tileD.spriteIndex;
					
					tiles[i][j].sprite = gp.openWorldTileSheet.sprites.get(tileD.spriteIndex);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("No file");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void unload() {
		tiles = null;
		
		
	}



	
		
	}
	


