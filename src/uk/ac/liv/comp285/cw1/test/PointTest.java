package uk.ac.liv.comp285.cw1.test;

import uk.ac.liv.comp285.cw1.shapes.Point;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PointTest {
    private Point point;

    @Before
    public void setUp() {
        // Set up before the test
        point = new Point(3.0f, 4.0f);
    }

    @After
    public void tearDown() {
        // Clean up after the test
        point = null;
    }

    @Test
    public void testGetX() {
        // Check if the x-coordinate of the point is 3.0
        assertEquals(3.0f, point.getX(), 0.0f);
    }

    @Test
    public void testGetY() {
        // Check if the y-coordinate of the point is 4.0
        assertEquals(4.0f, point.getY(), 0.0f);
    }

    @Test
    public void testSetX() {
        // Set the x-coordinate of the point to 7.0
        point.setX(7.0f);
        assertEquals(7.0f, point.getX(), 0.0f);
    }

    @Test
    public void testSetY() {
        // Set the y-coordinate of the point to 8.0
        point.setY(8.0f);
        assertEquals(8.0f, point.getY(), 0.0f);
    }

    @Test
    public void testRotateZeroAngle() {
        // Rotate the point by 0 degrees
        Point rotated = point.rotate(new Point(0, 0), 0.0);
        assertEquals(point.getX(), rotated.getX(), 0.0f);
        assertEquals(point.getY(), rotated.getY(), 0.0f);
    }

    @Test
    public void testRotateAroundOrigin90Degrees() {
        // Rotate the point by 90 degrees
        Point rotated = point.rotate(new Point(0, 0), Math.PI / 2);
        assertEquals(-4.0f, rotated.getX(), 0.001f);
        assertEquals(3.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundOriginNegative90Degrees() {
        // Rotate the point by -90 degrees
        Point rotated = point.rotate(new Point(0, 0), -Math.PI / 2);
        assertEquals(4.0f, rotated.getX(), 0.001f);
        assertEquals(-3.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundNonZeroOrigin() {
        // Rotate the point by 90 degrees around the point (1, 1)
        Point rotated = point.rotate(new Point(1, 1), Math.PI / 2);
        assertEquals(-2.0f, rotated.getX(), 0.001f);
        assertEquals(3.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundItself() {
        // Rotate the point by 45 degrees around itself
        Point rotated = point.rotate(point, Math.PI / 4);
        assertEquals(point.getX(), rotated.getX(), 0.001f);
        assertEquals(point.getY(), rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateWithLargeAngle() {
        // Rotate the point by large angle
        Point rotated = point.rotate(new Point(0, 0), 8 * Math.PI);
        assertEquals(point.getX(), rotated.getX(), 0.001f);
        assertEquals(point.getY(), rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateWithNegativeAngle() {
        // Rotate the point by negative angle
        Point rotated = point.rotate(new Point(0, 0), -2 * Math.PI);
        assertEquals(point.getX(), rotated.getX(), 0.001f);
        assertEquals(point.getY(), rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateZeroOriginZeroAngle() {
        // Rotate the point by 0 degrees around the origin so it should remain the same
        Point rotated = point.rotate(new Point(0, 0), 0.0);
        assertEquals(3.0f, rotated.getX(), 0.0f);
        assertEquals(4.0f, rotated.getY(), 0.0f);
    }

    @Test
    public void testRotateWithZeroPoint() {
        // Set a point (0, 0) and rotate it by 180 degrees
        Point zeroPoint = new Point(0, 0);
        Point rotated = zeroPoint.rotate(new Point(0, 0), Math.PI);
        assertEquals(0.0f, rotated.getX(), 0.0f);
        assertEquals(0.0f, rotated.getY(), 0.0f);
    }

    @Test
    public void testRotateAroundSamePoint() {
        // Rotate the point by 60 degrees around itself
        Point rotated = point.rotate(point, Math.PI / 3);
        assertEquals(point.getX(), rotated.getX(), 0.001f);
        assertEquals(point.getY(), rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateWithNullOrigin() {
        // Set the origin as null and rotate the point by 90 degrees
        Point rotated = point.rotate(null, Math.PI / 2);
        assertEquals(point.getX(), rotated.getX(), 0.001f);
        assertEquals(point.getY(), rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundOrigin360Degrees() {
        // Rotate the point by 360 degrees
        Point rotated = point.rotate(new Point(0, 0), 2 * Math.PI);
        assertEquals(3.0f, rotated.getX(), 0.001f);
        assertEquals(4.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundOriginXAxisZero() {
        // A point on the x-axis
        Point pointOnYAxis = new Point(0.0f, 5.0f);
        Point rotated = pointOnYAxis.rotate(new Point(0, 0), Math.PI / 2);
        assertEquals(-5.0f, rotated.getX(), 0.001f);
        assertEquals(0.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateAroundOriginYAxisZero() {
        // A point on the y-axis
        Point pointOnXAxis = new Point(5.0f, 0.0f);
        Point rotated = pointOnXAxis.rotate(new Point(0, 0), Math.PI / 2);
        assertEquals(0.0f, rotated.getX(), 0.001f);
        assertEquals(5.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateRelativePointXAxisZero() {
        Point origin = new Point(2.0f, 2.0f);
        // Its relative point has x = 0
        Point relativeXAxisZero = new Point(2.0f, 3.0f);
        Point rotated = relativeXAxisZero.rotate(origin, Math.PI / 2);
        assertEquals(1.0f, rotated.getX(), 0.001f);
        assertEquals(2.0f, rotated.getY(), 0.001f);
    }

    @Test
    public void testRotateRelativePointYAxisZero() {
        Point origin = new Point(2.0f, 2.0f);
        // Its relative point has y = 0
        Point relativeYAxisZero = new Point(3.0f, 2.0f);
        Point rotated = relativeYAxisZero.rotate(origin, Math.PI / 2);
        assertEquals(2.0f, rotated.getX(), 0.001f);
        assertEquals(3.0f, rotated.getY(), 0.001f);
    }
}
