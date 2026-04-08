package events;

import entity.Entity.EntityDirection;
import entity.Player;
import panels.GamePanel;
import tile.TileManager;
// This will have the event atributes to be able to use in other areas of the code.
public class PlayerMovementEvent extends Event {

	private EntityDirection directionMoved;
	private int moveAmount;
	public PlayerMovementEvent(events event, EntityDirection directionMoved, int moveAmount) {
		super(event);
		this.directionMoved = directionMoved;
		this.moveAmount = moveAmount;
	}

	public PlayerMovementEvent(int eventID, EntityDirection directionMoved, int moveAmount) {
		super(eventID);
		this.directionMoved = directionMoved;
		this.moveAmount = moveAmount;
	}

	@Override
	public events getEvent() {
		return event;
	}
	public EntityDirection getDirection() {
		return directionMoved;
	}
	public int getAmountMoved() {
		return moveAmount;
	}
	public void resolveEvent(Player player, TileManager tileM, int offsetX, int offsetY , GamePanel panel) {
		player.Move(directionMoved, moveAmount,offsetX,offsetY, tileM, panel);
	}

}
