package org.acme.geometry;

import java.util.ArrayList;
import java.util.List;

public class LineString extends AbstractGeometry {

    private List<Point> points;

    public LineString(){
        this.points = new ArrayList<>();
    }

    public LineString(List<Point> points){
        this.points = points;
    }

    public int getNumPoints(){
        return points.size();
    }

    public Point getPointN(int n){
        return points.get(n);
    }

    @Override
    public String getType(){
        return "LineString";
    }

    @Override
    public boolean isEmpty(){
        if (points.isEmpty()){
            return true;
        }
        for (Point point : points){
            if (point.isEmpty()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void translate(double dx, double dy) throws Exception{
        if (new Coordinate(dx, dy).isEmpty()){
            throw new Exception("Invalid Parameters");
        }
        else if (this.isEmpty()){
            throw new Exception("Invalid Geometry: cant translate");
        }else{
            for (Point p : points){
                p.translate(dx, dy);
            }
            this.triggerChange();
        }
    }

    @Override
    public boolean isEqual(Geometry geom) {
        if (geom instanceof LineString) {
            if (this.getNumPoints()==((LineString) geom).getNumPoints()) {
                boolean test = true;
                for (int i = 0; i < this.getNumPoints(); i++) {
                    test = this.getPointN(i).isEqual(
                            ((LineString) geom).getPointN(i)
                    );
                }
                return test;
            }
            else { return false; }
        }
        else{ return false; }
    }


    @Override
    public LineString clone(){
        List<Point> list = new ArrayList<>();
        for (Point p : points){
            list.add(p.clone());
        }
        return new LineString(list);
    }

    @Override
    public void accept(GeometryVisitor visitor){
        visitor.visit(this);
    }
}
