package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

public class EnvelopeBuilderTest {

    public static final double EPSILON = 1.0e-15;

    @Test
    public void testDefaultConstructor(){
        EnvelopeBuilder EB = new EnvelopeBuilder();
        Envelope E = EB.build();
        Assert.assertTrue(
                Double.isNaN(E.getXmin()) &&
                        Double.isNaN(E.getYmin()) &&
                        Double.isNaN(E.getXmax()) &&
                        Double.isNaN(E.getYmax())
        );
    }

    @Test
    public void testInsert(){
        EnvelopeBuilder EB = new EnvelopeBuilder();
        EB.insert(new Coordinate(1.0, 2.0));
        EB.insert(new Coordinate(3.0, 4.0));
        EB.insert(new Coordinate(1.5, 3.5));
        Envelope E = EB.build();

        Assert.assertEquals(1.0, E.getXmin(), EPSILON);
        Assert.assertEquals(2.0, E.getYmin(), EPSILON);
        Assert.assertEquals(3.0, E.getXmax(), EPSILON);
        Assert.assertEquals(4.0, E.getYmax(), EPSILON);
    }
}
