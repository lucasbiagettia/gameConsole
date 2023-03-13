package main;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import playable.IPlayable;
import playable.MyKeyEvent;
import playable.Pixel;

public class GameComponent extends Component implements KeyListener{
	private static final long serialVersionUID = -4728059692875211727L;
	IPlayable playable;

	GameComponent(IPlayable thePlayable) {
		playable = thePlayable;
	}

	@Override
	public void paint(Graphics graphics) {
		Pixel[][] pixels = playable.getBitMap();
		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[0].length; j++) {
				graphics.setColor(pixels[i][j].getColor());
				switch (pixels[i][j].getShape()) {
				case SQUARE:
					graphics.fillRect(i * 40, j * 40, 40, 40);
					break;
				case CIRCLE:
					graphics.fillOval(i * 40, j * 40, 40, 40);
					break;
				}
			}
		}
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
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

}