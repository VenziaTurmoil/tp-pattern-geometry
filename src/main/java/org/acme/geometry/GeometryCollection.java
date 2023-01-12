package org.acme.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryCollection extends AbstractGeometry{

    private List<Geometry> geometryList;

    public GeometryCollection(){
        this.geometryList = new ArrayList<>();
    }

    public GeometryCollection(List<Geometry> geometryList){
        this.geometryList = geometryList;
    }

    public int getNumGeometries(){
        return geometryList.size();
    }

    public Geometry getGeometryN(int n){
        return geometryList.get(n);
    }

    @Override
    public String getType() {
        return "GeometryCollection";
    }

    @Override
    public boolean isEmpty() {
        if (this.geometryList.isEmpty()){
            return true;
        }else{
            for (Geometry geom : this.geometryList){
                if (geom.isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void translate(double dx, double dy) throws Exception {
        for (Geometry geom : this.geometryList){
            geom.translate(dx, dy);
        }
        this.triggerChange();
    }

    @Override
    public boolean isEqual(Geometry geom) {
        if (geom instanceof GeometryCollection){
            GeometryCollection GC = (GeometryCollection) geom;
            if (this.getNumGeometries() == GC.getNumGeometries()){
                for (int i=0; i<this.getNumGeometries(); i++){
                    if (! this.getGeometryN(i).isEqual(GC.getGeometryN(i))){
                        return false;
                    }
                }
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Override
    public GeometryCollection clone() {
        List<Geometry> list = new ArrayList<>();
        for (Geometry geom : this.geometryList){
            list.add(geom.clone());
        }
        return new GeometryCollection(list);
    }

    @Override
    public void accept(GeometryVisitor visitor) {
        visitor.visit(this);
    }
}
