package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import games.maze.commons.MazeGame;
import playable.Configurations;
import playable.IPlayable;
import playable.MyKeyEvent;
import playable.Pixel;
import playable.Request;
import playable.Score;

public class FakeServer implements Runnable {
	private int port;
	Socket socket;
	OutputStream outputStream;
	ObjectOutputStream objectOutputStream;
	InputStream inputStream;
	ObjectInputStream objectInputStream;
	Object lock = new Object();
	IPlayable playable;

	public FakeServer(int thePort) {
		port = thePort;
		playable = new MazeGame();
			Thread thread = new Thread(this);
			thread.start();
	}

//	private void runsa() {
//		while (true) {
//			Object readObject = objectInputStream.readObject();
//			if (readObject instanceof Request) {
//				switch ((Request)readObject) {
//				case GET_CONFIGURATIONS:
//					Configurations configurations = playable.getConfigurations();
//					objectOutputStream.writeObject(configurations);
//					break;
//				case GET_SCORE:
//					Score score = playable.getScore();
//					objectOutputStream.writeObject(score);
//					break;
//				case GET_BIT_MAP:
//					Pixel[][] bitMap = playable.getBitMap();
//					objectOutputStream.writeObject(bitMap);
//					break;
//				case IS_FINISHED:
//					boolean finished = playable.isFinished();
//					objectOutputStream.writeObject(Boolean.valueOf(finished));
//					break;
//				case PLAY:
//					playable.play();
//					break;
//				case RECEIVE_EVENT:
//					MyKeyEvent myKeyEvent = ((Request) readObject).getKeyEvent().get();
//					playable.receiveEvent(myKeyEvent);
//					break;
//				default:
//					break;
//				}
//				
//			}
//						
//		}
//	}

	public void initialize() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		socket = serverSocket.accept();
		outputStream = socket.getOutputStream();
		objectOutputStream = new ObjectOutputStream(outputStream);
		inputStream = socket.getInputStream();
		objectInputStream = new ObjectInputStream(inputStream);
	}

	@Override
	public void run() {
		try {
			initialize();
		} catch (Exception e) {
		}
		while (true) {
			try {
				Object readObject = objectInputStream.readObject();
				if (readObject instanceof Request) {
					switch ((Request) readObject) {
					case GET_CONFIGURATIONS:
						Configurations configurations = playable.getConfigurations();
						objectOutputStream.writeObject(configurations);
						break;
					case GET_SCORE:
						Score score = playable.getScore();
						objectOutputStream.writeObject(score);
						break;
					case GET_BIT_MAP:
						Pixel[][] bitMap = playable.getBitMap();
						objectOutputStream.writeObject(bitMap);
						break;
					case IS_FINISHED:
						boolean finished = playable.isFinished();
						objectOutputStream.writeObject(Boolean.valueOf(finished));
						break;
					case PLAY:
						playable.play();
						break;
					case RECEIVE_EVENT:
						MyKeyEvent myKeyEvent = ((Request) readObject).getKeyEvent().get();
						playable.receiveEvent(myKeyEvent);
						break;
					default:
						break;
					}

				}
			} catch (Exception e) {
			}

		}
	}
}