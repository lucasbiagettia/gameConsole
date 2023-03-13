package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Closeable;
import java.io.IOException;
import java.util.Optional;

import javax.swing.JFrame;

import playable.Configurations;
import playable.IPlayable;
import playable.MyKeyEvent;
import playable.Score;

public class MyConsole implements KeyListener, Closeable{
	private IPlayable playable;
	private JFrame gameWindow;
	private Optional<Integer> autoRefresh;
	private GameComponent gameComponent;
	
	public MyConsole(IPlayable thePlayable) {
		playable = thePlayable;
		initialize ();
	}

	private void initialize() {
		Configurations configurations = playable.getConfigurations();
		gameComponent = new GameComponent(playable);
		autoRefresh = configurations.getAutoRefresh();
		gameWindow = new JFrame(configurations.getName());
		gameWindow.setSize(configurations.getScreenWidht() * 40, (configurations.getScreenHeight()+1) * 40);
		gameWindow.setLocation(300, 200);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.addKeyListener(this);
		gameWindow.add(gameComponent);
	}
	
	public void start() {
		gameWindow.setVisible(true);
		playable.play();
	}
	
	public void finish() {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
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
		if (autoRefresh.isEmpty()) {
			askIsFinished();
		}
	}

	private void askIsFinished() {
		if (playable.isFinished()) {
			getResults();
		}else {
			gameWindow.repaint();
		}
		
	}

	private void getResults() {
		Score score = playable.getScore();
		System.out.println(score.getScore());		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void close() throws IOException {
		gameWindow.remove(gameComponent);
		playable.close();
	}
}
