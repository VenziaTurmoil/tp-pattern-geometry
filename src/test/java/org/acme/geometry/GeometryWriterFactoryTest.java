package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class GeometryWriterFactoryTest {

    @Test
    public void testGeometryWriterFactoryWKT() throws Exception {
        GeometryWriterFactory geometryWriterFactory = new GeometryWriterFactory();
        GeometryWriter geometryWriter = geometryWriterFactory.createGeometryWriter("WKT");
        Assert.assertEquals("WKT", geometryWriter.getName());
        Assert.assertEquals("POINT (1.0 2.0)\n", geometryWriter.write(new Point(new Coordinate(1.0, 2.0))));
    }

    @Test
    public void testGeometryWriterFactoryGeoJSON() throws Exception {
        GeometryWriterFactory geometryWriterFactory = new GeometryWriterFactory();
        GeometryWriter geometryWriter = geometryWriterFactory.createGeometryWriter("GeoJSON");
        Assert.assertEquals("GeoJSON", geometryWriter.getName());
        Assert.assertEquals("Point: 1.0, 2.0\n", geometryWriter.write(new Point(new Coordinate(1.0, 2.0))));
    }

    @Test(expected = Exception.class)
    public void testGeometryWriterFactoryInvalid() throws Exception {
        GeometryWriterFactory geometryWriterFactory = new GeometryWriterFactory();
        GeometryWriter geometryWriter = geometryWriterFactory.createGeometryWriter("INVALID");
    }
}
