package org.acme.geometry;

import java.io.PrintStream;

public class LogGeometryVisitor implements GeometryVisitor{

    PrintStream out;

    public LogGeometryVisitor(){
        this.out = System.out;
    }

    public LogGeometryVisitor(PrintStream out){
        this.out =  out;
    }

    @Override
    public void visit(Point point) {
        if (!point.isEmpty()) {
            String string = "Je suis un point avec x=";
            string += point.getCoordinate().getX() + " et y=";
            string += point.getCoordinate().getY();
            out.println(string);
        }
    }

    @Override
    public void visit(LineString lineString){
        if (!lineString.isEmpty()){
            out.println(
                    "Je suis une polyligne definie par " +
                            lineString.getNumPoints() +
                            " point(s)"
            );
        }
    }

    @Override
    public void visit(GeometryCollection geometryCollection){
        if (!geometryCollection.isEmpty()){
            out.println(
                    "Je suis une colection de " +
                            geometryCollection.getNumGeometries() +
                            " Geometrie(s)"
            );
        }
    }
}
