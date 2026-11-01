package Explainations;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class BasicsExplainations2 extends JFrame implements GLEventListener {
    public static void main(String[] args) {
        new BasicsExplainations2();
    }
    public BasicsExplainations2(){
        this.setSize(600,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Graph App");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
        GLCanvas glCanvus= new GLCanvas();
        glCanvus.addGLEventListener(this);
        this.add(glCanvus,BorderLayout.CENTER);
        this.setVisible(true);
        JPanel panel = new JPanel(new FlowLayout()); // flow layout : elements are added in a linear order

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
        gl.glPushMatrix(); //STARTS IT BEFORE SCALING , ROTATING OR TRANSLATING
        // CODE THAT INCLUDES NORMAL GRAPHING IN ADDITION TO SCALING , TRANSLATING , AND ROTATING
        gl.glScaled(2,3,4); // glScaled(scale in x , scale in y , scale in z = 1 ( always one (we dont need it )) )
        gl.glTranslated(2,3,0); //same idea but z
        gl.glRotated(40,0 ,0,0); // v : rotation degrees , (v1, v2 , v3) =(x , y, z)
                                              // where x,y, and z are the rotation vector
        gl.glPopMatrix(); // END MATRIX




    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
