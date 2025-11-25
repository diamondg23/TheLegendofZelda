package entity;

import interfaces.Shoots;

public class Octorok extends Enemy implements Shoots{

	// the shootTimer will be a integer going down by 1 per frame until it reaches 0. It will then shoot then reset.
	int shootTimer;
	public Octorok(int x, int y, int width, int height) {
		super(x, y, width, height);
		// shoots every 500 frames
		shootTimer = 500;
	}

	@Override
	public Projectile shoot() {
		shootTimer = 500;
		//create a projectile object
		Projectile projectile =  new Projectile(this.x,this.y,this.width-10, this.height -10);
		projectile.changeDirection(directionFacing);
		return projectile;
		
	}

}
