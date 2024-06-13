package uk.ac.liv.comp285.cw1.shapes;

import java.awt.Graphics;

import uk.ac.liv.comp285.cw1.Shape;

public class Circle extends Shape {
	// Constructor for a circle object
	public Circle(float x, float y, float radius) {
		super();
		if (radius < 0) {
			throw new IllegalArgumentException("Radius cannot be negative");
		}
		this.x = x;
		this.y = y;
		this.radius = radius;
	}

	private float x, y, radius; // x,y are centre of the circle

	@Override
	public float getArea() {
		return ((float) (Math.PI * radius * radius));
	}

	@Override
	public Point getLowerLeftPoint() {
		return (new Point(x - radius, y - radius));
	}

	@Override
	public Point getUpperRightPoint() {
		return (new Point(x + radius, y + radius));
	}

	@Override
	public void render(Graphics g) { // Render the circle
		g.drawArc((int) (x - radius), (int) (y - radius), (int) (radius * 2), (int) (radius * 2), 0, 360);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	@Override
	public void setRotation(Point origin, double angle) {
		// Use the rotation method from the Point class to rotate the circle's center
		Point center = new Point(x, y);
		Point rotatedCenter = center.rotate(origin, angle);
		this.x = rotatedCenter.getX();
		this.y = rotatedCenter.getY();
	}

	@Override
	public void setScale(Point origin, double scaleX, double scaleY) {
		// Scaling the circle based on the x-scale factor only, because it's a circle
		if (scaleX <= 0 || Double.isInfinite(scaleX) || Double.isNaN(scaleX)) {
			throw new IllegalArgumentException("Scale factor must be positive");
		} else {
			radius *= scaleX;

			// If the origin is provided, also move the center towards/away from the origin
			if (origin != null) {
				Point center = new Point(x, y);
				Point relativeCenter = center.subtract(origin);

				// Apply scale to the relative center
				float newX = relativeCenter.getX() * (float) scaleX;
				float newY = relativeCenter.getY() * (float) scaleX;

				// Update the circle's center coordinates
				Point scaledCenter = new Point(newX, newY).add(origin);
				this.x = scaledCenter.getX();
				this.y = scaledCenter.getY();
			}
		}
	}
}
