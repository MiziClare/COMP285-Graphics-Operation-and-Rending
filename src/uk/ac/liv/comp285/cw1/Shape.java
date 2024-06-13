package uk.ac.liv.comp285.cw1;

import uk.ac.liv.comp285.cw1.shapes.Point;

/*
 * 
 * Shape.java 文件定义了一个抽象的 Shape 类，它实现了 IShape 接口。
 * 它具有两个属性： angle 和 rotationOrigin，用于定义形状的旋转。
 * Shape 类还提供了 doesCollide 方法来检测与其他形状的碰撞，以及用于设置旋转和缩放的功能。
 * 
 */
public abstract class Shape implements IShape {

	protected double angle; // rotation angle in radians
	protected Point rotationOrigin; // rotation centre/origin
	private Point scaleOrigin; // scale centre/origin
	private double scaleX; // scale factor in x
	private double scaleY; // scale factor in y

	// Constructor for Shape
	public Shape() {
		// Add this shape to the canvas in CanvasFrame (if it exists) when it is created
		if (CanvasFrame.getPanelCanvas() != null) {
			CanvasFrame.getPanelCanvas().addShape(this);
		}
	}

	// Check if this shape collides with another shape
	public boolean doesCollide(IShape shape) {
		Point lowerLeft1 = shape.getLowerLeftPoint(); // Get the lower left point of the other shape
		Point upperRight1 = shape.getUpperRightPoint(); // Get the upper right point of the other shape
		Point lowerLeft2 = getLowerLeftPoint(); // Get the lower left point of this shape
		Point upperRight2 = getUpperRightPoint(); // Get the upper right point of this shape

		// Compare 4 points of the two shapes to check if they overlap
		if (upperRight1.getX() < lowerLeft2.getX()) {
			return (false); // no over lap horizontal
		}
		if (lowerLeft1.getX() > upperRight2.getX()) {
			return (false); // no over lap horizontal
		}
		if (upperRight1.getY() < lowerLeft2.getY()) {
			return (false); // no over lap vertical
		}
		if (lowerLeft1.getY() > upperRight2.getY()) {
			return (false); // no over lap vertical
		}
		return (true);
	}

	// Set the rotation origin and angle
	@Override
	public void setRotation(Point origin, double angle) {
		this.angle = angle;
		this.rotationOrigin = origin;

	}

	// Set the scale origin and scale factors in x and y
	@Override
	public void setScale(Point p, double scaleX, double scaleY) {
		this.scaleOrigin = p;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	// Get the scale origin
	public Point getScaleOrigin() {
		return scaleOrigin;
	}

	// Get the scale factor in x
	public double getScaleX() {
		return scaleX;
	}

	// Get the scale factor in y
	public double getScaleY() {
		return scaleY;
	}

}
