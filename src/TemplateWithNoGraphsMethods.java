import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class TemplateWithNoGraphsMethods extends JFrame implements GLEventListener {
    public static void main(String[] args) {
        new TemplateWithGraphsMethods();
    }

    double xC = 300;
    double yC = 150;

    public TemplateWithNoGraphsMethods() {
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Graph App");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
        GLCanvas glCanvus = new GLCanvas();
        glCanvus.addGLEventListener(this);
        this.add(glCanvus, BorderLayout.CENTER);
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
        gl.glColor3f(1.0f, 0.0f, 0.0f); // color of points
        // invoke ur methods here
        gl.glEnd();

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}