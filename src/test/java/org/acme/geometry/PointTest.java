package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

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

}

