package events;

import entity.Entity.Direction;
import entity.Player;

public class PlayerMovementEvent extends Event {

	private Direction directionMoved;
	private int moveAmount;
	public PlayerMovementEvent(events event, Direction directionMoved, int moveAmount) {
		super(event);
		this.directionMoved = directionMoved;
		this.moveAmount = moveAmount;
	}

	public PlayerMovementEvent(int eventID, Direction directionMoved, int moveAmount) {
		super(eventID);
		this.directionMoved = directionMoved;
		this.moveAmount = moveAmount;
	}

	@Override
	public events getEvent() {
		return event;
	}
	public Direction getDirection() {
		return directionMoved;
	}
	public int getAmountMoved() {
		return moveAmount;
	}
	public void resolveEvent(Player player) {
		player.Move(directionMoved, moveAmount);
	}

}
