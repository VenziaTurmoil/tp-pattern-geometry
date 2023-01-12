package org.acme.geometry;

public interface GeometryVisitor {

    void visit(Point P);
    void visit (LineString LS);
    void visit(GeometryCollection geometryCollection);
}
