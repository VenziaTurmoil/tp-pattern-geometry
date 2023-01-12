package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class AbstractGeometryTest {

    @Test
    public void testAsText(){
        Point P = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("POINT (1.0 2.0)\n", P.asText() );

        LineString LS = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LINESTRING (1.0 2.0, 10.0 12.0)\n", LS.asText() );
    }
}
