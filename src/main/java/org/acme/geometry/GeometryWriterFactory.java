package org.acme.geometry;

public class GeometryWriterFactory {

    public GeometryWriterFactory(){}

    public GeometryWriter createGeometryWriter(String format) throws Exception{
        if(format == "WKT"){
            return new WktWriter();
        } else if(format == "GeoJSON"){
            return new GeoJSONWriter();
        }else {
            throw new Exception("Format not supported");
        }
    }
}
