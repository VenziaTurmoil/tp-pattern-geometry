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
        Assert.assertEquals(0, LS.getNumPoints());
    }

    @Test
    public void testParamsConstructor(){
        Point p1 = new Point();
        Point p2 = GeometryTestFactory.getDefaultPoint();
        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        LineString LS = new LineString(list);

        Assert.assertEquals(2, LS.getNumPoints());
        Assert.assertEquals(LS.getPointN(1), p2);
    }

    @Test
    public void testGetType(){
        LineString LS = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LineString", LS.getType());
    }

    @Test
    public void testIsEmpty(){
        LineString Empty = new LineString();
        Assert.assertTrue(Empty.isEmpty());
        LineString LS = GeometryTestFactory.getDefaultLineString();
        Assert.assertFalse(LS.isEmpty());
        Point P = new Point();
        List<Point> list = new ArrayList<>();
        list.add(P);
        LineString LS2 = new LineString(list);
        Assert.assertTrue(LS2.isEmpty());
    }

}
