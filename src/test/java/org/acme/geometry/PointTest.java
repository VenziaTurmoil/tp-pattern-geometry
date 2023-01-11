package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        Point P = new Point();
        Coordinate C = P.getCoordinate();
        Assert.assertEquals(0.0, C.getX(), EPSILON);
        Assert.assertEquals(0.0, C.getY(), EPSILON);
    }

    @Test
    public void testParamsConstructor(){
        Coordinate C = GeometryTestFactory.getDefaultCoordinate();
        Point P = new Point(C);
        Coordinate C2 = P.getCoordinate();
        Assert.assertEquals(C.getX(), C2.getX(), EPSILON);
        Assert.assertEquals(C.getY(), C2.getY(), EPSILON);
    }

}

