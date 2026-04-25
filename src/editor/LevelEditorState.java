package editor;

import java.util.ArrayList;
import java.util.List;

import entity.EnemyData;
import entity.ItemData;
import rooms.LevelData.LevelType;
import tile.TileData;

public class LevelEditorState {

    TileData[][] tiles;

    List<EnemyData> enemies = new ArrayList<>();
    List<ItemData> items = new ArrayList<>();

    LevelType type;

    String specialText;
    boolean isShop;

    int returnX;
    int returnY;
}