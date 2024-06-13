package uk.ac.liv.comp285.cw1.shapes;

public class Point {
	// Constructor for a point object
	public Point(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	private float x, y;

	/**
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Returns a point which is a subtraction of this Point away from another
	 * 
	 * @param otherPoint
	 * @return
	 */
	Point subtract(Point otherPoint) {
		// Return a new point which is the subtraction of this point from another point
		return (new Point(x - otherPoint.x, y - otherPoint.y));
	}

	/**
	 * Returns a point which is the addition of this point and another point
	 * 
	 * @param otherPoint
	 * @return
	 */
	Point add(Point otherPoint) {
		// Return a new point which is the addition of this point and another point
		return (new Point(x + otherPoint.x, y + otherPoint.y));
	}

	/**
	 * Rotates a point around the origin given by an angle in radians and returns
	 * the resulting Point
	 * 
	 * @param p
	 * @param origin
	 * @param angle
	 * @return
	 */
	public Point rotate(Point origin, double angle) {

		// Check if the origin or angle doesn't necessitate a rotation
		if (origin == null || angle == 0)
			return this;

		// Translate point to origin as the rotation center
		Point relativePoint = subtract(origin);

		// Calculate the new x and y coordinates using rotation matrix so that any angle
		// can be used
		// newX = x * cos(angle) − y * sin(angle)
		// newY = x * sin(angle) + y * cos(angle)
		float newX = (float) (relativePoint.getX() * Math.cos(angle) - relativePoint.getY() * Math.sin(angle));
		float newY = (float) (relativePoint.getX() * Math.sin(angle) + relativePoint.getY() * Math.cos(angle));

		// Translate point back
		Point rotatedPoint = new Point(newX, newY);
		return rotatedPoint.add(origin);

	}

}
