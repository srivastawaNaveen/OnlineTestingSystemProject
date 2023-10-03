import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;

import static java.awt.font.TextAttribute.FONT;

public class FirstPage {
    public static void main(String[] args) {
        Page1 user = new Page1();
    }
}
class Page1{
    static int marks=0;
    JFrame frame = new JFrame("Computer Quiz");
    JPanel panel = new JPanel();
    Connection conn=null;
    ResultSet res = null;
    PreparedStatement ps = null;
    JLabel l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4;
    JComboBox clas=null;
    Statement stmt=null;

    JButton b1,b2;
    Font font = new Font("SansSerif",FONT.WEIGHT_BOLD.byteValue(), 22);
    Page1(){
        frame.setSize(400,400);
        frame.setVisible(true);
        frame.add(panel);

        panel.setBackground(Color.black);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        panel.setLayout(null);
        frame.validate();
        init();
    }

    public void getConnectionFromMySql(){
            try {
                conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","naveen45@ks");
                System.out.println("Connection Established");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    public void init(){
        l1=new JLabel("Quiz Management System");
        l2=new JLabel("Name ");
        l3=new JLabel("Contact ");
        l4=new JLabel("Class ");
        l5=new JLabel("Email ");
        b1=new JButton("Submit");
        l1.setBounds(70,20,280,25);
        l2.setBounds(70,70,130,25);
        l3.setBounds(70,120,130,25);
        l4.setBounds(70,170,130,25);
        l5.setBounds(70,220,130,25);
        b1.setBounds(70,270,100,25);
        l1.setFont(font);
        l1.setForeground(Color.magenta);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(b1);
        t1=new JTextField();
        t2=new JTextField();
        clas = new JComboBox();
        clas.addItem("IX");
        clas.addItem("X");
        clas.addItem("XI");
        clas.addItem("XII");
        t4=new JTextField();
        t1.setBounds(150,70,140,25);
        t2.setBounds(150,120,140,25);
        clas.setBounds(150,170,140,25);
        t4.setBounds(150,220,140,25);
        frame.add(t1);
        frame.add(t2);
        frame.add(clas);
        frame.add(t4);
        frame.validate();
        frame.setResizable(false);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getConnectionFromMySql();
                String name,contact,email;
                name = t1.getText();
                contact = t2.getText();
                email = t4.getText();

            }
        });
    }
}
