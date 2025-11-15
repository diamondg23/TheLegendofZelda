package events;

public abstract class Event {
	public enum events {
		PLAYERMOVEMENT(),
		ENEMYMOVEMENT(),
		//this can be like a arrow being shot or something else moving
		MISCMOVEMENT(),
		BOMBPLACED(),
		
	}
	events event;
	int eventID;
	public Event(events event) {
		this.event = event;
		eventID = event.ordinal();
	}
	public Event(int eventID) {
		this.event = events.values()[eventID];
		this.eventID = eventID;
		
	}
	abstract public events getEvent();
}
