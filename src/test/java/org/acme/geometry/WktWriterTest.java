package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import javax.sound.sampled.Line;

public class WktWriterTest {

    @Test
    public void testWrite(){
        WktWriter writer = new WktWriter();

        Point P = GeometryTestFactory.getDefaultPoint();
        Assert.assertEquals("POINT (1.0 2.0)", writer.write(P));

        LineString LS = GeometryTestFactory.getDefaultLineString();
        Assert.assertEquals("LINESTRING (1.0 2.0, 10.0 12.0)", writer.write(LS));
    }
}
