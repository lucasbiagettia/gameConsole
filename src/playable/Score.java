package playable;

import java.io.Serializable;

public class Score implements Serializable, Comparable<Score> {
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
	
	@Override
    public int compareTo(Score otroScore) {
        return this.score.compareTo(otroScore.getScore());
    }
}
