package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class LineStringTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        LineString lineString = new LineString();
        Assert.assertEquals(0, lineString.getNumPoints());
    }

    @Test
    public void testParamsConstructor(){
        Point p1 = new Point();
        Point p2 = GeometryTestFactory.getDefaultPoint();
        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);

        LineString lineString = new LineString(list);

        Assert.assertEquals(2, lineString.getNumPoints());
        Assert.assertEquals(lineString.getPointN(1), p2);
    }

    @Test
    public void testGetType(){
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LineString", lineString.getType());
    }

    @Test
    public void testIsEmpty(){
        LineString Empty = new LineString();
        Assert.assertTrue(Empty.isEmpty());
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Assert.assertFalse(lineString.isEmpty());
        Point point = new Point();
        List<Point> list = new ArrayList<>();
        list.add(point);
        LineString lineString2 = new LineString(list);
        Assert.assertTrue(lineString2.isEmpty());
    }

    @Test
    public void testTranslateValid() throws Exception{
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        lineString.translate(1.0, -1.0);

        Assert.assertEquals(2.0, lineString.getPointN(0).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(1.0, lineString.getPointN(0).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(11.0, lineString.getPointN(1).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(11.0, lineString.getPointN(1).getCoordinate().getY(), EPSILON);
    }

    @Test(expected = Exception.class)
    public void testTranslateInvalid() throws Exception{
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        lineString.translate(Double.NaN, 1.0);
    }

    @Test(expected = Exception.class)
    public void testTranslateEmpty() throws Exception{
        LineString lineString = new LineString();
        lineString.translate(1.0, 1.0);
    }

    @Test
    public void testIsEqual(){
        LineString l1 = GeometryTestFactory.getDefaultLineString();
        LineString l2 = GeometryTestFactory.getDefaultLineString();
        Assert.assertTrue(l1.isEqual(l2));

        LineString l3 = new LineString();
        Assert.assertFalse(l1.isEqual(l3));

        Point point = GeometryTestFactory.getDefaultPoint();
        Assert.assertFalse(l1.isEqual(point));
    }

    @Test
    public void testClone(){
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        LineString copy = lineString.clone();

        Assert.assertTrue(lineString.isEqual(copy));
    }

    @Test
    public void testGetEnvelope(){
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Envelope envelope = lineString.getEnvelope();

        Assert.assertEquals(1.0, envelope.getXmin(), EPSILON);
        Assert.assertEquals(2.0, envelope.getYmin(), EPSILON);
        Assert.assertEquals(10.0, envelope.getXmax(), EPSILON);
        Assert.assertEquals(12.0, envelope.getYmax(), EPSILON);
    }

    @Test
    public void testAcceptLog() throws UnsupportedEncodingException {
        LineString lineString = GeometryTestFactory.getDefaultLineString();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        LogGeometryVisitor visitor = new LogGeometryVisitor(out);
        lineString.accept(visitor);
        String result = os.toString("UTF8");

        Assert.assertEquals("Je suis une polyligne definie par 2 point(s)\n", result);
    }

    @Test
    public void testAcceptWkt(){
        LineString lineString = GeometryTestFactory.getDefaultLineString();

        WktVisitor visitor = new WktVisitor();
        lineString.accept(visitor);
        Assert.assertEquals( "LINESTRING (1.0 2.0, 10.0 12.0)\n", visitor.getResult() );
    }

}
