package org.acme.geometry;

public interface Geometry {

    String getType();

    boolean isEmpty();

    void translate(double dx, double dy) throws Exception;

    boolean isEqual(Geometry geom);

    Geometry clone();

    Envelope getEnvelope();

    void accept(GeometryVisitor visitor);
}
