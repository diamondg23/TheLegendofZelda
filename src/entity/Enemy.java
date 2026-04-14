package entity;


public class Enemy extends Entity {

	public enum states{
		AGGRESSIVE(),
		PASSIVE(),
		RANDOM()
	}
	public states state;
	
	public Enemy(int x, int y, int width, int height, states state) {
		super(x, y, width, height);
		this.state = state;
	}

	@Override
	public void changeDirection(EntityDirection direction) {
		// TODO Auto-generated method stub

	}

}
