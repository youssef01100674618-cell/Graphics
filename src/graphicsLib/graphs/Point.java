package graphicsLib.graphs;

public class Point {
     public int x ,y ;
    public Point(int x , int y) {
        this.x = x;
        this.y = y;
    }
    public Point(int x , int y , int xc , int yc ) {
        this(x , y);
        translatedTo(xc , yc);
    }
    public  Point reflectOverX() {
        return new Point(x , -y);
    }
    public  void translatedTo(int xc , int yc) {
         x = x+ xc ;
         y = y + yc ;
    }
    public  Point translateTo(int xc , int yc) {
        return new Point(x+ xc , y + yc);
    }
}
