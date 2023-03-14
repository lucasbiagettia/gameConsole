package playable;

import java.io.Serializable;

public enum Request implements Serializable {
	GET_CONFIGURATIONS,
	GET_SCORE,
	GET_BIT_MAP,
	IS_FINISHED,
	PLAY,
	RECEIVE_EVENT;

	public MyKeyEvent keyEvent = null;
}

