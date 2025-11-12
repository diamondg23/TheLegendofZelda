package events;

public class PlayerMovementEvent extends Event {

	public PlayerMovementEvent(events event) {
		super(event);
		
	}

	public PlayerMovementEvent(int eventID) {
		super(eventID);
		
	}

	@Override
	<T> T getEvent() {
		
		return null;
	}

}
