package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class GeometryWithCachedEnvelopeTest {

    @Test
    public void testConstructor(){
        Geometry P = GeometryTestFactory.getDefaultPoint();
        Envelope E1 = P.getEnvelope();
        P = new GeometryWithCachedEnvelope(P);
        Envelope E2 = P.getEnvelope();

        Assert.assertEquals(E1, E2);
    }

    @Test
    public void testOnChange() throws Exception {
        Geometry P = GeometryTestFactory.getDefaultPoint();
        P = new GeometryWithCachedEnvelope(P);
        P.addListener((GeometryListener) P);
        P.translate(1.0, 2.0);
        Envelope E = P.getEnvelope();
        String s = P.getType();

        Assert.assertEquals("Point With Cached Envelope", s);

        Envelope correctEnvelope = new Envelope(new Coordinate(2.0, 4.0), new Coordinate(2.0, 4.0));
        Assert.assertEquals(correctEnvelope, E);
    }
}
