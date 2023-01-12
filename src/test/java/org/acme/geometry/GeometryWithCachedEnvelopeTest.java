package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class GeometryWithCachedEnvelopeTest {

    @Test
    public void testConstructor(){
        Geometry point = GeometryTestFactory.getDefaultPoint();
        Envelope E1 = point.getEnvelope();
        point = new GeometryWithCachedEnvelope(point);
        Envelope E2 = point.getEnvelope();

        Assert.assertEquals(E1, E2);
    }

    @Test
    public void testOnChange() throws Exception {
        Geometry point = GeometryTestFactory.getDefaultPoint();
        point = new GeometryWithCachedEnvelope(point);
        point.addListener((GeometryListener) point);
        point.translate(1.0, 2.0);
        Envelope envelope = point.getEnvelope();
        String s = point.getType();

        Assert.assertEquals("Point With Cached Envelope", s);

        Envelope correctEnvelope = new Envelope(new Coordinate(2.0, 4.0), new Coordinate(2.0, 4.0));
        Assert.assertEquals(correctEnvelope, envelope);
    }
}
