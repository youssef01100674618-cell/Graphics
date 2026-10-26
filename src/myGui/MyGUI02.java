package myGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyGUI02 extends JFrame implements ActionListener {
    ArrayList<String> list = new ArrayList<>();
    private final JTextArea textArea = new JTextArea();
    private final JPanel grid = new JPanel(new GridLayout(4,3,5,5));
    private final JButton button9 = new JButton("9");
    private final JButton button8 = new JButton("8");
    private final JButton button7 = new JButton("7");
    private final JButton button6 = new JButton("6");
    private final JButton button5 = new JButton("5");
    private final JButton button4 = new JButton("4");
    private final JButton button3 = new JButton("3");
    private final JButton button2 = new JButton("2");
    private final JButton button1 = new JButton("1");
    private final JButton button0 = new JButton("0");
    private final JButton plus = new JButton("+");
    private final JButton calc = new JButton("calc");

    public MyGUI02() {
        this.setTitle("Calculator");
        this.setResizable(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5,10));

        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button0.addActionListener(this);
        plus.addActionListener(this);
        calc.addActionListener(this);
        grid.add(button1);
        grid.add(button2);
        grid.add(button3);
        grid.add(button4);
        grid.add(button5);
        grid.add(button6);
        grid.add(button7);
        grid.add(button7);
        grid.add(button8);
        grid.add(button9);
        grid.add(button0);
        grid.add(plus);
        grid.add(calc);

        textArea.setText("0");

        this.add(textArea, BorderLayout.NORTH);
        this.add(grid, BorderLayout.CENTER);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(textArea.getText().equals("0"))
            textArea.setText("");
        if(e.getSource().equals(button1)) {
            textArea.setText(textArea.getText() + " " + button1.getText());
            list.add("1");
        }
        if(e.getSource().equals(button2)) {
            textArea.setText(textArea.getText() + " " + button2.getText());
            list.add("2");
        }
        if(e.getSource().equals(button3)) {
            textArea.setText(textArea.getText() + " " + button3.getText());
            list.add("3");
        }
        if(e.getSource().equals(button7)) {
            textArea.setText(textArea.getText() + " " + button7.getText());
            list.add("7");
        }
        if(e.getSource().equals(button6)) {
            textArea.setText(textArea.getText() + " " + button6.getText());
            list.add("6");
        }
        if(e.getSource().equals(button4)) {
            textArea.setText(textArea.getText() + " " + button4.getText());
            list.add("4");
        }
        if(e.getSource().equals(button5)) {
            textArea.setText(textArea.getText() + " " + button5.getText());
            list.add("5");
        }
        if(e.getSource().equals(plus)) {
            textArea.setText(textArea.getText() + " " + plus.getText());
            list.add("+");
        }
        if(e.getSource().equals(calc)) {
            textArea.setText(calc() + "");
        }
    }
    public int calc() {
        if(list.size() !=3)
            System.exit(20);
        if(list.get(1).compareTo("+") == 0) {
            return Integer.parseInt(list.get(0)) + Integer.parseInt(list.get(2));
        }
        return Integer.parseInt(list.get(0)) + Integer.parseInt(list.get(2));
    }
}
