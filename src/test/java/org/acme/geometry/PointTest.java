package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static java.lang.Double.isNaN;

public class PointTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        Point point = new Point();
        Coordinate c = point.getCoordinate();
        Assert.assertTrue(isNaN(c.getX()));
        Assert.assertTrue(isNaN(c.getY()));
    }

    @Test
    public void testParamsConstructor(){
        Coordinate c = GeometryTestFactory.getDefaultCoordinate();
        Point point = new Point(c);
        Coordinate c2 = point.getCoordinate();
        Assert.assertEquals(c, c2);
    }

    @Test
    public void testGetType(){
        Point point = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("Point", point.getType());
    }

    @Test
    public void testIsEmpty(){
        Point point = new Point();
        Assert.assertTrue(point.isEmpty());
        Point point2 = GeometryTestFactory.getDefaultPoint();
        Assert.assertFalse(point2.isEmpty());
    }

    @Test
    public void testTranslateValid() throws Exception {
        Point point = GeometryTestFactory.getDefaultPoint();
        point.translate(10.0, -2.0);

        Assert.assertEquals(11.0, point.getCoordinate().getX(), EPSILON);
        Assert.assertEquals(0.0, point.getCoordinate().getY(), EPSILON);
    }

    @Test(expected = Exception.class)
    public void testTranslateInvalid() throws Exception {
        Point point = GeometryTestFactory.getDefaultPoint();
        point.translate(Double.NaN, 1.0);
    }

    @Test(expected = Exception.class)
    public void testTranslateEmpty() throws Exception{
        Point point = new Point();
        point.translate(1.0, 1.0);
    }

    @Test
    public void testIsEqual(){
        Point p1 = GeometryTestFactory.getDefaultPoint();
        Point p2 = new Point(new Coordinate(1.0, 2.0));
        Assert.assertTrue(p1.isEqual(p2));

        Point p3 = new Point(new Coordinate(2.0, 1.0));
        Assert.assertFalse(p1.isEqual(p3));

        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Assert.assertFalse(p1.isEqual(lineString));
    }

    @Test
    public void testClone(){
        Point point = GeometryTestFactory.getDefaultPoint();
        Point copy = point.clone();

        Assert.assertTrue(point.isEqual(copy));
    }

    @Test
    public void testGetEnvelope(){
        Point point = GeometryTestFactory.getDefaultPoint();
        Envelope envelope = point.getEnvelope();

        Assert.assertEquals(point.getCoordinate().getX(), envelope.getXmin(), EPSILON);
        Assert.assertEquals(point.getCoordinate().getX(), envelope.getXmax(), EPSILON);
        Assert.assertEquals(point.getCoordinate().getY(), envelope.getYmin(), EPSILON);
        Assert.assertEquals(point.getCoordinate().getY(), envelope.getYmax(), EPSILON);
    }

    @Test
    public void testAcceptLog() throws UnsupportedEncodingException {
        Point point = GeometryTestFactory.getDefaultPoint();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        LogGeometryVisitor visitor = new LogGeometryVisitor(out);
        point.accept(visitor);
        String result = os.toString("UTF8");

        Assert.assertEquals("Je suis un point avec x=1.0 et y=2.0\n", result);
    }

    @Test
    public void testAcceptWkt(){
        Point point = GeometryTestFactory.getDefaultPoint();

        WktVisitor visitor = new WktVisitor();
        point.accept(visitor);
        Assert.assertEquals( "POINT (1.0 2.0)\n", visitor.getResult() );
    }


}

