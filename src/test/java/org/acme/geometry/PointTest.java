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
        Point P = new Point();
        Coordinate C = P.getCoordinate();
        Assert.assertTrue(isNaN(C.getX()));
        Assert.assertTrue(isNaN(C.getY()));
    }

    @Test
    public void testParamsConstructor(){
        Coordinate C = GeometryTestFactory.getDefaultCoordinate();
        Point P = new Point(C);
        Coordinate C2 = P.getCoordinate();
        Assert.assertEquals(C, C2);
    }

    @Test
    public void testGetType(){
        Point P = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("Point", P.getType());
    }

    @Test
    public void testIsEmpty(){
        Point P = new Point();
        Assert.assertTrue(P.isEmpty());
        Point P2 = GeometryTestFactory.getDefaultPoint();
        Assert.assertFalse(P2.isEmpty());
    }

    @Test
    public void testTranslateValid() throws Exception {
        Point P = GeometryTestFactory.getDefaultPoint();
        P.translate(10.0, -2.0);

        Assert.assertEquals(11.0, P.getCoordinate().getX(), EPSILON);
        Assert.assertEquals(0.0, P.getCoordinate().getY(), EPSILON);
    }

    @Test(expected = Exception.class)
    public void testTranslateInvalid() throws Exception {
        Point P = GeometryTestFactory.getDefaultPoint();
        P.translate(Double.NaN, 1.0);
    }

    @Test(expected = Exception.class)
    public void testTranslateEmpty() throws Exception{
        Point P = new Point();
        P.translate(1.0, 1.0);
    }

    @Test
    public void testIsEqual(){
        Point p1 = GeometryTestFactory.getDefaultPoint();
        Point p2 = new Point(new Coordinate(1.0, 2.0));
        Assert.assertTrue(p1.isEqual(p2));

        Point p3 = new Point(new Coordinate(2.0, 1.0));
        Assert.assertFalse(p1.isEqual(p3));

        LineString LS = GeometryTestFactory.getDefaultLineString();
        Assert.assertFalse(p1.isEqual(LS));
    }

    @Test
    public void testClone(){
        Point P = GeometryTestFactory.getDefaultPoint();
        Point copy = P.clone();

        Assert.assertTrue(P.isEqual(copy));
    }

    @Test
    public void testGetEnvelope(){
        Point P = GeometryTestFactory.getDefaultPoint();
        Envelope E = P.getEnvelope();

        Assert.assertEquals(P.getCoordinate().getX(), E.getXmin(), EPSILON);
        Assert.assertEquals(P.getCoordinate().getX(), E.getXmax(), EPSILON);
        Assert.assertEquals(P.getCoordinate().getY(), E.getYmin(), EPSILON);
        Assert.assertEquals(P.getCoordinate().getY(), E.getYmax(), EPSILON);
    }

    @Test
    public void testAcceptLog() throws UnsupportedEncodingException {
        Point P = GeometryTestFactory.getDefaultPoint();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        LogGeometryVisitor visitor = new LogGeometryVisitor(out);
        P.accept(visitor);
        String result = os.toString("UTF8");

        Assert.assertEquals("Je suis un point avec x=1.0 et y=2.0\n", result);
    }

    @Test
    public void testAcceptWkt(){
        Point P = GeometryTestFactory.getDefaultPoint();

        WktVisitor visitor = new WktVisitor();
        P.accept(visitor);
        Assert.assertEquals( "POINT (1.0 2.0)\n", visitor.getResult() );
    }


}

