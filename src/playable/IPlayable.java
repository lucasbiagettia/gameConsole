package playable;

import java.io.Closeable;
import java.io.Serializable;

public interface IPlayable extends Serializable, Closeable {

	Configurations getConfigurations();

	Score getScore();

	Pixel[][] getBitMap();
	
	boolean isFinished();

	void play();

	void receiveEvent(MyKeyEvent keyEvent);
	
}
