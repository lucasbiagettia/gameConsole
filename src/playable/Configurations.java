package playable;

import java.io.Serializable;
import java.util.Optional;

public class Configurations implements Serializable {
	private static final long serialVersionUID = 3740109136716918811L;
	private final String name;
	private final Optional<Integer> autoRefresh;
	private final Integer screenWidht;
	private final Integer screenHeight;

	public Configurations(String theName, Optional<Integer> theAutoRefresh, Integer theScreenWidht, Integer theScreenHeight) {
		name = theName;
		autoRefresh = theAutoRefresh;
		screenWidht = theScreenWidht;
		screenHeight = theScreenHeight;
	}

	public String getName() {
		return name;
	}

	public Optional<Integer> getAutoRefresh() {
		return autoRefresh;
	}

	public Integer getScreenWidht() {
		return screenWidht;
	}

	public Integer getScreenHeight() {
		return screenHeight;
	}

}
