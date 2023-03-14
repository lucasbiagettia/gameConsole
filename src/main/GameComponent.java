package main;

import java.awt.Component;
import java.awt.Graphics;
import java.io.IOException;

import playable.IPlayable;
import playable.Pixel;

public class GameComponent extends Component {
	private static final long serialVersionUID = -4728059692875211727L;
	IPlayable playable;

	GameComponent(IPlayable thePlayable) {
		playable = thePlayable;
	}

	@Override
	public void paint(Graphics graphics) {
		Pixel[][] pixels;
		try {
			pixels = playable.getBitMap();

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
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}