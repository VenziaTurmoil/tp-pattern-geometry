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
    public void visit(Point P) {
        if (!P.isEmpty()) {
            String s = "Je suis un point avec x=";
            s += P.getCoordinate().getX() + " et y=";
            s += P.getCoordinate().getY();
            out.println(s);
        }
    }

    @Override
    public void visit(LineString LS){
        if (!LS.isEmpty()){
            out.println(
                    "Je suis une polyligne definie par " +
                            LS.getNumPoints() +
                            " point(s)"
            );
        }
    }
}
