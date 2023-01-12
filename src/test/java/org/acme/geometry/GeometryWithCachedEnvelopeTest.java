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
}
