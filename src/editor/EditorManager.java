package editor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import tile.Sprite;
import tile.Tile;
import tile.TileData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Item;

import entity.Enemy;
import entity.EnemyData;
import entity.ItemData;
import main.Animation;
import main.Animation.AnimationType;
import rooms.Level;
import rooms.LevelData;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class EditorManager {
	static Scanner scanner = new Scanner(System.in);

	public EditorManager() {
	
	}
	public static Level loadLevel(File file, EditorPanel gp){
		deleteCurrentData(gp);
		  try {
		        Gson gson = new Gson();
		  
		        FileReader reader = new FileReader(file);
		       LevelData data = gson.fromJson(reader, LevelData.class);
		       reader = new FileReader(data.enemyData);
		       Type enemyListType = new TypeToken<LinkedList<EnemyData>>(){}.getType();

		       LinkedList<EnemyData> enemies = gson.fromJson(reader, enemyListType);
		       
		       
		       convertToEnemy(enemies,gp);
		       reader = new FileReader(data.itemData);
		       Type itemListType = new TypeToken<LinkedList<ItemData>>(){}.getType();

		       LinkedList<ItemData> items = gson.fromJson(reader, itemListType);
		       convertToItem(items,gp);
		       reader = new FileReader(data.tileData); 
		    
		      TileData[][] tileData = gson.fromJson(reader, TileData[][].class);
		      convertToTile(tileData, gp);
		      
		      convertToLevel(data, gp);
		       
		        reader.close();

		      

		    } catch(Exception e) {
		        e.printStackTrace();
		    }

		    return null;
	}
	
	public static void deleteCurrentData(EditorPanel gp) {
		gp.enemies.clear();
		gp.items.clear();
	}

	public static void convertToLevel(LevelData data , EditorPanel gp) {
		gp.levelID = data.ID;
		gp.currentLevelType = data.type;
		gp.roomXPosition = data.x;
		gp.roomYPosition = data.y;
		
	}
	public static void convertToEnemy(LinkedList<EnemyData> enemyData, EditorPanel gp) {
		for(int i = 0; i < enemyData.size(); i++) {
			EnemyData currData = enemyData.get(i);
			Enemy currEnemy = new Enemy(currData.x,currData.y,currData.width,currData.height, currData.state, currData.id);
			Sprite sprite = gp.EnemySheet.sprites.get(currData.id);
			Sprite[] sprites = new Sprite[1];
			sprites[0] = sprite;
			Animation animation = new Animation(sprites);
			currEnemy.addAnimation(AnimationType.PREVIEW, animation);
			currEnemy.setAnimation(AnimationType.PREVIEW);
			gp.enemies.add(currEnemy);
		}
	}
	public static void convertToItem(LinkedList<ItemData> itemData, EditorPanel gp) {
		for(int i = 0; i < itemData.size(); i++) {
			ItemData currData = itemData.get(i);
			Item currItem = new Item(currData.name, currData.x, currData.y,currData.width, currData.height, currData.id, new Sprite(gp.itemSheetSprites.get(currData.id).image));
			gp.items.add(currItem);
		}
	}
	public static void convertToTile(TileData[][] tiledata, EditorPanel gp){
		for(int i = 0; i < tiledata.length; i++) {
			for(int j = 0; j < tiledata[i].length; j++) {
				TileData tileD = tiledata[i][j];
				int x = j*48;
				int y = i*48;
				gp.mapTiles[i][j] = new Tile(new Sprite(null), tileD.room, tileD.roomid,tileD.hasCollision,tileD.hasRoomCollision, tileD.canExplode, tileD.direction,tileD.flammable,x,y);
				gp.mapTiles[i][j].spriteIndex = tileD.spriteIndex;
				gp.mapTiles[i][j].sprite = gp.TileSheet.sprites.get(tileD.spriteIndex);
			}
		}
		
	}


}
