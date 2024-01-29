import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Temparature_Converter extends JFrame implements ActionListener {
    JButton b;
    JRadioButton rb1, rb2;
    JLabel l1, l2, l3, l4,l5;
    JTextField tf;

    Temparature_Converter() {
        rb1 = new JRadioButton("Celcius to Fahrenheit");
        rb1.setBounds(20, 10, 150, 30);
        rb2 = new JRadioButton("Fahrenhite to Celcius");
        rb2.setBounds(170, 10, 150, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        rb1.setSelected(true);
        rb1.addActionListener(this);
        rb2.addActionListener(this);
        l1 = new JLabel("Temperature");
        l1.setBounds(20, 52, 80, 30);
        tf = new JTextField();
        tf.setBounds(110, 58, 280, 20);
        l2 = new JLabel("C");
        l2.setBounds(400, 52, 10, 30);
        l3 = new JLabel("F");
        l3.setVisible(false);
        l3.setBounds(400, 52, 10, 30);
        b = new JButton("Convert");
        b.setBounds(415,58,100,20);
        b.addActionListener(this);
        l4 = new JLabel("Result:- ");
        l4.setBounds(20, 80, 80, 30);
        l5 = new JLabel("00.00");
        l5.setBounds(100,80,80,30);
        add(rb1);
        add(rb2);
        add(l1);
        add(tf);
        add(l2);
        add(l3);
        add(b);
        add(l4);
        add(l5);
        setSize(600, 200);
        setTitle("Temperature Converter");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        double input = Integer.parseInt(tf.getText());
        if (rb1.isSelected()) {
            l2.setVisible(true);
            l3.setVisible(false);
            if(e.getSource() == b){
               double temp = (input*9/5)+32;
               l5.setText(String.valueOf(temp));

            }

        }
        if (rb2.isSelected()) {
            l2.setVisible(false);
            l3.setVisible(true);
            if(e.getSource() == b){
                double temp = (input-32)*5/9;
                l5.setText(String.valueOf(temp));

            }
        }
    }


    public static void main(String[] args) {
        new Temparature_Converter();
    }

}
