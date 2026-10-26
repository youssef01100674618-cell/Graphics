package graphicsLib.templates;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import java.awt.*;
/*
to use this class extend it  and override any method you want , this class places
a canvus onto the gui
dont override init for a default init
 */

public class TemplateWithGUlCanvus extends Template implements GLEventListener {
    public TemplateWithGUlCanvus(int width , int height ) {
        super(width , height);
        this.setVisible(false);
        GLCanvas glCanvus= new GLCanvas();
        glCanvus.addGLEventListener(this);
        this.add(glCanvus, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public TemplateWithGUlCanvus() {
        this(600,300);
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

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
