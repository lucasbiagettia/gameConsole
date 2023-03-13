package playable;

import java.io.Serializable;

public interface IPlayable extends Serializable {

	Configurations getConfigurations();

	Score getScore();

	Pixel[][] getBitMap();
	
	boolean isFinished();

	void play();

	void receiveEvent(MyKeyEvent keyEvent);
	
}
