package org.acme.geometry;

public class Point implements Geometry{

    private Coordinate coordinate;

    public Point(){
        this.coordinate = new Coordinate();
    }

    public Point(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate(){return this.coordinate;}

    @Override
    public String getType(){
        return "Point";
    }

    @Override
    public boolean isEmpty(){
        return coordinate.isEmpty();
    }

    @Override
    public void translate(double dx, double dy) throws Exception {
        if (new Coordinate(dx, dy).isEmpty()){
            throw new Exception("Invalid Parameters");
        }
        else if (!this.isEmpty()){
            double x = this.coordinate.getX();
            double y = this.coordinate.getY();
            this.coordinate = new Coordinate(x+dx, y+dy);
        }else{
            throw new Exception("Invalid Geometry: cant translate");
        }
    }

    @Override
    public boolean isEqual(Geometry geom) {
        if (geom instanceof Point) {
            return
                    (this.coordinate.getX() == ((Point) geom).coordinate.getX()
                            || this.coordinate.getY() == ((Point) geom).coordinate.getY());
        }
        else{ return false; }
    }

    @Override
    public Point clone(){
        double x = this.coordinate.getX();
        double y = this.coordinate.getY();

        return new Point(new Coordinate(x, y));
    }

    @Override
    public Envelope getEnvelope(){
        EnvelopeBuilder EB = new EnvelopeBuilder();
        EB.insert(this.coordinate);
        return EB.build();
    }
}
