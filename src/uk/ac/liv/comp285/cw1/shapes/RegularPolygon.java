package uk.ac.liv.comp285.cw1.shapes;

import java.awt.Graphics;

import uk.ac.liv.comp285.cw1.Shape;

public class RegularPolygon extends Shape {

	public RegularPolygon(int sides, Point centre, float radius) {
		super();
		if (sides < 3) { // A polygon must have at least 3 sides
			throw new IllegalArgumentException("A polygon must have at least 3 sides");
		}
		if (radius < 0) { // Radius cannot be negative
			throw new IllegalArgumentException("Radius cannot be negative");
		}
		this.sides = sides;
		this.centre = centre;
		this.radius = radius;
	}

	/**
	 * How many sides to the polygon
	 */
	private int sides = 0;
	/**
	 * Centre of polygon, this represents the centre of the smallest circle which
	 * would contain the polygon
	 */
	private Point centre;
	/**
	 * Size of the polygon defined as the radius of the centre of the smallest
	 * circle which would contain the polygon
	 */

	private float radius;

	@Override
	public float getArea() {
		return (float) (0.5 * sides * radius * radius * Math.sin(2 * Math.PI / sides));
	}

	@Override
	public Point getLowerLeftPoint() {
		float minX = Float.MAX_VALUE;
		float minY = Float.MAX_VALUE;
		// Calculate the minimum x and y values of the polygon
		for (int i = 0; i < sides; i++) {
			double angleRad = 2 * Math.PI * i / sides + angle; // Add the angle of rotation
			float x = centre.getX() + (float) (radius * Math.cos(angleRad));
			float y = centre.getY() + (float) (radius * Math.sin(angleRad));
			// Find the minimum x and y values because the lower left point is the one with
			// the smallest x and y values
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
		}

		return new Point(minX, minY);
	}

	@Override
	public Point getUpperRightPoint() {
		float maxX = Float.MIN_VALUE;
		float maxY = Float.MIN_VALUE;
		// Calculate the maximum x and y values of the polygon
		for (int i = 0; i < sides; i++) {
			double angleRad = 2 * Math.PI * i / sides + angle; // Add the angle of rotation
			float x = centre.getX() + (float) (radius * Math.cos(angleRad));
			float y = centre.getY() + (float) (radius * Math.sin(angleRad));
			// Find the maximum x and y values because the upper right point is the one with
			// the largest x and y values
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
		}

		return new Point(maxX, maxY);
	}

	@Override
	public void render(Graphics g) {
		// Create arrays to store the x and y coordinates of the polygon
		int[] xPoints = new int[sides];
		int[] yPoints = new int[sides];
		// Calculate the x and y coordinates of the polygon
		for (int i = 0; i < sides; i++) {
			double angleRad = 2 * Math.PI * i / sides + angle;
			xPoints[i] = (int) (centre.getX() + radius * Math.cos(angleRad));
			yPoints[i] = (int) (centre.getY() + radius * Math.sin(angleRad));
		}

		g.drawPolygon(xPoints, yPoints, sides);
	}

	@Override
	public void setRotation(Point origin, double angle) {
		// Rotate the centre of the polygon using the rotation method from the Point
		// class
		Point rotatedCentre = centre.rotate(origin, angle);
		this.centre = rotatedCentre;
		// Accumulate the angle
		this.angle += angle;
	}

	@Override
	public void setScale(Point origin, double scaleX, double scaleY) {
		// Check if the scale factors are positive
		if (scaleX <= 0 || scaleY <= 0) {
			throw new IllegalArgumentException("Scale factors must be positive");
		}
		// Scale the centre of the polygon
		Point relativeCentre = centre.subtract(origin);
		float newX = relativeCentre.getX() * (float) scaleX;
		float newY = relativeCentre.getY() * (float) scaleY;
		centre = new Point(newX, newY).add(origin);

		// Calculate the mean scale factor
		double scaleFactor = (scaleX + scaleY) / 2;
		radius *= scaleFactor;
	}

}
