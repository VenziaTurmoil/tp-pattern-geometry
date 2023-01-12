package org.acme.geometry;

public class GeoJSONWriter implements GeometryWriter{

    //TODO: REECRIRE AVEC LE PATRON VISITOR

    @Override
    public String getName() {
        return "GeoJSON";
    }

    @Override
    public String write(Geometry geometry) {
        if (geometry instanceof Point){
            Point point = (Point) geometry;
            return "Point: " + point.getCoordinate().getX() + ", " + point.getCoordinate().getY() + "\n";
        } else if(geometry instanceof LineString){
            LineString lineString = (LineString) geometry;
            String result = "LineString: \n";
            for (int i=0; i<lineString.getNumPoints(); i++){
                result += write(lineString.getPointN(i));
            }
            return result + "\n";
        } else if(geometry instanceof GeometryCollection){
            GeometryCollection geometryCollection = (GeometryCollection) geometry;
            String result = "Collection: \n";
            for (int i=0; i<geometryCollection.getNumGeometries(); i++){
                result += write(geometryCollection.getGeometryN(i));
            }
            return result + "\n\n";
        } else {
            return "GeometryType not supported";
        }
    }
}
