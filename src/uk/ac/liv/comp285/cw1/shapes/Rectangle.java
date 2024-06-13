package uk.ac.liv.comp285.cw1.shapes;

import java.awt.Graphics;

import uk.ac.liv.comp285.cw1.Shape;

public class Rectangle extends Shape {

	public Rectangle(float x, float y, float width, float height) {
		super();
		// Check if the width and height are negative
		if (width < 0 || height < 0) {
			throw new IllegalArgumentException("Width and height cannot be negative");
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	private float x, y, width, height;

	@Override
	public float getArea() { // Calculate the area of the rectangle
		return (width * height);
	}

	@Override
	public Point getLowerLeftPoint() {
		// Rotate the four corners of the rectangle and return the one with the smallest
		// x and y values
		Point p1 = new Point(x, y).rotate(rotationOrigin, angle);
		Point p2 = new Point(x + width, y).rotate(rotationOrigin, angle);
		Point p3 = new Point(x, y + height).rotate(rotationOrigin, angle);
		Point p4 = new Point(x + width, y + height).rotate(rotationOrigin, angle);

		// Find the minimum x and y values of the four points
		float minX = Math.min(Math.min(p1.getX(), p2.getX()), Math.min(p3.getX(), p4.getX()));
		float minY = Math.min(Math.min(p1.getY(), p2.getY()), Math.min(p3.getY(), p4.getY()));

		return new Point(minX, minY);
	}

	@Override
	public Point getUpperRightPoint() {
		// Rotate the four corners of the rectangle and return the one with the largest
		// x and y values
		Point p1 = new Point(x, y).rotate(rotationOrigin, angle);
		Point p2 = new Point(x + width, y).rotate(rotationOrigin, angle);
		Point p3 = new Point(x, y + height).rotate(rotationOrigin, angle);
		Point p4 = new Point(x + width, y + height).rotate(rotationOrigin, angle);

		// Find the maximum x and y values of the four points
		float maxX = Math.max(Math.max(p1.getX(), p2.getX()), Math.max(p3.getX(), p4.getX()));
		float maxY = Math.max(Math.max(p1.getY(), p2.getY()), Math.max(p3.getY(), p4.getY()));

		return new Point(maxX, maxY);
	}

	@Override
	public void render(Graphics g) {
		// Rotate the four corners of the rectangle using the rotation method
		Point p1 = new Point(x, y).rotate(rotationOrigin, angle);
		Point p2 = new Point(x + width, y).rotate(rotationOrigin, angle);
		Point p3 = new Point(x + width, y + height).rotate(rotationOrigin, angle);
		Point p4 = new Point(x, y + height).rotate(rotationOrigin, angle);
		// Draw the lines between the four points
		g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
		g.drawLine((int) p2.getX(), (int) p2.getY(), (int) p3.getX(), (int) p3.getY());
		g.drawLine((int) p3.getX(), (int) p3.getY(), (int) p4.getX(), (int) p4.getY());
		g.drawLine((int) p4.getX(), (int) p4.getY(), (int) p1.getX(), (int) p1.getY());

	}

	@Override
	public void setRotation(Point origin, double angle) {
		// Accumulate the angle of rotation
		this.angle += angle;
		// Set the new rotation origin
		this.rotationOrigin = origin;
	}

	@Override
	public void setScale(Point origin, double scaleX, double scaleY) {
		// Check if the scale factors are positive
		if (scaleX <= 0 || scaleY <= 0) {
			throw new IllegalArgumentException("Scale factors must be positive");
		}

		// Calculate the new x and y coordinates of the lower left and upper right
		// points of the rectangle
		Point lowerLeft = new Point(x, y).subtract(origin);
		Point upperRight = new Point(x + width, y + height).subtract(origin);

		// Apply the scale factors to the x and y coordinates
		float newX1 = lowerLeft.getX() * (float) scaleX;
		float newY1 = lowerLeft.getY() * (float) scaleY;
		float newX2 = upperRight.getX() * (float) scaleX;
		float newY2 = upperRight.getY() * (float) scaleY;

		// Update the rectangle's coordinates
		Point scaledLowerLeft = new Point(newX1, newY1).add(origin);
		Point scaledUpperRight = new Point(newX2, newY2).add(origin);
		this.x = scaledLowerLeft.getX();
		this.y = scaledLowerLeft.getY();
		this.width = scaledUpperRight.getX() - scaledLowerLeft.getX();
		this.height = scaledUpperRight.getY() - scaledLowerLeft.getY();
	}
}
