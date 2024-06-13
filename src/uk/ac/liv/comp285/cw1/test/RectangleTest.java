package uk.ac.liv.comp285.cw1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.liv.comp285.cw1.shapes.Rectangle;
import uk.ac.liv.comp285.cw1.shapes.Point;
import uk.ac.liv.comp285.cw1.shapes.Circle;

public class RectangleTest {
    private Rectangle rectangle;

    @Before
    public void setUp() {
        rectangle = new Rectangle(10.0f, 10.0f, 5.0f, 3.0f);
    }

    @After
    public void tearDown() {
        rectangle = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeWidth() {
        // Test how the constructor handles negative width
        new Rectangle(10.0f, 10.0f, -5.0f, 3.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeHeight() {
        // Test how the constructor handles negative height
        new Rectangle(10.0f, 10.0f, 5.0f, -3.0f);
    }

    @Test
    public void testGetArea() {
        // Test the area of the rectangle
        assertEquals(15.0f, rectangle.getArea(), 0.001);
    }

    @Test
    public void testGetLowerLeftPoint() {
        // Test the lower left point of the rectangle
        Point lowerLeft = rectangle.getLowerLeftPoint();
        assertEquals(10.0f, lowerLeft.getX(), 0.001f);
        assertEquals(10.0f, lowerLeft.getY(), 0.001f);

        // Test different rotation angles
        rectangle.setRotation(new Point(0, 0), Math.PI / 4);
        lowerLeft = rectangle.getLowerLeftPoint();
        assertEquals(-2.121f, lowerLeft.getX(), 0.001f);
        assertEquals(14.142f, lowerLeft.getY(), 0.001f);
    }

    @Test
    public void testGetUpperRightPoint() {
        // Test the upper right point of the rectangle
        Point upperRight = rectangle.getUpperRightPoint();
        assertEquals(15.0f, upperRight.getX(), 0.001f);
        assertEquals(13.0f, upperRight.getY(), 0.001f);

        // Test different rotation angles
        rectangle.setRotation(new Point(0, 0), Math.PI / 4);
        upperRight = rectangle.getUpperRightPoint();
        assertEquals(3.535f, upperRight.getX(), 0.001f);
        assertEquals(19.798f, upperRight.getY(), 0.001f);
    }

    @Test
    public void testSetRotation() {
        // Test setting the rotation of the rectangle
        rectangle.setRotation(new Point(10, 10), Math.PI / 4);
        // 4 point of the rectangle is rotated by 45 degrees
        Point lowerLeft = rectangle.getLowerLeftPoint();
        Point upperRight = rectangle.getUpperRightPoint();
        assertEquals(7.878f, lowerLeft.getX(), 0.001f);
        assertEquals(10.0f, lowerLeft.getY(), 0.001f);
        assertEquals(13.535f, upperRight.getX(), 0.001f);
        assertEquals(15.656f, upperRight.getY(), 0.001f);
    }

    @Test
    public void testSetScale() {
        // Test negative scale factor
        try {
            rectangle.setScale(new Point(10, 10), -1, 1);
            fail("Expected IllegalArgumentException for negative scale factor");
        } catch (IllegalArgumentException e) {
            // Pass
        }

        // Test zero scale factor
        try {
            rectangle.setScale(new Point(10, 10), 0, 1);
            fail("Expected IllegalArgumentException for zero scale factor");
        } catch (IllegalArgumentException e) {
            // Pass
        }

        // Test scaling the rectangle
        rectangle.setScale(new Point(10, 10), 2.0, 2.0);
        assertEquals(20.0f, rectangle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(16.0f, rectangle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollide() {
        // Test collision with other shapes
        Rectangle otherRectangle = new Rectangle(12.0f, 12.0f, 3.0f, 3.0f);
        assertTrue(rectangle.doesCollide(otherRectangle));

        Circle circle = new Circle(15.0f, 15.0f, 2.0f);
        assertTrue(rectangle.doesCollide(circle));

        Rectangle nonCollidingRectangle = new Rectangle(50.0f, 50.0f, 10.0f, 10.0f);
        assertFalse(rectangle.doesCollide(nonCollidingRectangle));
    }

    @Test
    public void testGetAreaExtremeValues() {
        // Test area calculation for a rectangle with width or height of zero
        Rectangle zeroWidthRectangle = new Rectangle(10.0f, 10.0f, 0, 3.0f);
        assertEquals(0.0f, zeroWidthRectangle.getArea(), 0.001);

        Rectangle zeroHeightRectangle = new Rectangle(10.0f, 10.0f, 5.0f, 0);
        assertEquals(0.0f, zeroHeightRectangle.getArea(), 0.001);

        // Test area calculation for a large rectangle
        Rectangle largeRectangle = new Rectangle(10.0f, 10.0f, 10000.0f, 5000.0f);
        assertEquals(50000000.0f, largeRectangle.getArea(), 0.001);
    }

    @Test
    public void testSetScaleDifferentFactors() {
        // Test scaling only in one direction
        rectangle.setScale(new Point(10, 10), 2.0, 1.0);
        assertEquals(20.0f, rectangle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(13.0f, rectangle.getUpperRightPoint().getY(), 0.001f);

        // Test scaling by a smaller factor
        rectangle.setScale(new Point(10, 10), 0.5, 0.5);
        assertEquals(15.0f, rectangle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(11.5f, rectangle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideEdgeCases() {
        // Test edge cases where the rectangles just touch each other
        Rectangle touchingRectangle = new Rectangle(15.0f, 10.0f, 5.0f, 3.0f);
        assertTrue(rectangle.doesCollide(touchingRectangle));

        // Test edge cases where rectangles are partially overlapping
        Rectangle partialOverlapRectangle = new Rectangle(12.0f, 10.0f, 5.0f, 3.0f);
        assertTrue(rectangle.doesCollide(partialOverlapRectangle));

        // Test edge cases where rectangles do not overlap
        Rectangle nonCollidingRectangle = new Rectangle(20.0f, 20.0f, 5.0f, 3.0f);
        assertFalse(rectangle.doesCollide(nonCollidingRectangle));
    }

    @Test
    public void testSetRotationVariousAngles() {
        // Test rotation by 0 degrees (no rotation)
        rectangle.setRotation(new Point(0, 0), 0);
        assertEquals(10.0f, rectangle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(10.0f, rectangle.getLowerLeftPoint().getY(), 0.001f);

        // Test rotation by 180 degrees
        rectangle.setRotation(new Point(10, 10), Math.PI);
        assertEquals(5.0f, rectangle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(7.0f, rectangle.getLowerLeftPoint().getY(), 0.001f);

        // Test rotation by -45 degrees (negative angle)
        rectangle.setRotation(new Point(0, 0), -Math.PI / 4);
        assertEquals(-19.798f, rectangle.getLowerLeftPoint().getX(), 0.001f);
        assertEquals(-2.121f, rectangle.getLowerLeftPoint().getY(), 0.001f);
    }

    @Test
    public void testSetScaleSpecialCases() {
        // Test scaling by 1 (no scaling)
        rectangle.setScale(new Point(10, 10), 1.0, 1.0);
        assertEquals(15.0f, rectangle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(13.0f, rectangle.getUpperRightPoint().getY(), 0.001f);

        // Test scaling by a very small factor
        rectangle.setScale(new Point(10, 10), 0.01, 0.01);
        assertEquals(10.05f, rectangle.getUpperRightPoint().getX(), 0.001f);
        assertEquals(10.03f, rectangle.getUpperRightPoint().getY(), 0.001f);
    }

    @Test
    public void testDoesCollideContainment() {
        // Test if one rectangle completely contains another
        Rectangle smallerRectangle = new Rectangle(10.0f, 10.0f, 3.0f, 2.0f);
        assertTrue(rectangle.doesCollide(smallerRectangle));

        // Test if the same-sized rectangles overlap
        Rectangle sameSizeRectangle = new Rectangle(10.0f, 10.0f, 5.0f, 3.0f);
        assertTrue(rectangle.doesCollide(sameSizeRectangle));

        // Test if a rectangle is entirely outside another
        Rectangle distantRectangle = new Rectangle(50.0f, 50.0f, 5.0f, 3.0f);
        assertFalse(rectangle.doesCollide(distantRectangle));
    }

    @Test
    public void testSimpleRotationAndScaling() {
        // Test a simple rotation and scaling
        Rectangle rectangle = new Rectangle(0, 0, 10, 5);
        Point origin = new Point(0, 0);
        rectangle.setRotation(origin, Math.PI / 2);

        // Expected lower left and upper right points after rotation
        Point expectedLowerLeft = new Point(-5, 0);
        Point expectedUpperRight = new Point(0, 10);

        // Assert that the rotated points are as expected
        assertEquals(expectedLowerLeft.getX(), rectangle.getLowerLeftPoint().getX(), 0.001);
        assertEquals(expectedLowerLeft.getY(), rectangle.getLowerLeftPoint().getY(), 0.001);
        assertEquals(expectedUpperRight.getX(), rectangle.getUpperRightPoint().getX(), 0.001);
        assertEquals(expectedUpperRight.getY(), rectangle.getUpperRightPoint().getY(), 0.001);

        // Scale the rectangle by a factor of 2
        rectangle.setScale(origin, 2.0, 2.0);

        // Expected lower left and upper right points after scaling
        expectedLowerLeft = new Point(-10, 0);
        expectedUpperRight = new Point(0, 20);

        // Assert that the scaled points are as expected
        assertEquals(expectedLowerLeft.getX(), rectangle.getLowerLeftPoint().getX(), 0.001);
        assertEquals(expectedLowerLeft.getY(), rectangle.getLowerLeftPoint().getY(), 0.001);
        assertEquals(expectedUpperRight.getX(), rectangle.getUpperRightPoint().getX(), 0.001);
        assertEquals(expectedUpperRight.getY(), rectangle.getUpperRightPoint().getY(), 0.001);
    }

}
