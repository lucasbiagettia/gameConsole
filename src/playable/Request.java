package playable;

import java.io.Serializable;
import java.util.Optional;

public enum Request implements Serializable {
	GET_CONFIGURATIONS,
	GET_SCORE,
	GET_BIT_MAP,
	IS_FINISHED,
	PLAY,
	RECEIVE_EVENT;

	private Optional<MyKeyEvent> keyEvent = Optional.empty();

	private Request() {
	}

	public void setKeyEvent(Optional<MyKeyEvent> theKeyEvent) {
		keyEvent = theKeyEvent;
	}

	public Optional<MyKeyEvent> getKeyEvent() {
		return keyEvent;
	}

}
