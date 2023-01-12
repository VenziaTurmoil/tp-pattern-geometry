package org.acme.geometry;

public class WktWriter {

    public String write(Geometry geom){
        if (geom.isEmpty()){
            throw new RuntimeException("Empty Geometry");
        }
        else if ( geom instanceof Point ){
            Point point = (Point)geom;
            String s = "POINT (";
            s += point.getCoordinate().getX() + " ";
            s += point.getCoordinate().getY() + ")";
            return s;
        }else if ( geom instanceof LineString ){
            LineString lineString = (LineString)geom;
            String s = "LINESTRING (";
            for (int i = 0; i<lineString.getNumPoints(); i++){
                s += lineString.getPointN(i).getCoordinate().getX() + " ";
                s += lineString.getPointN(i).getCoordinate().getY();
                if (i< lineString.getNumPoints()-1){
                    s += ", ";
                }
            }
            s += ")";
            return s;
        }else{
            throw new RuntimeException("geometry type not supported");
        }
    }
}
