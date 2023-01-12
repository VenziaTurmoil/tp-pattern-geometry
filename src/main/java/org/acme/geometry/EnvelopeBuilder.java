package org.acme.geometry;

public class EnvelopeBuilder {

    private double Xmin = Double.NaN;
    private double Ymin = Double.NaN;
    private double Xmax = Double.NaN;
    private double Ymax = Double.NaN;

    public void insert(Coordinate coordinate){
        if (!coordinate.isEmpty()) {
            if (Double.isNaN(Xmin) || coordinate.getX() < Xmin) {
                Xmin = coordinate.getX();
            }
            if (Double.isNaN(Ymin) || coordinate.getY() < Ymin) {
                Ymin = coordinate.getY();
            }
            if (Double.isNaN(Xmax) || coordinate.getX() > Xmax) {
                Xmax = coordinate.getX();
            }
            if (Double.isNaN(Ymax) || coordinate.getY() > Ymax) {
                Ymax = coordinate.getY();
            }
        }
    }

    public Envelope build(){
        Coordinate bottomLeft = new Coordinate(Xmin, Ymin);
        Coordinate topRight = new Coordinate(Xmax, Ymax);
        return new Envelope(bottomLeft, topRight);
    }
}
