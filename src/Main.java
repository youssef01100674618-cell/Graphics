import graphicsLib.graphs.Point;
import graphicsLib.graphs.circles.PolarCircle;
import graphicsLib.templates.TemplateWithGUlCanvus;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Main extends TemplateWithGUlCanvus implements GLEventListener {
    public static void main(String[] args) {
        new Main();
    }

    public Main() { super(400,400);}


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        GL gl = glAutoDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glColor3f(1.0f,0.4f,0.2f);
        gl.glBegin(GL.GL_POINTS);
        for( Point point :new PolarCircle(100).pointsList()) {
            point.translatedTo(200,200);
            System.out.println(point.x + "  "+ point.y);
            gl.glVertex2i(point.x , point.y);

            }
        gl.glEnd();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {
    }
}
