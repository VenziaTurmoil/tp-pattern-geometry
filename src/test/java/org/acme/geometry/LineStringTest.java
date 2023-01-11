package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LineStringTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        LineString LS = new LineString();
        Assert.assertEquals(0, LS.getNumPoints(), EPSILON);
    }

    @Test
    public void testParamsConstructor(){
        Point p1 = new Point();
        Point p2 = GeometryTestFactory.getDefaultPoint();
        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        LineString LS = new LineString(list);

        Assert.assertEquals(2, LS.getNumPoints(), EPSILON);
        Assert.assertEquals(10.0, LS.getPointN(1).getCoordinate().getX(), EPSILON);
    }

}
