package uk.ac.liv.comp285.cw1;

import java.awt.Graphics;

import uk.ac.liv.comp285.cw1.shapes.Point;

public interface IShape {

	/**
	 * Calculates and returns the area of the shape
	 * 
	 * @return area calculated
	 */
	public float getArea();

	/**
	 * Returns the lower left hand point of the bounding rectangle of the shape
	 * 
	 * @return Point containing lower left hand Point
	 */
	public Point getLowerLeftPoint();

	/**
	 * Returns the upper right hand Point of the bounding rectangle of the shape
	 * 
	 * @return Point containing upper right hand shape
	 */
	public Point getUpperRightPoint();

	/**
	 * Returns true if this shape touches or overlaps another shape
	 * There is a simple implementation contained within Shape.java which checks for
	 * collisions between
	 * the bounding rectangle of the given shapes
	 * 
	 * @param shape Other shape to check collision againsts
	 * @return True if shapes are colliding, otherwise False
	 */
	public boolean doesCollide(IShape shape);

	/**
	 * Draws the shape in 2D using the AWT graphics class, see documentation for AWT
	 * to understand how to
	 * use the Graphics object
	 * 
	 * @param g Graphics object to draw into
	 */
	public void render(Graphics g);

	/**
	 * Set's a rotation on the object from the given origin
	 * 
	 * @param p     Origin of rotation
	 *              The rotation for a positive angle will be in a anti-clockwise
	 *              direction
	 *              from the origin point p
	 * @param angle Angle of rotation in radians
	 */
	public void setRotation(Point p, double angle);

	/**
	 * Scale's the shape relative to the origin given
	 * 
	 * @param p
	 * @param scaleX
	 * @param scaleY
	 */
	public void setScale(Point p, double scaleX, double scaleY);

}
