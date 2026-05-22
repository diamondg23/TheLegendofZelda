package editor;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import entity.Enemy;
import entity.EnemyData;
import entity.ItemData;
import rooms.LevelData;
import rooms.LevelData.LevelType;
import rooms.SpecialData;
import tile.Tile;
import tile.TileData;

public class LevelWriter {

    public static void save(EditorPanel gp) {
    	JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("res"));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		int result = chooser.showOpenDialog(null);
		TileData[][] tileData = new TileData[gp.mapTiles.length][gp.mapTiles[0].length];
		if (result == JFileChooser.APPROVE_OPTION) {
			   File selectedFile = chooser.getSelectedFile();
			   String baseName = selectedFile.getName();
			   System.out.println(baseName);
		
			if(!checkIfNull(gp.mapTiles)) {
				return;
			}
			else {
				
				for(int i = 0; i < tileData.length; i++) {
					for(int j = 0; j < tileData[i].length; j++) {
						Tile tile = gp.mapTiles[i][j];
						tileData[i][j] = new TileData(tile.spriteIndex,tile.room.ordinal(),tile.hasCollision,tile.hasRoomCollision, tile.canExplode, tile.direction.ordinal(),tile.isFlammable);
						
					}
				}
			}
	       String tilePath =  saveTiles(tileData, baseName);
	       LinkedList<EnemyData> enemyData = new LinkedList<EnemyData>();
	       for(int i = 0; i < gp.enemies.size(); i++) {
	    	   Enemy currEnemy = gp.enemies.get(i);
	    	   String name = getEnemyName(gp.enemies.get(i).ID);
	    	   EnemyData data = new EnemyData(name, currEnemy.x,currEnemy.y,currEnemy.width,currEnemy.height,currEnemy.state,currEnemy.ID);
	    	   enemyData.add(data);
	       }
	      String enemyPath = saveEnemies(enemyData, baseName);
	
	     //   saveItems(state.items, baseName);
	
	    //    saveSpecial(state, baseName);
	
	      //  saveLevelData(state, baseName);
		}
    }
    private static String getEnemyName(int ID) {
    	switch(ID) {
    		case 0:
    			return "snake";
    			
    		case 1:
    			return "Red Minotar";
    		case 2:
    			return "Red Knight";
    		case 3:
    			return "Red Archer";
    		case 4:
    			return "Red Octorok";
    		case 5:
    			return "Red Boomeraang";
    		case 6:
    			return "Statue";
    		case 7:
    			return "Big Slime";
    		case 8:
    			return "Blue Crab";
    		case 9:
    			return "Rabbit Mouse";
    		case 10:
    			return "Red Crab";
    		case 11:
    			return "Slinky";
    		case 12:
    			return "FlyBee";
    		case 13:
    			return "Blue Mage";
    		case 14:
    			return "Red Bat";
    		case 15:
    			return "Blue Bat";
    		case 16:
    			return "Ghost";
    		case 17:
    			return "Red Hopper";
    		case 18:
    			return "Blue Hand";
    		case 19:
    			return "Blue Bat Bunny";
    		case 20:
    			return "Red Mage";
    		case 21:
    			return "Sea Creature";
    		case 22:
    			return "Blue Minotar";
    		case 23:
    			return "Blue Archer";
    		case 24:
    			return "Blue Boomeraang";
    		case 25:
    			return "Blue Knight";
    		case 26:
    			return "Blue Octorok";
    		default:
    			return "ERROR";
    		
    	
    			
    	}
    }
    private static String saveTiles(TileData[][] tiles, String base) {
    	  Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("res/TileData/" + base + "_tiles.json")) {
            gson.toJson(tiles, writer);
            return "res/TileData/" + base + "_tiles.json";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String saveEnemies(LinkedList<EnemyData> enemies, String base) {
    	  Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("res/EnemyData/" + base + "_enemies.json")) {
            gson.toJson(enemies, writer);
            return "res/EnemyData/" + base + "_enemies.json";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String saveItems(List<ItemData> items, String base) {
    	  Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("res/ItemData/" + base + "_items.json")) {
            gson.toJson(items, writer);
            return "res/ItemData/" + base + "_items.json";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    private static String saveSpecial(LevelEditorState state, String base) {

        if (state.type != LevelType.CAVE && state.type != LevelType.SHOP) {
            return null; // only special levels use it
        }
        return null;
        SpecialData data = new SpecialData();
        //data.text = state.specialText;
       // data.isShop = state.isShop;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_special.json")) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String saveLevelData(LevelData state, String base) {

        LevelData data = new LevelData();

        data.name = base;
        data.type = state.type;

        data.tileData = "/maps/" + base + "_tiles.json";
        data.enemyData = "/maps/" + base + "_enemies.json";
        data.itemData = "/maps/" + base + "_items.json";

        if (state.type == LevelType.CAVE || state.type == LevelType.SHOP) {
            data.specialData = "/maps/" + base + "_special.json";
        }

        data.returnX = state.returnX;
        data.returnY = state.returnY;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("res/levelData/" + base + "_level.json")) {
            gson.toJson(data, writer);
            return "res/levelData/" + base + "_level.json";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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