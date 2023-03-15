package playable;

import java.io.Serializable;

public class Configurations implements Serializable {
	private static final long serialVersionUID = 3740109136716918811L;
	private final String name;
	private final Integer autoRefresh;
	private final Integer screenWidht;
	private final Integer screenHeight;

	public Configurations(String theName, Integer theAutoRefresh, Integer theScreenWidht, Integer theScreenHeight) {
		name = theName;
		autoRefresh = theAutoRefresh;
		screenWidht = theScreenWidht;
		screenHeight = theScreenHeight;
	}

	public String getName() {
		return name;
	}

	public Integer getAutoRefresh() {
		return autoRefresh;
	}

	public Integer getScreenWidht() {
		return screenWidht;
	}

	public Integer getScreenHeight() {
		return screenHeight;
	}

}
