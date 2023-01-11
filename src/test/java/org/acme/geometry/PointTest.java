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

}

