package connection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import playable.Configurations;
import playable.IPlayable;
import playable.MyKeyEvent;
import playable.Pixel;
import playable.Request;
import playable.Score;


public class SocketClientPlayable implements IPlayable {
	private int port = 5555;
	Socket socket;
	OutputStream outputStream;
	ObjectOutputStream objectOutputStream;
	InputStream inputStream;
	ObjectInputStream objectInputStream;
	Object lock = new Object();

	public SocketClientPlayable(File file) throws IOException {
		JarExecutor jarExecutor = new JarExecutor (file);
		jarExecutor.execute();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
		}
		
		try {
			tryInitializeSocket();
		} catch (IOException e) {
			close();
			throw e;
		}

	}

	private void tryInitializeSocket() throws IOException {
		socket = new Socket("localhost", port);
		outputStream = socket.getOutputStream();
		objectOutputStream = new ObjectOutputStream(outputStream);
		inputStream = socket.getInputStream();
		objectInputStream = new ObjectInputStream(inputStream);
	}

	@Override
	public void close() throws IOException {
		socket.close();
		outputStream.close();
		objectOutputStream.close();
		inputStream.close();
		objectInputStream.close();
	}

	@Override
	public Configurations getConfigurations() throws IOException, ClassNotFoundException {
		synchronized (lock) {
			objectOutputStream.writeObject(Request.GET_CONFIGURATIONS);
			int timeOut = 5;
			int counter = 0;
			Object configurations = new Object();
			while (!(configurations instanceof Configurations) && counter <= timeOut) {
				configurations = objectInputStream.readObject();
				counter++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			return (Configurations) configurations;
		}
	}

	@Override
	public Score getScore() throws IOException, ClassNotFoundException {
		synchronized (lock) {
			objectOutputStream.writeObject(Request.GET_SCORE);
			int timeOut = 5;
			int counter = 0;
			Object score = new Object();
			while (!(score instanceof Score) && counter <= timeOut) {
				score = objectInputStream.readObject();
				counter++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			return (Score) score;
		}
	}

	@Override
	public Pixel[][] getBitMap() throws ClassNotFoundException, IOException {
		synchronized (lock) {
			objectOutputStream.writeObject(Request.GET_BIT_MAP);
			int timeOut = 5;
			int counter = 0;
			Pixel[][] bitmap = null;
			while (bitmap == null && counter <= timeOut) {
				bitmap = (Pixel[][]) objectInputStream.readObject();
				counter++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			return bitmap;
		}
	}

	@Override
	public boolean isFinished() throws IOException, ClassNotFoundException {
		synchronized (lock) {
			objectOutputStream.writeObject(Request.IS_FINISHED);
			int timeOut = 5;
			int counter = 0;
			Boolean finished = null;
			while (finished == null && counter <= timeOut) {
				finished = (Boolean) objectInputStream.readObject();
				counter++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
			return finished.booleanValue();
		}
	}

	@Override
	public void play() throws IOException {
		synchronized (lock) {
			objectOutputStream.writeObject(Request.PLAY);
		}

	}

	@Override
	public void receiveEvent(MyKeyEvent keyEvent) throws IOException {
		synchronized (lock) {
			objectOutputStream.writeObject(keyEvent);
		}
	}
}
