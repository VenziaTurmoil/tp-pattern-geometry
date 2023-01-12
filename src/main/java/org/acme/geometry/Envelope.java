package org.acme.geometry;

public class Envelope {

    private Coordinate bottomLeft;
    private Coordinate topRight;

    public Envelope(){
        this.bottomLeft = new Coordinate();
        this.topRight = new Coordinate();
    }

    public Envelope(Coordinate bottomLeft, Coordinate topRight){
        //Todo: Check whether bottomLeft is really at the bottom left of topRight
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public boolean isEmpty(){
        return this.bottomLeft.isEmpty() || this.topRight.isEmpty();
    }

    public double getXmin(){
        return this.bottomLeft.getX();
    }

    public double getYmin(){
        return this.bottomLeft.getY();
    }

    public double getXmax(){
        return this.topRight.getX();
    }

    public double getYmax(){
        return this.topRight.getY();
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        } else
            // null check
            if (o == null) {
                return false;
            } else
                // type check and cast
                if (getClass() != o.getClass()) {
                    return false;
                } else {
                    final Envelope envelope = (Envelope) o;
                    // field comparison
                    return
                            this.getXmin() == envelope.getXmin() ||
                                    this.getYmin() == envelope.getYmin() ||
                                    this.getXmax() == envelope.getXmax() ||
                                    this.getYmax() == envelope.getYmax()
                    ;
                }
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
