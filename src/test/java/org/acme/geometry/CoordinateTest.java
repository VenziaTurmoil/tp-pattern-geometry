package org.acme.geometry;

import org.junit.Assert;
import org.junit.Test;

import static java.lang.Double.isNaN;

public class CoordinateTest {

	public static final double EPSILON = 1.0e-15;

	@Test
	public void testDefaultConstructor(){
		Coordinate c = new Coordinate();
		Assert.assertTrue(isNaN(c.getX()));
		Assert.assertTrue(isNaN(c.getY()));
	}

	@Test
	public void testParamsConstructor(){
		Coordinate c = new Coordinate(1.0,2.0);
		Assert.assertEquals(1.0, c.getX(), EPSILON);
		Assert.assertEquals(2.0, c.getY(), EPSILON);
	}

	@Test
	public void testIsEmpty(){
		Coordinate c = new Coordinate(1.0, Double.NaN);
		Assert.assertTrue(c.isEmpty());
		Coordinate c2 = GeometryTestFactory.getDefaultCoordinate();
		Assert.assertFalse(c2.isEmpty());
	}

}
