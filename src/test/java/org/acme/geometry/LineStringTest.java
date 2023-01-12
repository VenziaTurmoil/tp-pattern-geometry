package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import javax.sound.sampled.Line;
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

    @Test
    public void testTranslateValid() throws Exception{
        LineString LS = GeometryTestFactory.getDefaultLineString();
        LS.translate(1.0, -1.0);

        Assert.assertEquals(2.0, LS.getPointN(0).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(1.0, LS.getPointN(0).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(11.0, LS.getPointN(1).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(11.0, LS.getPointN(1).getCoordinate().getY(), EPSILON);
    }

    @Test(expected = Exception.class)
    public void testTranslateInvalid() throws Exception{
        LineString LS = GeometryTestFactory.getDefaultLineString();
        LS.translate(Double.NaN, 1.0);
    }

    @Test(expected = Exception.class)
    public void testTranslateEmpty() throws Exception{
        LineString LS = new LineString();
        LS.translate(1.0, 1.0);
    }

}
