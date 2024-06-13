package uk.ac.liv.comp285.cw1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.ac.liv.comp285.cw1.shapes.Circle;
import uk.ac.liv.comp285.cw1.shapes.Point;
import uk.ac.liv.comp285.cw1.shapes.Rectangle;

public class CircleTest {
    private Circle circle;

    @Before
    public void setUp() {
        circle = new Circle(10.0f, 10.0f, 5.0f);
    }

    @After
    public void tearDown() {
        circle = null;
    }

    @Test
    public void testGetArea() {
        // Test the area of the circle
        assertEquals(Math.PI * 25, circle.getArea(), 0.001);
    }

    @Test
    public void testGetLowerLeftPoint() {
        // Test the lower left point of the circle
        Point lowerLeft = circle.getLowerLeftPoint();
        assertEquals(5.0f, lowerLeft.getX(), 0.001f);
        assertEquals(5.0f, lowerLeft.getY(), 0.001f);
    }

    @Test
    public void testGetUpperRightPoint() {
        // Test the upper right point of the circle
        Point upperRight = circle.getUpperRightPoint();
        assertEquals(15.0f, upperRight.getX(), 0.001f);
        assertEquals(15.0f, upperRight.getY(), 0.001f);
    }

    @Test
    public void testSetRotationZeroAngle() {
        // Test setting the rotation of the circle to 0 degrees
        Point origin = new Point(0, 0);
        circle.setRotation(origin, 0);
        assertEquals(5.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(5.0f, circle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test
    public void testSetRotationPositiveAngle() {
        // Test setting the rotation of the circle to 90 degrees
        Point origin = new Point(0, 0);
        circle.setRotation(origin, Math.PI / 2);
        assertEquals(-15.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(5.0f, circle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test
    public void testSetRotationNegativeAngle() {
        // Test setting the rotation of the circle to -90 degrees
        Point origin = new Point(0, 0);
        circle.setRotation(origin, -Math.PI / 2);
        assertEquals(5.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(-15.0f, circle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleZeroScaleFactor() {
        // Test setting the scale of the circle to 0
        Point origin = new Point(0, 0);
        circle.setScale(origin, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleNegativeScaleFactor() {
        // Test setting the scale of the circle to a negative value
        Point origin = new Point(0, 0);
        circle.setScale(origin, -1, -1);
    }

    @Test
    public void testSetScalePositiveScaleFactor() {
        // Test setting the scale of the circle to 2
        Point origin = new Point(0, 0);
        circle.setScale(origin, 2.0, 2.0);
        assertEquals(10.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(10.0f, circle.getLowerLeftPoint().getY(), 0.001f);
        assertEquals(20.0f, circle.getUpperRightPoint().getX() - 10.0f, 0.001f);
        assertEquals(20.0f, circle.getUpperRightPoint().getY() - 10.0f, 0.001f);
    }

    @Test
    public void testSetScaleRelativeToDifferentOrigin() {
        // Test setting the scale of the circle to 2 with a different origin
        Point origin = new Point(5, 5);
        circle.setScale(origin, 2.0, 2.0);
        assertEquals(5.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(5.0f, circle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideWithOtherCircleColliding() {
        // Test if the circle collides with another circle
        Circle otherCircle = new Circle(15.0f, 10.0f, 5.0f);
        assertTrue(circle.doesCollide(otherCircle));
    }

    @Test
    public void testDoesCollideWithOtherCircleNotColliding() {
        // Test if the circle does not collide with another circle
        Circle otherCircle = new Circle(30.0f, 10.0f, 5.0f);
        assertFalse(circle.doesCollide(otherCircle));
    }

    @Test
    public void testDoesCollideWithRectangleColliding() {
        // Test if the circle collides with a rectangle
        Rectangle rectangle = new Rectangle(12.0f, 12.0f, 5.0f, 5.0f);
        assertTrue(circle.doesCollide(rectangle));
    }

    @Test
    public void testDoesCollideWithRectangleNotColliding() {
        // Test if the circle does not collide with a rectangle
        Rectangle rectangle = new Rectangle(30.0f, 30.0f, 10.0f, 10.0f);
        assertFalse(circle.doesCollide(rectangle));
    }

    @Test
    public void testSetScaleWithDifferentScaleFactors() {
        // Scale by different factors for x and y, expect the circle only respects
        // scaleX
        Point origin = new Point(0, 0);
        circle.setScale(origin, 2.0, 3.0); // Only scaleX should be used
        assertEquals(30.0f, circle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(30.0f, circle.getUpperRightPoint().getY(), 0.001f);
        assertEquals(20.0f, circle.getUpperRightPoint().getX() - 10.0f, 0.001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleWithExtremeNegativeScaleFactor() {
        // Extreme negative scaling, expecting IllegalArgumentException
        circle.setScale(new Point(0, 0), -100.0, -100.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleWithExtremePositiveScaleFactor() {
        // Extreme positive scaling with zero, expecting IllegalArgumentException
        circle.setScale(new Point(0, 0), Double.POSITIVE_INFINITY, 1.0);
    }

    @Test
    public void testSetScaleWithLargeRadius() {
        // Testing scaling with a large radius
        circle.setScale(new Point(0, 0), 1000.0, 1000.0);
        assertEquals(15000.0f, circle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(15000.0f, circle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideWithRectangleEdges() {
        // Test collision where the rectangle and circle are just touching each other
        Rectangle touchingRectangle = new Rectangle(15.0f, 10.0f, 0.0f, 10.0f);
        assertTrue(circle.doesCollide(touchingRectangle));
    }

    @Test
    public void testDoesCollideWithCircleEdges() {
        // Test collision where two circles are just touching each other
        Circle touchingCircle = new Circle(20.0f, 10.0f, 5.0f);
        assertTrue(circle.doesCollide(touchingCircle));
    }

    @Test
    public void testSetScaleWithZeroRadius() {
        // Testing scaling when the circle has zero radius
        Circle zeroRadiusCircle = new Circle(10.0f, 10.0f, 0.0f);
        zeroRadiusCircle.setScale(new Point(0, 0), 2.0, 2.0);
        assertEquals(20.0f, zeroRadiusCircle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(20.0f, zeroRadiusCircle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testSetScaleRelativeToCircleCenter() {
        // Testing scaling with the circle's center as the origin
        circle.setScale(new Point(circle.getX(), circle.getY()), 3.0, 3.0);
        assertEquals(25.0f, circle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(25.0f, circle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideWithCircleInside() {
        // Test collision where another circle is completely inside the original circle
        Circle innerCircle = new Circle(10.0f, 10.0f, 3.0f);
        assertTrue(circle.doesCollide(innerCircle));
    }

    @Test
    public void testDoesCollideWithRectangleInside() {
        // Test collision where the rectangle is completely inside the circle
        Rectangle innerRectangle = new Rectangle(10.0f, 10.0f, 2.0f, 2.0f);
        assertTrue(circle.doesCollide(innerRectangle));
    }

    @Test
    public void testDoesCollideWithCircleHalfIntersect() {
        // Test collision where two circles partially intersect
        Circle intersectingCircle = new Circle(13.0f, 10.0f, 5.0f);
        assertTrue(circle.doesCollide(intersectingCircle));
    }

    @Test
    public void testDoesCollideWithRectangleHalfIntersect() {
        // Test collision where the rectangle and circle partially intersect
        Rectangle intersectingRectangle = new Rectangle(13.0f, 10.0f, 5.0f, 5.0f);
        assertTrue(circle.doesCollide(intersectingRectangle));
    }

    @Test
    public void testDoesCollideWithRectangleOutside() {
        // Test collision where the rectangle is completely outside the circle
        Rectangle outsideRectangle = new Rectangle(30.0f, 30.0f, 10.0f, 10.0f);
        assertFalse(circle.doesCollide(outsideRectangle));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeRadius() {
        // Test circle creation with a negative radius, expecting an
        // IllegalArgumentException
        new Circle(10.0f, 10.0f, -5.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleWithInfiniteScaleFactor() {
        // Testing scaling with an infinite scale factor
        circle.setScale(new Point(0, 0), Double.POSITIVE_INFINITY, 1.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetScaleWithNaNScaleFactor() {
        // Testing scaling with NaN as scale factor
        circle.setScale(new Point(0, 0), Double.NaN, Double.NaN);
    }

    @Test
    public void testSetRotationAroundSelf() {
        // Testing rotation with the circle's center as the origin
        Point center = new Point(circle.getX(), circle.getY());
        circle.setRotation(center, Math.PI / 2);
        assertEquals(5.0f, circle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(5.0f, circle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideWithCircleTouchingEdge() {
        // Testing collision where two circles are touching exactly at one point
        Circle touchingCircle = new Circle(15.0f, 10.0f, 5.0f);
        assertTrue(circle.doesCollide(touchingCircle));
    }
}