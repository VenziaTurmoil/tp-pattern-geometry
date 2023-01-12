package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class GeometryWriterTest {

    @Test
    public void testWriterWkt(){
        WktWriter writer = new WktWriter();

        Point point = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("POINT (1.0 2.0)\n", writer.write(point));

        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LINESTRING (1.0 2.0, 10.0 12.0)\n", writer.write(lineString));

        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();
        Assert.assertEquals("GEOMETRYCOLLECTION (LINESTRING (1.0 2.0, 10.0 12.0)\n" +
                ", POINT (0.0 4.0)\n" +
                ")\n", writer.write(geometryCollection));
    }

    @Test
    public void testWriterGeoJSON(){
        GeoJSONWriter writer = new GeoJSONWriter();

        Point point = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("Point: 1.0, 2.0\n", writer.write(point));

        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LineString: \n" +
                "Point: 1.0, 2.0\n" +
                "Point: 10.0, 12.0\n" +
                "\n", writer.write(lineString));

        GeometryCollection geometryCollection = GeometryTestFactory.getDefaultGeometryCollection();
        Assert.assertEquals("Collection: \n" +
                "LineString: \n" +
                "Point: 1.0, 2.0\n" +
                "Point: 10.0, 12.0\n" +
                "\n" +
                "Point: 0.0, 4.0\n" +
                "\n\n", writer.write(geometryCollection));
    }
}
