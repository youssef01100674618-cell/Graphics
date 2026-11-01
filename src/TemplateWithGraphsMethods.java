import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class TemplateWithGraphsMethods extends JFrame  implements GLEventListener {
    public static void main(String[] args) {
        new TemplateWithGraphsMethods();
    }
        double xC = 300;
        double yC = 150;

    public TemplateWithGraphsMethods() {
        this.setSize(600,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Graph App");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
        GLCanvas glCanvus= new GLCanvas();
        glCanvus.addGLEventListener(this);
        this.add(glCanvus,BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glViewport(0, 0, 600, 300);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0.0, 600.0, 0.0, 300.0, -1.0, 1.0);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(1.0f);
        gl.glBegin(GL.GL_POINTS);
        gl.glColor3f(1.0f,0.0f,0.0f); // color of points
        // invoke ur methods here
        setAxis(gl , 200,200);
        gl.glEnd();

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
    // tools

    private void setAxis(GL gl, double originX, double originY) {
        float red;
        float green;
        float blue;

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        // حفظ الحالة الحالية للشاشة
        gl.glPushMatrix();

        // تحريك نقطة الأصل (Origin) للنقطة اللي إنت عايزها
        gl.glTranslated(originX, originY, 0);

        //////////////////////////////
        // رسم محور X ومحور Y
        red = 0.0f;
        green = 0.2f;
        blue = 0.4f;
        gl.glColor3f(red, green, blue);

        gl.glLineWidth(2.0f);

        gl.glBegin(GL.GL_LINES);
        // line for y-axis
        gl.glVertex2d(0, 140);
        gl.glVertex2d(0, -140);

        // line for x-axis
        gl.glVertex2d(240, 0);
        gl.glVertex2d(-240, 0);
        gl.glEnd();

        /////////////////////
        // رسم الأسهم (Arrow heads)
        gl.glBegin(GL.GL_TRIANGLES);

        // أسهم محور Y
        gl.glVertex2d(0, 150);
        gl.glVertex2d(-5, 140);
        gl.glVertex2d(5, 140);

        gl.glVertex2d(0, -150);
        gl.glVertex2d(-5, -140);
        gl.glVertex2d(5, -140);

        // أسهم محور X
        gl.glVertex2d(250, 0);
        gl.glVertex2d(240, -5);
        gl.glVertex2d(240, 5);

        gl.glVertex2d(-250, 0);
        gl.glVertex2d(-240, -5);
        gl.glVertex2d(-240, 5);

        gl.glEnd();

        // إرجاع الشاشة لحالتها الأصلية
        gl.glPopMatrix();
    }

    public void  setPixel(GL gl ,double x , double y , double xC , double yC) {
        int myX = toInt(Math.round(x + xC));
        int myY = toInt( Math.round(y + yC));
        gl.glVertex2i(myX , myY);
    }
    public static int toInt(double value) {
        return (int) Math.round(value);
    }
    //axis
    public void verticalLine(GL gl , double X , int startY , int endY) {
        int x = toInt(X);
        int y =startY ;
        while( y <= endY ) {
            setPixel(gl , x , y , 0 , 0);
            y++;
        }
    }
    public void horizontalLine(GL gl , double Y , int startX , int endX ) {
        int y = toInt(Y);
        int x = startX ;
        while(x <= endX) {
            setPixel(gl , x , y , 0 , 0);
            x++;
        }
    }
    // sets axis on the canvus
    public void setAxis(GL gl , double originX , double originY , int width , int height) {
        int x = toInt(originX);
        int y = toInt(originY);
        verticalLine(gl , x , 0 , height);
        horizontalLine(gl , y , 0 , width);

    }
    // Circle methods

    public void cartesianCircle(GL gl ,int radius , int xc , int yc) {
        gl.glVertex2i(0 , radius);
        int r = toInt(radius);
        int xC = toInt(xc);
        int yC = toInt(yc);
        for(int x = radius; x >= -radius; x-=1) {
            int y =  (int)Math.round((Math.sqrt(Math.pow(r , 2) -Math.pow(x , 2)))) ;
            setPixel(gl,x , y , xc ,yc);
            setPixel(gl,x , -y ,xc  ,yc);

        }
    }
    public void polarCircle(GL gl , double radius , double xc , double yc) {
        int xC = (int) Math.round(xc);
        int yC = (int) Math.round(yc);
        for( double angle = 0; angle <= 360; angle += Math.toDegrees(1/radius)) {
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            setPixel(gl, x ,y , xc ,yc);
            setPixel(gl, x , -y , xc ,yc);

        }
    }
    //midpoint drawing algorithm methods
    public void midPointCircle(GL gl , double radius , double xc , double yc ) {
        int r = toInt(radius);
        gl.glVertex2i(0,r);
        int initialP = toInt(1 - radius); // initializing the first decision parameter
        int p = initialP; // p to be changed over iterations to avoid the confusion with initialP
        int x =0;
        int y = r;
        while(x <= y){
            if(p < 0) {
                p = p + 2 * x + 3;
                setEightOctants(gl , ++x , y , xc , yc);
            }
            else {
                p = p + 2 * x - 2 * y + 1;
                setEightOctants(gl , ++x , --y , xc , yc);
            }
        }

    }
    public  void setEightOctants(GL gl , double X , double Y , double xc , double yc) {
        int xC = toInt(xc);
        int yC = toInt(yc);
        int x = toInt(X);
        int y = toInt(Y);
        gl.glVertex2i(xC + x, yC + y);
        gl.glVertex2i(xC - x, yC + y);
        gl.glVertex2i(xC + x, yC - y);
        gl.glVertex2i(xC - x, yC - y);
        gl.glVertex2i(xC + y, yC + x);
        gl.glVertex2i(xC - y, yC + x);
        gl.glVertex2i(xC + y, yC - x);
        gl.glVertex2i(xC - y, yC - x);
    }

}
