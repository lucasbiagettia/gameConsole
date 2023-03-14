package playable;

import java.awt.Color;
import java.io.Serializable;

public class Pixel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -812771453273672297L;
	/**
	 * 
	 */
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
