package Tasks;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class LineDrawingListener implements GLEventListener {

    // Endpoints entered by the user
    private int x1 = -200;
    private int y1 = -100;
    private int x2 = 200;
    private int y2 = 100;

    // Update the endpoints when the user clicks Redraw
    public void setLineEndpoints(
            int x1, int y1, int x2, int y2) {

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    // Initialize OpenGL
    @Override
    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();

        // Black background
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        gl.glViewport(0, 0, 900, 650);

        // Set up the 2D coordinate system
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluOrtho2D(-450, 450, -300, 300);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    // Draw the complete scene
    @Override
    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

        // Draw the grid first
        drawGrid(gl);

        // Draw the axes over the grid
        drawAxes(gl);

        // Slope-intercept: red
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glPointSize(3.0f);

        gl.glBegin(GL.GL_POINTS);
        slopeIntercept(gl, x1, y1, x2, y2);
        gl.glEnd();

        // DDA: green, shifted 5 units vertically
        gl.glColor3f(0.0f, 1.0f, 0.0f);

        gl.glBegin(GL.GL_POINTS);
        dda(gl, x1, y1 + 5, x2, y2 + 5);
        gl.glEnd();

        // Bresenham: yellow, shifted 10 units vertically
        gl.glColor3f(1.0f, 1.0f, 0.0f);

        gl.glBegin(GL.GL_POINTS);
        bresenham(gl, x1, y1 + 10, x2, y2 + 10);
        gl.glEnd();

        gl.glFlush();
    }

    // Draw the grid
    private void drawGrid(GL gl) {

        gl.glColor3f(0.20f, 0.20f, 0.20f);
        gl.glLineWidth(1.0f);

        gl.glBegin(GL.GL_LINES);

        // Vertical grid lines
        for (int x = -450; x <= 450; x += 25) {
            gl.glVertex2i(x, -300);
            gl.glVertex2i(x, 300);
        }

        // Horizontal grid lines
        for (int y = -300; y <= 300; y += 25) {
            gl.glVertex2i(-450, y);
            gl.glVertex2i(450, y);
        }

        gl.glEnd();
    }

    // Draw the coordinate axes
    private void drawAxes(GL gl) {

        gl.glColor3f(0.0f, 0.4f, 1.0f);
        gl.glLineWidth(2.0f);

        gl.glBegin(GL.GL_LINES);

        // X-axis
        gl.glVertex2i(-450, 0);
        gl.glVertex2i(450, 0);

        // Y-axis
        gl.glVertex2i(0, -300);
        gl.glVertex2i(0, 300);

        gl.glEnd();
    }

    // Algorithm 1: Slope-intercept
    private void slopeIntercept(
            GL gl, int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        // Handle a single point
        if (dx == 0 && dy == 0) {
            gl.glVertex2i(x1, y1);
            return;
        }

        // Use x as the independent variable for shallow lines
        if (Math.abs(dx) >= Math.abs(dy)) {

            double m = (double) dy / dx;
            double b = y1 - m * x1;

            int step = dx >= 0 ? 1 : -1;

            for (int x = x1; ; x += step) {

                int y = (int) Math.round(m * x + b);

                gl.glVertex2i(x, y);

                if (x == x2) {
                    break;
                }
            }

        } else {

            // Use y as the independent variable for steep lines
            double inverseM = (double) dx / dy;
            double c = x1 - inverseM * y1;

            int step = dy >= 0 ? 1 : -1;

            for (int y = y1; ; y += step) {

                int x = (int) Math.round(inverseM * y + c);

                gl.glVertex2i(x, y);

                if (y == y2) {
                    break;
                }
            }
        }
    }

    // Algorithm 2: Digital Differential Analyzer (DDA)
    private void dda(
            GL gl, int x1, int y1, int x2, int y2) {

        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(
                Math.abs(dx),
                Math.abs(dy)
        );

        if (steps == 0) {
            gl.glVertex2i(x1, y1);
            return;
        }

        double xIncrement = (double) dx / steps;
        double yIncrement = (double) dy / steps;

        double x = x1;
        double y = y1;

        for (int i = 0; i <= steps; i++) {

            int pixelX = (int) Math.round(x);
            int pixelY = (int) Math.round(y);

            gl.glVertex2i(pixelX, pixelY);

            x += xIncrement;
            y += yIncrement;
        }
    }

    // Algorithm 3: Bresenham
    private void bresenham(
            GL gl, int x1, int y1, int x2, int y2) {

        int dx = Math.abs(x2 - x1);
        int sx = x1 < x2 ? 1 : -1;

        int dy = -Math.abs(y2 - y1);
        int sy = y1 < y2 ? 1 : -1;

        int error = dx + dy;

        while (true) {

            gl.glVertex2i(x1, y1);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            int e2 = 2 * error;

            if (e2 >= dy) {
                error += dy;
                x1 += sx;
            }

            if (e2 <= dx) {
                error += dx;
                y1 += sy;
            }
        }
    }

    @Override
    public void reshape(
            GLAutoDrawable drawable,
            int x,
            int y,
            int width,
            int height) {

        GL gl = drawable.getGL();

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();

        GLU glu = new GLU();

        // Keep the coordinate system centered
        glu.gluOrtho2D(-450, 450, -300, 300);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void displayChanged(
            GLAutoDrawable drawable,
            boolean modeChanged,
            boolean deviceChanged) {
    }
}