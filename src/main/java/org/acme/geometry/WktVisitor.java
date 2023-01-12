package org.acme.geometry;

public class WktVisitor implements GeometryVisitor{

    private StringBuilder buffer = new StringBuilder("");

    public String getResult(){
        return buffer.toString();
    }

    @Override
    public void visit (Point P){
        buffer.append("POINT (");
        buffer.append(P.getCoordinate().getX());
        buffer.append(" ");
        buffer.append(P.getCoordinate().getY());
        buffer.append(")\n");
    }

    @Override
    public void visit (LineString LS){
        buffer.append("LINESTRING (");
        for (int i = 0; i<LS.getNumPoints(); i++){
            buffer.append(LS.getPointN(i).getCoordinate().getX() + " ");
            buffer.append(LS.getPointN(i).getCoordinate().getY());
            if (i< LS.getNumPoints()-1){
                buffer.append(", ");
            }
        }
        buffer.append(")\n");
    }
}
