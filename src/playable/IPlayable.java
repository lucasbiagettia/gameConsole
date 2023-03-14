package playable;

import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;

public interface IPlayable extends Serializable, Closeable {

	Configurations getConfigurations() throws IOException, ClassNotFoundException;

	Score getScore() throws IOException, ClassNotFoundException;

	Pixel[][] getBitMap() throws IOException, ClassNotFoundException;

	boolean isFinished() throws IOException, ClassNotFoundException;

	void play() throws IOException;

	void receiveEvent(MyKeyEvent keyEvent) throws IOException;

}
