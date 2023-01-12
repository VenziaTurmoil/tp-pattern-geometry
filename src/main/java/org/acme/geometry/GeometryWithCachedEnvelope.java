package org.acme.geometry;

public class GeometryWithCachedEnvelope implements Geometry{

    private Geometry original;
    private Envelope cachedEnvelope;

    public GeometryWithCachedEnvelope(Geometry original){
        this.original = original;
        this.cachedEnvelope = original.getEnvelope();
    }

    @Override
    public Envelope getEnvelope(){
        return this.cachedEnvelope;
    }

    @Override
    public void accept(GeometryVisitor visitor) {
        this.original.accept(visitor);
    }

    @Override
    public String getType() {
        return this.original.getType();
    }

    @Override
    public boolean isEmpty() {
        return this.original.isEmpty();
    }

    @Override
    public void translate(double dx, double dy) throws Exception {
         this.original.translate(dx, dy);
    }

    @Override
    public boolean isEqual(Geometry geom) {
        return this.original.isEqual(geom);
    }

    @Override
    public GeometryWithCachedEnvelope clone(){
        return new GeometryWithCachedEnvelope(original.clone());
    }
}
