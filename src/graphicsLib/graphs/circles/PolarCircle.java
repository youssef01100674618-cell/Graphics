package graphicsLib.graphs.circles;

import graphicsLib.graphs.Point;

import java.util.ArrayList;

public class PolarCircle extends Circle{
    double increment;
    double  angle ;
    public PolarCircle(double radius) {
        super(radius);
        increment = 1/ radius ;
        this.angle = 0;
    }
    public ArrayList<Point> pointsList() {
        ArrayList<Point> pointsList = new ArrayList<>();
        for(; angle <= 2 * Math.PI ; angle+= increment ) {
            int x = (int)Math.round(r* Math.cos(angle));
            int y = (int)Math.round(r * Math.sin(angle));
            pointsList.add(new Point(x , y));
            pointsList.add(new Point(x , -y));
        }
        return pointsList;
    }
}
