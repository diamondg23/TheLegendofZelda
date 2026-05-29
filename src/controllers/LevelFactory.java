package controllers;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;

import panels.GamePanel;
import rooms.Level;
import rooms.LevelData;
import rooms.OverWorldLevel;
import rooms.Shop;
import rooms.Cave;
import rooms.DungeonLevel;


public class LevelFactory {

	
	
	public static void levelInitizer(GamePanel gp) {
	
		  Gson gson = new Gson();

	      File folder = new File("res/LevelData/");
	      File[] files = folder.listFiles();

	      if (files == null) {
	           return;
	      }
	      for(File file: files) {
	    	  System.out.println("File found!");
	    	  if (!file.isFile()) {
	                continue;
	            }

	            if (!file.getName().endsWith(".json")) {
	                continue;
	            }
	            try (FileReader reader = new FileReader(file)) {

	                LevelData levelData = gson.fromJson(reader, LevelData.class);
	                Level level = create(levelData,gp);
	                switch(level.levelType) {
					case CAVE:
						gp.caveLevels.put(level.ID, (Cave) level);
						break;
					case LEVEL1:
						DungeonLevel dungeon1 = (DungeonLevel) level;
						gp.Dungeon1Map[dungeon1.dungeonY][dungeon1.dungeonX] = dungeon1;
						break;
					case LEVEL2:
						DungeonLevel dungeon2 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon2.dungeonY][dungeon2.dungeonX] = dungeon2;
						break;
					case LEVEL3:
						DungeonLevel dungeon3 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon3.dungeonY][dungeon3.dungeonX] = dungeon3;
						break;
					case LEVEL4:
						DungeonLevel dungeon4 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon4.dungeonY][dungeon4.dungeonX] = dungeon4;
						break;
					case LEVEL5:
						DungeonLevel dungeon5 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon5.dungeonY][dungeon5.dungeonX] = dungeon5;
						break;
					case LEVEL6:
						DungeonLevel dungeon6 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon6.dungeonY][dungeon6.dungeonX] = dungeon6;
						break;
					case LEVEL7:
						DungeonLevel dungeon7 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon7.dungeonY][dungeon7.dungeonX] = dungeon7;
						break;
					case LEVEL8:
						DungeonLevel dungeon8 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon8.dungeonY][dungeon8.dungeonX] = dungeon8;
						break;
					case LEVEL9:
						DungeonLevel dungeon9 = (DungeonLevel) level;
						gp.Dungeon2Map[dungeon9.dungeonY][dungeon9.dungeonX] = dungeon9;
						break;
					case OVERWORLD:
						OverWorldLevel overworld = (OverWorldLevel) level;
						gp.overworldMap[overworld.worldY][overworld.worldX] = overworld;
						break;
					case SHOP:
						Shop shop = (Shop) level;
						gp.shopLevels.put(shop.ID, shop);
						break;
					default:
						break;
	                
	                }
	                System.out.println(
	                    "Loaded level: " + file.getName()
	                );

	            } catch (Exception e) {
	                System.out.println(
	                    "Failed to load: " + file.getName()
	                );

	                e.printStackTrace();
	            }
	        }
	      }

	
	
    public static Level create(LevelData data, GamePanel gp) {

        Level level;

        switch (data.type) {

            case OVERWORLD:
                level = new OverWorldLevel(data.ID, data.name, data.tileData, data.enemyData, data.itemData, null, data.x, data.y);
                break;

            case CAVE:
                level = new Cave(data.ID, data.name, data.tileData, data.enemyData, data.itemData, null);
                break;


            default:
                level = new Level(data.type, data.ID,data.name, data.tileData, data.enemyData, data.itemData, null);
        }

        
        level.tileManager = new TileManager(gp);
        level.enemyManager = new EnemyManager(gp);
        level.itemManager = new ItemManager(gp);

        return level;
    }
}