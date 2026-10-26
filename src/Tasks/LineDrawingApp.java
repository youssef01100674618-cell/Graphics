package Tasks;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;

public class LineDrawingApp extends JFrame {

    private GLCanvas canvas;
    private LineDrawingListener listener;

    private JTextField x1Field = new JTextField("-200", 5);
    private JTextField y1Field = new JTextField("-100", 5);
    private JTextField x2Field = new JTextField("200", 5);
    private JTextField y2Field = new JTextField("100", 5);

    private JLabel slopeLabel = new JLabel("Slope: 0.5");

    public LineDrawingApp() {

        super("Line Drawing Algorithms");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create the graphics listener
        listener = new LineDrawingListener();

        // Create the JOGL canvas
        GLCapabilities capabilities = new GLCapabilities();
        canvas = new GLCanvas(capabilities);

        // Register the listener
        canvas.addGLEventListener(listener);

        // Create the input panel
        JPanel controls = new JPanel(new FlowLayout());

        controls.add(new JLabel("X1:"));
        controls.add(x1Field);

        controls.add(new JLabel("Y1:"));
        controls.add(y1Field);

        controls.add(new JLabel("X2:"));
        controls.add(x2Field);

        controls.add(new JLabel("Y2:"));
        controls.add(y2Field);

        controls.add(slopeLabel);

        JButton redrawButton = new JButton("Redraw");
        controls.add(redrawButton);

        // Handle button clicks
        redrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int x1 = Integer.parseInt(x1Field.getText().trim());
                    int y1 = Integer.parseInt(y1Field.getText().trim());
                    int x2 = Integer.parseInt(x2Field.getText().trim());
                    int y2 = Integer.parseInt(y2Field.getText().trim());

                    listener.setLineEndpoints(x1, y1, x2, y2);

                    if (x2 != x1) {
                        double slope =
                                (double) (y2 - y1) / (x2 - x1);

                        slopeLabel.setText("Slope: " + slope);
                    } else {
                        slopeLabel.setText("Slope: undefined");
                    }

                    // Request a redraw
                    canvas.repaint();

                } catch (NumberFormatException ex) {

                    JOptionPane.showMessageDialog(
                            LineDrawingApp.this,
                            "Please enter valid integer coordinates.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        // Add components to the window
        add(controls, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);

        setSize(900, 650);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LineDrawingApp app = new LineDrawingApp();
                app.setVisible(true);
            }
        });
    }
}