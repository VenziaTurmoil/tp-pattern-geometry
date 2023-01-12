package org.acme.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryTestFactory {

    public static Coordinate getDefaultCoordinate(){
        return new Coordinate(1.0, 2.0);
    }

    public static Point getDefaultPoint(){
        Coordinate c1 = new Coordinate(1.0, 2.0);
        return new Point(c1);
    }

    public static LineString getDefaultLineString(){
        Coordinate c1 = new Coordinate(1.0, 2.0);
        Coordinate c2 = new Coordinate(10.0, 12.0);

        Point p1 = new Point(c1);
        Point p2 = new Point(c2);

        List<Point> list1 = new ArrayList<>();
        list1.add(p1);
        list1.add(p2);

        return new LineString(list1);
    }

    public static GeometryCollection getDefaultGeometryCollection(){
        LineString lineString = GeometryTestFactory.getDefaultLineString();
        Point point = new Point(new Coordinate(0.0, 4.0));

        List<Geometry> list = new ArrayList<>();
        list.add(lineString);
        list.add(point);

        return new GeometryCollection(list);
    }

}
