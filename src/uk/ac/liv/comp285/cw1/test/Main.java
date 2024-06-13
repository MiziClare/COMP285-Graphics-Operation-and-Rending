package uk.ac.liv.comp285.cw1.test;

import uk.ac.liv.comp285.cw1.CanvasFrame;
import uk.ac.liv.comp285.cw1.shapes.Circle;
import uk.ac.liv.comp285.cw1.shapes.Point;
import uk.ac.liv.comp285.cw1.shapes.Rectangle;
import uk.ac.liv.comp285.cw1.shapes.RegularPolygon;

public class Main {

	public static void main(String[] args) {

		// CanvasFrame.showShapes() is used to display the window
		CanvasFrame.showShapes();

		System.out.print("Checking out shapes");

		// Rectangles
		Rectangle rectangle0 = new Rectangle(300, 300, 200, 400);
		rectangle0.setRotation(new Point(0, 0), 0);

		Rectangle rectangle = new Rectangle(300, 300, 200, 400);
		rectangle.setRotation(new Point(0, 0), 0.1);
		rectangle.setScale(new Point(0, 0), 1, 1);

		Rectangle rectangle1 = new Rectangle(300, 300, 200, 400);
		rectangle1.setRotation(new Point(0, 0), -0.1);
		rectangle1.setScale(new Point(0, 0), 1, 1);

		Rectangle rectangle2 = new Rectangle(300, 300, 200, 400);
		rectangle2.setRotation(new Point(0, 0), -0.1);
		rectangle2.setRotation(new Point(0, 0), -0.1);
		rectangle2.setScale(new Point(0, 0), 1, 1);

		Rectangle rectangle3 = new Rectangle(300, 300, 200, 400);
		rectangle3.setRotation(new Point(0, 0), Math.PI / 6);
		rectangle3.setScale(new Point(0, 0), 1, 1);

		Rectangle rectangle4 = new Rectangle(300, 300, 200, 400);
		rectangle4.setRotation(new Point(300, 300), Math.PI / 6);

		// Circles
		Circle circle = new Circle(0, 0, 200);
		circle.setScale(new Point(0, 0), 2, 2);

		Circle circle1 = new Circle(0, 0, 200);
		circle1.setScale(new Point(0, 0), 1, 1);
		circle1.setRotation(new Point(100, 100), Math.PI / 4);

		// Regular Polygons
		RegularPolygon polygon = new RegularPolygon(100, new Point(500, 500), 100);
		polygon.setRotation(new Point(0, 0), Math.PI * 2);
		// polygon.setScale(new Point(0, 0), 0.5, 1.5);

		RegularPolygon polygon1 = new RegularPolygon(100, new Point(500, 500), 100);
		polygon1.setRotation(new Point(400, 500), Math.PI / 4);

		System.out.println("Collides is " + rectangle.doesCollide(circle));

		int x = -50;
		int y = 50;

		double tan = y / x;

		System.out.println("Arc tan is " + Math.atan(tan) * (360 / (Math.PI * 2)));

		// Circle circle1 = new Circle(10.0f, 10.0f, 5.0f);
		// circle1.setScale(new Point(0, 0), 1000.0, 1000.0);

		// System.out.println("Area is " + circle1.getArea());

		// System.out.println("Lower left point is " +
		// circle1.getLowerLeftPoint().getX() + " "
		// + circle1.getLowerLeftPoint().getY());
		// System.out.println("Upper right point is " +
		// circle1.getUpperRightPoint().getX() + " "
		// + circle1.getUpperRightPoint().getY());

	}

}
