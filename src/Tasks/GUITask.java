package Tasks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUITask extends JFrame implements MouseListener , MouseMotionListener , KeyListener ,ActionListener {
    private int clicks = 0;
    String key = "";
    private final JPanel panel = new JPanel();
    private final JLabel mouseX = new JLabel();
    private final JLabel mouseY = new JLabel();
    private final JLabel status = new JLabel();
    private final JLabel totalClicks = new JLabel();
    private final JLabel lastKeyPressed = new JLabel();
    private final JButton reset = new JButton("reset");
    private final JButton exit = new JButton("exit");

    public GUITask(){
        this.setSize(700,500);
        this.setTitle("Event Tracker");
        this.setLayout( new GridLayout(7,2,10,10));
        this.addKeyListener(this);
        this.setFocusable(true);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reset.addActionListener(this);
        exit.addActionListener(this);

        this.add(new JLabel("mouseX "));
        this.add(mouseX);
        this.add(new JLabel("mouseY "));
        this.add(mouseY);
        this.add(new JLabel("status "));
        this.add(status);
        this.add(new JLabel("total clicks "));
        this.add(totalClicks);
        this.add(new JLabel("last keyPressed") );
        this.add(lastKeyPressed);
        this.add(reset);
        this.add(exit);
        this.setVisible(true);


    }


    @Override
    public void mouseClicked(MouseEvent e) {
        clicks+=1;
        totalClicks.setText(clicks + "");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        status.setText("Pressed");

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        status.setText("Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX.setText(e.getX() + "");
        mouseY.setText(e.getY() +"");
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            lastKeyPressed.setText("Left");
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            lastKeyPressed.setText("right");
        if(e.getKeyCode() == KeyEvent.VK_UP)
            lastKeyPressed.setText("up");
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            lastKeyPressed.setText("down");
        else
            lastKeyPressed.setText(new Character(e.getKeyChar()) + "");

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(reset)) {
            mouseY.setText("");
            mouseX.setText("");
            status.setText("");
            totalClicks.setText("");
            clicks = 0;
            lastKeyPressed.setText("");
        }
        if(e.getSource().equals(exit))
            System.exit(0);
    }
}
