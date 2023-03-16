package user_interface;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Closeable;
import java.io.IOException;

import javax.swing.JFrame;

import main.ScoreManager;
import playable.Configurations;
import playable.IPlayable;
import playable.MyKeyEvent;

public class MyConsole implements KeyListener, Closeable {
	private IPlayable playable;
	private JFrame gameWindow;
	private Integer autoRefresh;
	private GameComponent gameComponent;

	public MyConsole(IPlayable thePlayable) throws ClassNotFoundException, IOException {
		playable = thePlayable;
		initialize();
	}

	private void initialize() throws ClassNotFoundException, IOException {
		Configurations configurations = playable.getConfigurations();
		gameComponent = new GameComponent(playable);
		autoRefresh = configurations.getAutoRefresh();
		gameWindow = new JFrame(configurations.getName());
		gameWindow.setSize((configurations.getScreenWidht()+1) * 40, (configurations.getScreenHeight() + 2) * 40);
		gameWindow.setLocation(300, 200);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.addKeyListener(this);
		gameWindow.add(gameComponent);
	}

	public void start() throws IOException {
		gameWindow.setVisible(true);
		playable.play();

		if (autoRefresh > 0) {
			Thread thread = new Thread(new AutomaticRepainter(autoRefresh));
			thread.run();
		}
	}

	class AutomaticRepainter implements Runnable {
		int sleep;

		AutomaticRepainter(int autoRefresh) {
			sleep = autoRefresh;
		}

		@Override
		public void run() {
			while (true) {
				gameWindow.repaint();
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		try {
			switch (e.getKeyCode()) {
			case 38:
				playable.receiveEvent(MyKeyEvent.UP);
				break;
			case 37:
				playable.receiveEvent(MyKeyEvent.LEFT);
				break;
			case 40:
				playable.receiveEvent(MyKeyEvent.DOWN);
				break;
			case 39:
				playable.receiveEvent(MyKeyEvent.RIGHT);
				break;
			}

			if (autoRefresh == 0) {
				askIsFinished();
			}
		} catch (IOException | ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void askIsFinished() throws ClassNotFoundException, IOException {
		if (playable.isFinished()) {
			gameWindow.dispose();
			ScoreManager scoreManager = ScoreManager.getInstance();
			scoreManager.addScore(playable.getScore(), playable.getConfigurations().getName());
		} else {
			gameWindow.repaint();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void close() throws IOException {
		gameWindow.remove(gameComponent);
		playable.close();
	}
}
