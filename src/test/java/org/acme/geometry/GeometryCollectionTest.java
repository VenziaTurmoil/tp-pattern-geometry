package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class GeometryCollectionTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        GeometryCollection GC = new GeometryCollection();

        Assert.assertTrue(GC.isEmpty());
        Assert.assertEquals(0, GC.getNumGeometries());
    }

    @Test
    public void testParamsConstructor(){
        List<Geometry> list = new ArrayList<>();
        list.add(GeometryTestFactory.getDefaultPoint());
        list.add(GeometryTestFactory.getDefaultLineString());

        GeometryCollection GC = new GeometryCollection(list);
        Assert.assertEquals("GeometryCollection", GC.getType());
        Assert.assertFalse(GC.isEmpty());
        Assert.assertEquals(2, GC.getNumGeometries());
        Assert.assertTrue(GeometryTestFactory.getDefaultLineString().isEqual(GC.getGeometryN(1)));
    }

    @Test
    public void testIsEqual(){
        GeometryCollection GC1 = GeometryTestFactory.getDefaultGeometryCollection();
        GeometryCollection GC2 = GeometryTestFactory.getDefaultGeometryCollection();
        Assert.assertTrue(GC1.isEqual(GC2));
    }

    @Test
    public void testClone(){
        GeometryCollection GC1 = GeometryTestFactory.getDefaultGeometryCollection();
        GeometryCollection GC2 = GC1.clone();
        Assert.assertTrue(GC1.isEqual(GC2));
    }

    @Test
    public void testAcceptLog() throws UnsupportedEncodingException {
        GeometryCollection GC = GeometryTestFactory.getDefaultGeometryCollection();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        LogGeometryVisitor visitor = new LogGeometryVisitor(out);
        GC.accept(visitor);
        String result = os.toString("UTF8");

        Assert.assertEquals("Je suis une colection de 2 Geometrie(s)\n", result);
    }

    @Test
    public void testAcceptWkt(){
        GeometryCollection GC = GeometryTestFactory.getDefaultGeometryCollection();

        WktVisitor visitor = new WktVisitor();
        GC.accept(visitor);
        Assert.assertEquals( "GEOMETRYCOLLECTION (LINESTRING (1.0 2.0, 10.0 12.0)\n" +
                ", POINT (0.0 4.0)\n" +
                ")\n", visitor.getResult() );
    }

    @Test
    public void testGetEnvelope(){
        GeometryCollection GC = GeometryTestFactory.getDefaultGeometryCollection();
        Envelope E = GC.getEnvelope();

        Assert.assertEquals(0, E.getXmin(), EPSILON);
        Assert.assertEquals(2, E.getYmin(), EPSILON);
        Assert.assertEquals(10, E.getXmax(), EPSILON);
        Assert.assertEquals(12, E.getYmax(), EPSILON);
    }

    @Test
    public void testTranslate() throws Exception {
        GeometryCollection GC = GeometryTestFactory.getDefaultGeometryCollection();
        GC.translate(100.0, 500.0);
        LineString LS = (LineString) GC.getGeometryN(0);
        Point P = (Point) GC.getGeometryN(1);

        Assert.assertEquals(101.0, LS.getPointN(0).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(502.0, LS.getPointN(0).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(110.0, LS.getPointN(1).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(512.0, LS.getPointN(1).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(100.0, P.getCoordinate().getX(), EPSILON);
        Assert.assertEquals(504.0, P.getCoordinate().getY(), EPSILON);

    }

}
