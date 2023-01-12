package org.acme.geometry;

public abstract class AbstractGeometry implements Geometry{

    public String asText(){
        WktVisitor visitor = new WktVisitor();
        this.accept(visitor);
        return visitor.getResult();
    }

    public abstract AbstractGeometry clone();

    public Envelope getEnvelope(){
        EnvelopeBuilder visitor = new EnvelopeBuilder();
        this.accept((visitor));
        return visitor.build();
    }
}
