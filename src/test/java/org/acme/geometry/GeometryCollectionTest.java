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
        GeometryCollection geometryCollection = new GeometryCollection();

        Assert.assertTrue(geometryCollection.isEmpty());
        Assert.assertEquals(0, geometryCollection.getNumGeometries());
    }

    @Test
    public void testParamsConstructor(){
        List<Geometry> list = new ArrayList<>();
        list.add(GeometryTestFactory.getDefaultPoint());
        list.add(GeometryTestFactory.getDefaultLineString());

        GeometryCollection geometryCollection = new GeometryCollection(list);
        Assert.assertEquals("GeometryCollection", geometryCollection.getType());
        Assert.assertFalse(geometryCollection.isEmpty());
        Assert.assertEquals(2, geometryCollection.getNumGeometries());
        Assert.assertTrue(GeometryTestFactory.getDefaultLineString().isEqual(geometryCollection.getGeometryN(1)));
    }

    @Test
    public void testIsEqual(){
        GeometryCollection geometryCollection1 = GeometryTestFactory.getDefaultGeometryCollection();
        GeometryCollection geometryCollection2 = GeometryTestFactory.getDefaultGeometryCollection();
        Assert.assertTrue(geometryCollection1.isEqual(geometryCollection2));
    }

    @Test
    public void testClone(){
        GeometryCollection geometryCollection1 = GeometryTestFactory.getDefaultGeometryCollection();
        GeometryCollection geometryCollection2 = geometryCollection1.clone();
        Assert.assertTrue(geometryCollection1.isEqual(geometryCollection2));
    }

    @Test
    public void testAcceptLog() throws UnsupportedEncodingException {
        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(os);
        LogGeometryVisitor visitor = new LogGeometryVisitor(out);
        geometryCollection.accept(visitor);
        String result = os.toString("UTF8");

        Assert.assertEquals("Je suis une colection de 2 Geometrie(s)\n", result);
    }

    @Test
    public void testAcceptWkt(){
        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();

        WktVisitor visitor = new WktVisitor();
        geometryCollection.accept(visitor);
        Assert.assertEquals( "GEOMETRYCOLLECTION (LINESTRING (1.0 2.0, 10.0 12.0)\n" +
                ", POINT (0.0 4.0)\n" +
                ")\n", visitor.getResult() );
    }

    @Test
    public void testGetEnvelope(){
        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();
        Envelope envelope = geometryCollection.getEnvelope();

        Assert.assertEquals(0, envelope.getXmin(), EPSILON);
        Assert.assertEquals(2, envelope.getYmin(), EPSILON);
        Assert.assertEquals(10, envelope.getXmax(), EPSILON);
        Assert.assertEquals(12, envelope.getYmax(), EPSILON);
    }

    @Test
    public void testTranslate() throws Exception {
        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();
        geometryCollection.translate(100.0, 500.0);
        LineString lineString = (LineString) geometryCollection.getGeometryN(0);
        Point point = (Point) geometryCollection.getGeometryN(1);

        Assert.assertEquals(101.0, lineString.getPointN(0).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(502.0, lineString.getPointN(0).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(110.0, lineString.getPointN(1).getCoordinate().getX(), EPSILON);
        Assert.assertEquals(512.0, lineString.getPointN(1).getCoordinate().getY(), EPSILON);
        Assert.assertEquals(100.0, point.getCoordinate().getX(), EPSILON);
        Assert.assertEquals(504.0, point.getCoordinate().getY(), EPSILON);

    }

}
