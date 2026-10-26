package myGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI01 extends JFrame implements ActionListener {
    private final JButton button;
    private final JTextArea textArea;
    private final JTextField textField;

    private final BorderLayout bl;
   public MyGUI01() {
        button = new JButton();
        button.setText("tab");
        button.addActionListener(this);
        textArea = new JTextArea();
        textField = new JTextField();
        bl = new BorderLayout(50,50);

        this.setTitle("Print App");
        this.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(this);
        this.setLayout(bl);
        this.add(textArea, BorderLayout.CENTER);
        this.add(textField , BorderLayout.NORTH);
        this.add(button , BorderLayout.SOUTH);
        this.setVisible(true);
   }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource().equals(button)) {
            textArea.setText(textArea.getText() + "\n" + textField.getText());

        }
    }

}