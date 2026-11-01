package Explainations;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.*;
import java.awt.*;

public class BasicsExplainations extends JFrame implements GLEventListener {
    public static void main(String[] args) {
        new BasicsExplainations();
    }
    public BasicsExplainations(){
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


        // TO MAKE A POINT YOU SHOULD GIVE IT A SIZE
        gl.glPointSize(13.0f);
        // AND A COLOR
        gl.glColor3f(1.0f,0.0f,0.0f); // RGB FORMAT (TAKES 3 FLOATS [0,1] AS ARGUMENTS )
        gl.glBegin(GL.GL_POINTS); // STARTING A POINT
        gl.glVertex2i(300,150);  // GIVING THE POINT LOCATION 150PX OF HEIGHT AND 300
        // OF WIDTH
        gl.glEnd();
        gl.glBegin(GL.GL_LINES); // STARTING LINES
        gl.glColor3f(1.0f,0.0f,0.0f); // LINE COLOR
        gl.glVertex2i(300,150); // INITIAL POINT
        gl.glVertex2i(500,150); // FINAL POINT
        gl.glEnd(); // ENDING LINES

        gl.glBegin(GL.GL_LINES);  // STARTING LINES
        //LINE 1 WITH A COLOR C
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex2i(300,150);
        gl.glVertex2i(500,150);
        //LINE 2 WITH THE SAME COLOR C
        gl.glVertex2i(300,160);
        gl.glVertex2i(500,160);
        //LINE3 WITH THE SAME COLOR
        gl.glVertex2i(300,170);
        gl.glVertex2i(500,170);
        //LINE 4 WITH DIFFERENT COLOR
        gl.glColor3f(0.0f,1.0f,0.0f);
        gl.glVertex2i(300,180);
        gl.glVertex2i(500,180);
        /*ALL OF THESE LINES ARE IN THE SAME BEGAIN AND END EACH LINE
          REQUIRES 2 INDICES FOR THE BEGAIN AND END POINTS
         */
        gl.glEnd(); // ENDING ALL LINES


        gl.glBegin(GL.GL_POLYGON); // STARTING ( FULL FILLED ) POLYGON
        // IT REQUIRES 6 INDICES
        gl.glColor3f(1.0f,0.0f,0.0f);
        gl.glVertex2i(580,180); //VERTIX 1
        gl.glVertex2i(590,150); //VERTIX 2
        gl.glVertex2i(580,280); //VERTIX 3
        gl.glVertex2i(550,280); //VERTIX 4
        gl.glVertex2i(540,150); //VERTIX 5
        gl.glVertex2i(500,180); //VERTIX 6
        gl.glEnd();




    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }
}
