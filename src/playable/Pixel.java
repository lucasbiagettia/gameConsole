package playable;

import java.awt.Color;

public class Pixel {
	private Shape shape;
	private Color color;
	public Pixel(Shape theShape, Color theColor) {
		shape = theShape;
		color = theColor;
	}
	public Shape getShape() {
		return shape;
	}
	public Color getColor() {
		return color;
	}
	
}
