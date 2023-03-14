package playable;

import java.io.Serializable;
import java.util.Optional;

public class Score implements Serializable {
	private static final long serialVersionUID = 800058354218508918L;
	String userName;
	Integer score;

	public Score(String theUserName, Integer theScore) {
		userName = theUserName;
		score = theScore;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setUserName(String theUserName) {
		userName = theUserName;
	}

	public void setScore(Integer theScore) {
		score = theScore;
	}
}
