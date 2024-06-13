package uk.ac.liv.comp285.cw1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import uk.ac.liv.comp285.cw1.shapes.Point;
import uk.ac.liv.comp285.cw1.shapes.RegularPolygon;

public class RegularPolygonTest {
    private RegularPolygon polygon;

    @Before
    public void setUp() {
        polygon = new RegularPolygon(5, new Point(0, 0), 5.0f);
    }

    @After
    public void tearDown() {
        polygon = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidSides() {
        // Test the case where the number of sides is less than 3
        new RegularPolygon(2, new Point(0, 0), 5.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeRadius() {
        // Test the case where the radius is negative
        new RegularPolygon(5, new Point(0, 0), -5.0f);
    }

    @Test
    public void testGetArea() {
        // Test the area of a regular pentagon with a radius of 5
        double expectedArea = 0.5 * 5 * 5.0 * 5.0 * Math.sin(2 * Math.PI / 5);
        assertEquals((float) expectedArea, polygon.getArea(), 0.001);
    }

    @Test
    public void testGetAreaZeroRadius() {
        // Test the area of a regular pentagon with a radius of 0
        RegularPolygon zeroRadiusPolygon = new RegularPolygon(5, new Point(0, 0), 0.0f);
        assertEquals(0.0f, zeroRadiusPolygon.getArea(), 0.001);
    }

    @Test
    public void testGetAreaHighSidesCount() {
        // Test the area of a regular polygon with a large number of sides
        RegularPolygon highSidesPolygon = new RegularPolygon(100, new Point(0, 0), 5.0f);
        double expectedArea = Math.PI * 5 * 5; // Approximate area of a circle with radius 5
        assertEquals((float) expectedArea, highSidesPolygon.getArea(), 0.1);
    }

    @Test
    public void testGetLowerLeftPoint() {
        // Test a regular polygon with 5 sides and a radius of 5
        polygon = new RegularPolygon(5, new Point(0, 0), 5.0f);
        Point lowerLeft = polygon.getLowerLeftPoint();

        assertEquals(-4.04f, lowerLeft.getX(), 0.1f);
        assertEquals(-4.75f, lowerLeft.getY(), 0.1f);

        // Test a regular polygon with 5 sides and a radius of 0
        RegularPolygon zeroRadiusPolygon = new RegularPolygon(5, new Point(0, 0), 0.0f);
        lowerLeft = zeroRadiusPolygon.getLowerLeftPoint();
        assertEquals(0.0f, lowerLeft.getX(), 0.1f);
        assertEquals(0.0f, lowerLeft.getY(), 0.1f);
    }

    @Test
    public void testGetUpperRightPoint() {
        // test a regular polygon with 5 sides and a radius of 5
        polygon = new RegularPolygon(5, new Point(0, 0), 5.0f);
        Point upperRight = polygon.getUpperRightPoint();

        assertEquals(5.0f, upperRight.getX(), 0.1f);
        assertEquals(4.75f, upperRight.getY(), 0.1f);

        // Test a regular polygon with 5 sides and a radius of 0
        RegularPolygon zeroRadiusPolygon = new RegularPolygon(5, new Point(0, 0), 0.0f);
        upperRight = zeroRadiusPolygon.getUpperRightPoint();
        assertEquals(0.0f, upperRight.getX(), 0.1f);
        assertEquals(0.0f, upperRight.getY(), 0.1f);
    }

    @Test
    public void testSetRotation() {
        // Test rotating the polygon by 45 degrees
        polygon.setRotation(new Point(0, 0), Math.PI / 4);
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();

        assertEquals(-4.93f, lowerLeft.getX(), 0.1f);
        assertEquals(-4.93f, lowerLeft.getY(), 0.1f);
        assertEquals(4.45f, upperRight.getX(), 0.1f);
        assertEquals(4.45f, upperRight.getY(), 0.1f);

        // Rotate point is set to the lower left corner of the polygon
        polygon.setRotation(polygon.getLowerLeftPoint(), Math.PI / 2);
        lowerLeft = polygon.getLowerLeftPoint();
        upperRight = polygon.getUpperRightPoint();

        assertEquals(-14.33f, lowerLeft.getX(), 0.1f);
        assertEquals(-4.93f, lowerLeft.getY(), 0.1f);
        assertEquals(0.0f, upperRight.getX(), 0.1f);
        assertEquals(4.45f, upperRight.getY(), 0.1f);
    }

    @Test
    public void testSetScale() {
        // Test scaling the polygon by a factor of 2
        polygon.setScale(new Point(0, 0), 2.0, 2.0);
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();
        // Expected coordinates after scaling
        assertEquals(-8.09f, lowerLeft.getX(), 0.1f);
        assertEquals(-9.51f, lowerLeft.getY(), 0.1f);
        assertEquals(10.0f, upperRight.getX(), 0.1f);
        assertEquals(9.51f, upperRight.getY(), 0.1f);

        // Test scaling the polygon scaleX = -1
        try {
            polygon.setScale(new Point(0, 0), -1, 1);
            fail("Expected IllegalArgumentException for negative scale factor");
        } catch (IllegalArgumentException e) {
            // Pass
        }

        // Test scaling the polygon scaleY = -1
        try {
            polygon.setScale(new Point(0, 0), 1, -1);
            fail("Expected IllegalArgumentException for negative scale factor");
        } catch (IllegalArgumentException e) {
            // Pass
        }

        // Test scaling the polygon with a zero scale factor
        try {
            polygon.setScale(new Point(0, 0), 0, 1);
            fail("Expected IllegalArgumentException for zero scale factor");
        } catch (IllegalArgumentException e) {
            // Pass
        }
    }

    @Test
    public void testDoesCollide() {
        // Test collision with another regular polygon
        RegularPolygon otherPolygon = new RegularPolygon(5, new Point(2, 2), 5.0f);
        assertTrue(polygon.doesCollide(otherPolygon));

        // Test collision with itself
        assertTrue(polygon.doesCollide(polygon));

        // Test collision with a circle
        RegularPolygon farPolygon = new RegularPolygon(5, new Point(50, 50), 5.0f);
        assertFalse(polygon.doesCollide(farPolygon));

        // Test collision with a circle
        RegularPolygon nearPolygon = new RegularPolygon(5, new Point(4, 4), 5.0f);
        assertTrue(polygon.doesCollide(nearPolygon));
    }

    @Test
    public void testSquare() {
        // Create a square with a side length of 400 and a center at (500, 500)
        RegularPolygon square = new RegularPolygon(4, new Point(500, 500), 200);

        // Set the rotation origin and angle
        square.setRotation(new Point(500, 500), Math.PI / 4);

        // Expected lower left and upper right points after rotation
        Point expectedLowerLeft = new Point(358.6f, 358.6f);
        Point expectedUpperRight = new Point(641.4f, 641.4f);

        Point lowerLeft = square.getLowerLeftPoint();
        Point upperRight = square.getUpperRightPoint();

        assertEquals(expectedLowerLeft.getX(), lowerLeft.getX(), 0.1f);
        assertEquals(expectedLowerLeft.getY(), lowerLeft.getY(), 0.1f);
        assertEquals(expectedUpperRight.getX(), upperRight.getX(), 0.1f);
        assertEquals(expectedUpperRight.getY(), upperRight.getY(), 0.1f);

        // Set the scale origin and scale factors
        square.setScale(new Point(0, 0), 2.0, 2.0);
        Point expectedLowerLeftScaled = new Point(717.2f, 717.2f);
        Point expectedUpperRightScaled = new Point(1282.8f, 1282.8f);

        Point lowerLeftScaled = square.getLowerLeftPoint();
        Point upperRightScaled = square.getUpperRightPoint();

        assertEquals(expectedLowerLeftScaled.getX(), lowerLeftScaled.getX(), 0.1f);
        assertEquals(expectedLowerLeftScaled.getY(), lowerLeftScaled.getY(), 0.1f);
        assertEquals(expectedUpperRightScaled.getX(), upperRightScaled.getX(), 0.1f);
        assertEquals(expectedUpperRightScaled.getY(), upperRightScaled.getY(), 0.1f);
    }

    @Test
    public void testRotationCollide() {
        // Create two regular pentagons with a side length of 5
        RegularPolygon polygon1 = new RegularPolygon(5, new Point(0, 0), 5.0f);
        RegularPolygon polygon2 = new RegularPolygon(5, new Point(5, 5), 5.0f);

        assertTrue(polygon1.doesCollide(polygon2));

        // After rotating, they should still collide
        polygon2.setRotation(new Point(5, 5), Math.PI / 4);
        assertTrue(polygon1.doesCollide(polygon2));
    }

    @Test
    public void testScaledBoundaryPoints() {
        // Test scaling the polygon by a factor of 2
        polygon.setScale(new Point(0, 0), 2.0, 2.0);

        // The lower left and upper right points should be outside the original boundary
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();
        assertTrue(lowerLeft.getX() < -7.0f);
        assertTrue(lowerLeft.getY() < -7.0f);
        assertTrue(upperRight.getX() > 7.0f);
        assertTrue(upperRight.getY() > 7.0f);
    }

    @Test
    public void testDifferentRotationOrigins() {
        // Test rotating the polygon by 45 degrees with a rotation origin of (-5, -5)
        polygon.setRotation(new Point(-5, -5), Math.PI / 4);
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();

        assertEquals(-9.9f, lowerLeft.getX(), 0.1f);
        assertEquals(-2.86f, lowerLeft.getY(), 0.1f);
        assertEquals(-0.1f, upperRight.getX(), 0.1f);
        assertEquals(6.52f, upperRight.getY(), 0.1f);
    }

    @Test
    public void testThreeSidedPolygon() {
        // Test a regular triangle with a side length of 5
        RegularPolygon triangle = new RegularPolygon(3, new Point(0, 0), 5.0f);
        double expectedArea = (5.0 * 5.0 * Math.sqrt(3)) / 4 * 3;
        assertEquals((float) expectedArea, triangle.getArea(), 0.1);
    }

    @Test
    public void testInvalidPolygonWithZeroRadius() {
        // In this case, the polygon will have zero area
        RegularPolygon invalidPolygon = new RegularPolygon(3, new Point(0, 0), 0.0f);
        assertEquals(0.0f, invalidPolygon.getArea(), 0.1f);
    }

    @Test
    public void testGetLowerLeftPointAfterRotation() {
        // Test lower left point after rotating polygon
        polygon.setRotation(new Point(0, 0), Math.PI / 3);
        Point lowerLeft = polygon.getLowerLeftPoint();
        assertNotNull(lowerLeft);
    }

    @Test
    public void testGetUpperRightPointAfterRotation() {
        // Test upper right point after rotating polygon
        polygon.setRotation(new Point(0, 0), Math.PI / 3);
        Point upperRight = polygon.getUpperRightPoint();
        assertNotNull(upperRight);
    }

    @Test
    public void testSetScaleWithHighScaleFactor() {
        // Test scaling polygon with a high scale factor
        polygon.setScale(new Point(0, 0), 100.0, 100.0);
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();
        assertEquals(-404.50f, lowerLeft.getX(), 0.1f);
        assertEquals(-475.52f, lowerLeft.getY(), 0.1f);
        assertEquals(500.00f, upperRight.getX(), 0.1f);
        assertEquals(475.52f, upperRight.getY(), 0.1f);
    }

    @Test
    public void testSetRotationToItself() {
        // Test rotating the polygon to itself
        polygon.setRotation(new Point(polygon.getLowerLeftPoint().getX(), polygon.getLowerLeftPoint().getY()), 0);
        Point lowerLeft = polygon.getLowerLeftPoint();
        Point upperRight = polygon.getUpperRightPoint();
        assertNotNull(lowerLeft);
        assertNotNull(upperRight);
    }

}
