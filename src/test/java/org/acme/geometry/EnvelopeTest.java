package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class EnvelopeTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        Envelope E = new Envelope();
        Assert.assertTrue(
                Double.isNaN(E.getXmin()) &&
                        Double.isNaN(E.getYmin()) &&
                        Double.isNaN(E.getXmax()) &&
                        Double.isNaN(E.getYmax())
        );
    }

    @Test
    public void testParamsConstructor(){
        Envelope E = new Envelope(new Coordinate(1.0, 2.0), new Coordinate(3.0, 4.0));
        Assert.assertEquals(1.0, E.getXmin(), EPSILON);
        Assert.assertEquals(2.0, E.getYmin(), EPSILON);
        Assert.assertEquals(3.0, E.getXmax(), EPSILON);
        Assert.assertEquals(4.0, E.getYmax(), EPSILON);
    }

    @Test
    public void testIsEmpty(){
        Envelope E = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Assert.assertTrue(E.isEmpty());
    }

    @Test
    public void testEquals(){
        Envelope E1 = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Envelope E2 = new Envelope(new Coordinate(), new Coordinate(3.0, 4.0));
        Assert.assertTrue(E1.equals(E2));

        Point P = new Point();
        Assert.assertFalse(E1.equals(P));
    }

}
