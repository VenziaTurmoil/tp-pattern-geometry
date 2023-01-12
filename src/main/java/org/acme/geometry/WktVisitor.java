package org.acme.geometry;

public class WktVisitor implements GeometryVisitor{

    private StringBuilder buffer = new StringBuilder("");

    public String getResult(){
        return buffer.toString();
    }

    @Override
    public void visit (Point point){
        buffer.append("POINT (");
        buffer.append(point.getCoordinate().getX());
        buffer.append(" ");
        buffer.append(point.getCoordinate().getY());
        buffer.append(")\n");
    }

    @Override
    public void visit (LineString lineString){
        buffer.append("LINESTRING (");
        for (int i = 0; i<lineString.getNumPoints(); i++){
            buffer.append(lineString.getPointN(i).getCoordinate().getX() + " ");
            buffer.append(lineString.getPointN(i).getCoordinate().getY());
            if (i< lineString.getNumPoints()-1){
                buffer.append(", ");
            }
        }
        buffer.append(")\n");
    }

    @Override
    public void visit (GeometryCollection geometryCollection){
        buffer.append("GEOMETRYCOLLECTION (");
        for (int i=0; i<geometryCollection.getNumGeometries(); i++){
            geometryCollection.getGeometryN(i).accept(this);
            if (i<geometryCollection.getNumGeometries()-1){
                buffer.append(", ");
            }
        }
        buffer.append(")\n");
    }
}
