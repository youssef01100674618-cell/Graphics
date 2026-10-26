package graphicsLib.graphs.circles;

import graphicsLib.graphs.Point;

import java.util.ArrayList;

/*
Cartesian circle algorithm class that extends the concrete class Circle
 */
public class CartesianCircle extends Circle {
    int intXC = Math.toIntExact(Math.round(xc)); // integer x center
    int intYC = Math.toIntExact(Math.round(yc)); // integer y center
    public CartesianCircle( double r) {
        super(r) ;
    }
    public CartesianCircle(double r , double xc , double yc) {
        super(r,xc,yc);
    }

    public ArrayList<Point> pointsList() {
        int radius = Math.toIntExact(Math.round(r));
        ArrayList<Point> pointsList = new ArrayList<Point>() ;
        pointsList.add(new Point(r,0));
        for( int x = radius ; x >= -radius ; x-=1) {
            double initialY = Math.sqrt(Math.pow(radius , 2) -Math.pow(x , 2)) ;// y before rounding
            // adding a rounded version of y with its x and adding the centers to make a translation
            pointsList.add(new Point(x + intXC , Math.toIntExact(Math.round(initialY )) + intYC)); //final point
            // adding the reflected corresponding point ( the half of the circle below the x axis
            pointsList.add(new Point(x + intXC , -Math.toIntExact(Math.round(initialY )) + intYC));
        }
        return pointsList;
    }
}

// a method that return an arrayList of the points generated in the algorithm

