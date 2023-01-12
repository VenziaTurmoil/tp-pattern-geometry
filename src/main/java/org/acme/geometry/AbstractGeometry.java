package org.acme.geometry;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGeometry implements Geometry{

    private List<GeometryListener> listeners = new ArrayList<>();

    public String asText(){
        WktVisitor visitor = new WktVisitor();
        this.accept(visitor);
        return visitor.getResult();
    }

    public abstract AbstractGeometry clone();

    public Envelope getEnvelope(){
        EnvelopeBuilder visitor = new EnvelopeBuilder();
        this.accept(visitor);
        return visitor.build();
    }

    @Override
    public void addListener(GeometryListener listener){
        listeners.add(listener);
    }

    protected void triggerChange(){
        for (GeometryListener listener : listeners){
            listener.onChange(this);
        }
    }
}
