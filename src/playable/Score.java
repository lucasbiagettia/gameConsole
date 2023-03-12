package playable;

import java.io.Serializable;
import java.util.Optional;

public class Score implements Serializable {
	private static final long serialVersionUID = 800058354218508918L;
	Optional<String> userName;
	Integer score;

	public Optional<String> getUserName() {
		return userName;
	}

	public Integer getScore() {
		return score;
	}

	public void setUserName(Optional<String> theUserName) {
		userName = theUserName;
	}

	public void setScore(Integer theScore) {
		score = theScore;
	}
}
