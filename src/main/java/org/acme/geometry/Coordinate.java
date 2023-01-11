package org.acme.geometry;

import static java.lang.Double.isNaN;

public class Coordinate {

    private double x;
    private double y;

    public Coordinate(){
        this.x = Double.NaN;
        this.y = Double.NaN;
    }

    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isEmpty(){
        return isNaN(x) || isNaN(y);
    }
}
