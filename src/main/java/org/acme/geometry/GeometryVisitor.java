package org.acme.geometry;

public interface GeometryVisitor {

    void visit(Point point);
    void visit (LineString lineString);
    void visit(GeometryCollection geometryCollection);
}
