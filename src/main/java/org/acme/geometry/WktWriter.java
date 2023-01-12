package org.acme.geometry;

public class WktWriter implements GeometryWriter{

    @Override
    public String getName() {
        return "WKT";
    }

    public String write(Geometry geom) {
        WktVisitor visitor = new WktVisitor();
        geom.accept(visitor);
        return visitor.getResult();
    }
}
