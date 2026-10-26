package graphicsLib.graphs.circles;

/*
this class contains  methods of the circle algorithms
a method starting with plot indicates direct plotting with the Gl sent to it as the
first parameter
a method with the same name without plot indicates a return of the Point class
 */
public class Circle {
    public int r; //radius
    public double xc;
    public double yc;

    public Circle(double r) {
        this.r = (int)(Math.round(r));
    }

    public Circle(double r, double xc, double yc) {
        this.r = (int)(Math.round(r));
        this.xc = xc;
        this.yc = yc;
    }
}
