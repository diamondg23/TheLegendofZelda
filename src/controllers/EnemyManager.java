package controllers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import interfaces.Manager;
import main.Animation;
import main.Animation.AnimationType;
import panels.GamePanel;
import tile.Sprite;
import tile.SpriteSheet;
import tile.TileData;
import entity.Enemy;
import entity.EnemyData;
public class EnemyManager implements Manager{

	GamePanel gp;
	LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	public EnemyManager(GamePanel gp) {
		this.gp = gp;
	}
	public void update() {
		//called every frame to loop through the enemy
		
	}

	public void draw(Graphics2D g2) {
		for(Enemy enemy : enemies) {
			g2.drawImage(enemy.getAnimation().getCurrentFrame().image, enemy.x, enemy.y + GamePanel.UI_HEIGHT/3, enemy.width, enemy.height, gp);
			g2.setColor(Color.blue);
			g2.drawRect(enemy.solidArea.x, enemy.solidArea.y + GamePanel.UI_HEIGHT/3, enemy.solidArea.width, enemy.solidArea.height );
		}
		
	}
	public void load(String enemyData) {
		Gson gson = new Gson();
		FileReader reader;
		try {
			reader = new FileReader(enemyData);
			 Type enemyListType = new TypeToken<LinkedList<EnemyData>>(){}.getType();

		     LinkedList<EnemyData> dataEnemies = gson.fromJson(reader, enemyListType);
		     for(int i = 0; i < dataEnemies.size(); i++) {
		    	 EnemyData currData = dataEnemies.get(i);
		    	 
		    	 Enemy currEnemy = new Enemy(currData.x,currData.y,currData.width,currData.height, Enemy.states.RANDOM, currData.id);
		    	 currEnemy.x /= 3;
		    	 currEnemy.x -= 32;
		    	 currEnemy.y /= 3;
		    	 currEnemy.width /= 3;
		    	 currEnemy.height /= 3;
		    	 currEnemy.solidArea.x /= 3;
		    	 currEnemy.solidArea.x -= 32;
		    	 currEnemy.solidArea.y /= 3;
		    	 currEnemy.solidArea.width /= 3;
		    	 currEnemy.solidArea.height /= 3;
		    	 
		    	 currEnemy.solidArea.x +=3;
		    	 currEnemy.solidArea.y +=3;
		    	 assignAnimations(currEnemy);
		    	 currEnemy.setAnimation(AnimationType.WALK_SOUTH);
		    	 enemies.add(currEnemy);
		     }
		}catch(Exception e) {
			
		}
		
	}
	@Override
	public void unload() {
		enemies.clear(); 
		
	}
	private void assignAnimations( Enemy enemy) {
		try {
			switch(enemy.ID) {
			case 4:
				SpriteSheet octorokSheet = new SpriteSheet(new Sprite(ImageIO.read(getClass().getResourceAsStream("/spritesheet/legendofzelda_Octorok.png"))), 16,16,8,14,14,2);
				Sprite[] sprites = new Sprite[2];
				sprites[0] = octorokSheet.sprites.get(0);
				sprites[1] = octorokSheet.sprites.get(4);
				Animation currAnimation = new Animation(sprites);
				enemy.addAnimation(AnimationType.WALK_SOUTH, currAnimation);
				sprites[0] = octorokSheet.sprites.get(1);
				sprites[1] = octorokSheet.sprites.get(5);
				currAnimation = new Animation(sprites);
				enemy.addAnimation(AnimationType.WALK_WEST, currAnimation);
				sprites[0] = octorokSheet.sprites.get(2); 
				sprites[1] = octorokSheet.sprites.get(6);
				currAnimation = new Animation(sprites);
				enemy.addAnimation(AnimationType.WALK_NORTH, currAnimation);
				sprites[0] = octorokSheet.sprites.get(3);
				sprites[1] = octorokSheet.sprites.get(7);
				currAnimation = new Animation(sprites);
				enemy.addAnimation(AnimationType.WALK_EAST, currAnimation);
				break;
			 
				
		}
		}catch(Exception e) {
			
		}
		
		
		
	}

}
