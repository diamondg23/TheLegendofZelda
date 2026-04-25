package editor;

import java.io.FileWriter;
import java.util.List;

import com.google.gson.Gson;

import entity.EnemyData;
import entity.ItemData;
import rooms.LevelData;
import rooms.LevelData.LevelType;
import rooms.SpecialData;
import tile.TileData;

public class LevelWriter {

    public static void save(LevelEditorState state, String baseName) {

        saveTiles(state.tiles, baseName);

        saveEnemies(state.enemies, baseName);

        saveItems(state.items, baseName);

        saveSpecial(state, baseName);

        saveLevelData(state, baseName);
    }
    private static void saveTiles(TileData[][] tiles, String base) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_tiles.json")) {
            gson.toJson(tiles, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void saveEnemies(List<EnemyData> enemies, String base) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_enemies.json")) {
            gson.toJson(enemies, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void saveItems(List<ItemData> items, String base) {
        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_items.json")) {
            gson.toJson(items, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static void saveSpecial(LevelEditorState state, String base) {

        if (state.type != LevelType.CAVE && state.type != LevelType.SHOP) {
            return; // only special levels use it
        }

        SpecialData data = new SpecialData();
        //data.text = state.specialText;
       // data.isShop = state.isShop;

        Gson gson = new Gson();
        

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_special.json")) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void saveLevelData(LevelEditorState state, String base) {

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

        Gson gson = new Gson();

        try (FileWriter writer = new FileWriter("res/maps/" + base + "_level.json")) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}