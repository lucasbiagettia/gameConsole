package playable;

import java.io.Serializable;

public interface IPlayable extends Serializable{

	Configurations getConfigurations();

	void play();

	Score getScore();

}
