package graphicsLib.templates;

import javax.swing.*;
import java.awt.*;
/*
to use this class you need to setVisible(true) after invoking the constructor
use it by extending it to ur class
 */
public class Template extends JFrame {
    // a constructor with a set JFrame
    public Template(int width , int height) {
        this.setSize(width,height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Event Tracker");
        this.setTitle("My Graph App");
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(this);
    }
    public Template() {
       this(600,300) ;
    }
}
