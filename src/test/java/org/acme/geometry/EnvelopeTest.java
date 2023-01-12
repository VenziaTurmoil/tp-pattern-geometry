package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class EnvelopeTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        Envelope envelope = new Envelope();
        Assert.assertTrue(
                Double.isNaN(envelope.getXmin()) &&
                        Double.isNaN(envelope.getYmin()) &&
                        Double.isNaN(envelope.getXmax()) &&
                        Double.isNaN(envelope.getYmax())
        );
    }

    @Test
    public void testParamsConstructor(){
        Envelope envelope = new Envelope(new Coordinate(1.0, 2.0), new Coordinate(3.0, 4.0));
        Assert.assertEquals(1.0, envelope.getXmin(), EPSILON);
        Assert.assertEquals(2.0, envelope.getYmin(), EPSILON);
        Assert.assertEquals(3.0, envelope.getXmax(), EPSILON);
        Assert.assertEquals(4.0, envelope.getYmax(), EPSILON);
    }

    @Test
    public void testIsEmpty(){
        Envelope envelope = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Assert.assertTrue(envelope.isEmpty());
    }

    @Test
    public void testEquals(){
        Envelope envelope1 = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Envelope envelope2 = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Assert.assertTrue(envelope1.equals(envelope2));

        Point point = new Point();
        Assert.assertFalse(envelope1.equals(point));
    }

}
