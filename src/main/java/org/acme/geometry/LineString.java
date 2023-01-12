package org.acme.geometry;

import java.util.ArrayList;
import java.util.List;

public class LineString implements Geometry{

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
        if (points.size()==0){
            return true;
        }
        for (Point P : points){
            if (P.isEmpty()){
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
        }
    }
}
