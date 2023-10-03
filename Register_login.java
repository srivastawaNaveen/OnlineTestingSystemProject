import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.awt.font.TextAttribute.FONT;

public class Register_login {
    public static void main(String[] args) {
        Register naveen = new Register();
        naveen.getConnectionFromMySql();
        naveen.init();

    }
}

class OneTime{
    public static String generateOTP(int length){
        String number = "0123456789";
        Random rndm = new Random();
        char []otp = new char[length];
        for(int i=0;i<length;i++){
            otp[i]=number.charAt(rndm.nextInt(number.length()));
        }
        return new String(otp);
    }
}

class Register extends JFrame{
    Connection conn=null;
    ResultSet res = null;
    PreparedStatement ps = null;
    Statement stmt = null;
    String name,pass,email,contact;
    JFrame frame = new JFrame("Register");
    JLabel l1 = new JLabel("Enter Name");
    JTextField t1 = new JTextField();
    JLabel l2 = new JLabel("Enter Password");
    JPasswordField p1 = new JPasswordField();

    JLabel l3 = new JLabel("Enter Contact");
    JTextField t2 = new JTextField();

    JLabel l4 = new JLabel("Enter Email");
    JLabel l5 = new JLabel("LOGIN if already user ");
    JTextField t3 = new JTextField();
    JButton b1 = new JButton("REGISTER");

    JButton b2 = new JButton("LOGIN");
    Font font = new Font("SansSerif",FONT.WEIGHT_BOLD.byteValue(), 18);
    String otp = OneTime.generateOTP(4);
    public void getConnectionFromMySql(){
        try {
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/db2","root","naveen45@ks");
            System.out.println("Connection Established");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void init(){
        frame.setSize(400,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocation(400,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1.setBounds(70,50,120,30);
        t1.setBounds(190,50,150,30);
        l2.setBounds(70,110,120,30);
        p1.setBounds(190,110,150,30);
        l3.setBounds(70,170,120,30);
        t2.setBounds(190,170,150,30);
        l4.setBounds(70,230,120,30);
        t3.setBounds(190,230,150,30);
        b1.setBounds(150,290,130,30);
        l5.setBounds(110,340,200,30);
        b2.setBounds(150,380,130,30);
        frame.add(l1);
        frame.add(t1);
        frame.add(l2);
        frame.add(p1);
        frame.add(l3);
        frame.add(t2);
        frame.add(l4);
        frame.add(t3);
        frame.add(b1);
        frame.add(l5);
        frame.add(b2);
        l5.setFont(font);
        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                name = t1.getText();
                pass = Arrays.toString(p1.getPassword());
                contact = t2.getText();
                email = t3.getText();
                if(name.length()>0 && email.length()>0 && pass.length()>0 && contact.length()>0){
                    try {
                        String q = "insert into Register(name,pass,contact,email) values (?,?,?,?)";
                        ps = conn.prepareStatement(q);
                        ps.setString(1,name);
                        ps.setString(2,pass);
                        ps.setString(3,contact);
                        ps.setString(4,email);
                        ps.executeUpdate();
                        ps.close();
                        conn.close();
                            JOptionPane.showMessageDialog(frame, "Registration Success !");
                            t1.setText("");
                            t2.setText("");
                            p1.setText("");
                            t3.setText("");
                    }
                    catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                 else{
                    JOptionPane.showMessageDialog(frame, "Registration Failed !!! ");
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getConnectionFromMySql();
                JFrame frame2 = new JFrame("LOGIN NOW ");
                frame2.setSize(500,300);
                frame2.setLayout(null);
                frame2.setVisible(true);
                frame2.setLocation(400,150);
                frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel l1 = new JLabel("Enter Email ");
                JLabel l2 = new JLabel("Enter OTP ");
                JButton b3 = new JButton("GET OTP");
                JButton b4 = new JButton("VALIDATE");
                JTextField t1 = new JTextField();
                JTextField t2 = new JTextField();
                JPasswordField p1 = new JPasswordField();
                JButton b1 = new JButton("GO TO PAPER");
                JButton back = new JButton("BACK");
                JLabel l3 = new JLabel("Select Class");
                JButton go = new JButton("GO TO PAPER");
                JComboBox clas = new JComboBox();
                clas.addItem("IX");
                clas.addItem("X");
                clas.addItem("XI");
                clas.addItem("XII");
                l1.setBounds(50,50,120,30);
                l2.setBounds(50,100,120,30);
                t1.setBounds(170,50,130,30);
                b3.setBounds(320,50,120,30);
                t2.setBounds(170,100,130,30);
                l3.setBounds(50,150,100,30);
                clas.setBounds(170,150,100,30);
                b1.setBounds(90,210,140,30);
                back.setBounds(250,210,100,30);
                b4.setBounds(320,100,120,30);
                frame2.add(l3);
                frame2.add(go);
                frame2.add(l1);
                frame2.add(clas);
                frame2.add(l1);
                frame2.add(l2);
                frame2.add(t1);
                frame2.add(t2);
                frame2.add(b1);
                frame2.add(back);
                frame2.add(b3);
                frame2.add(b4);
                clas.disable();
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(true);
                        frame2.dispose();
                    }
                });

                frame.dispose();
                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String email;
                        email = t1.getText();
                        String databaseEmail;
                        int valid =0,count=0;
                        try {
                            stmt = conn.createStatement();
                            String q = "select email from register ";
                            ResultSet res = stmt.executeQuery(q);
                            while(res.next()){
                                databaseEmail=res.getString("email");
                                if(databaseEmail.equals(email)) {
                                    valid = 1;
                                }
                            }
                            if(valid==1){
                                JOptionPane.showMessageDialog(frame2,"Your OTP is "+ otp);
                                t1.setText("");
                                t1.disable();
                            }
                            else{
                                JOptionPane.showMessageDialog(frame2,"Email does not match ");
                            }
                            b4.addActionListener(new ActionListener() {
                                int count=0;
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(t2.getText().equals(otp)){
                                        count=1;
                                    }
                                    if(count==1){
                                        JOptionPane.showMessageDialog(frame2,"OTP is correct ");
                                        t2.setText("");
                                        t2.disable();
                                        clas.enable();
                                    }
                                }
                            });
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });


                b1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int marks=0;
                        String cls = (String) clas.getSelectedItem();
                        if(cls=="X"){
                            frame2.dispose();
                            try{
                                String userAns;
                                stmt = conn.createStatement();
                                String query="Select question,optA,optB,optC,optD, answer from xthpaper ";
                                ResultSet res = stmt.executeQuery(query);
                                System.out.println("\n\t\t\t\tScience Paper of Xth class :- ");
                                while(res.next()){
                                    String question=res.getString("question");
                                    String optA=res.getString("optA");
                                    String optB=res.getString("optB");
                                    String optC=res.getString("optC");
                                    String optD=res.getString("optD");
                                    String ans = res.getString("answer");
                                    System.out.println(question+"\n"+optA+"\n"+optB+"\n"+optC+"\n"+optD);
                                    System.out.println("\nEnter your Answer ");
                                    Scanner sc = new Scanner(System.in);
                                    userAns=sc.next();
                                    if(userAns.toUpperCase().equals(ans)){
                                        System.out.println("\nCorrect ...");
                                        marks+=1;
                                    }
                                    else{
                                        System.out.println("Wrong ... ");
                                        marks-=1;
                                    }
//                                    System.out.println("Answer is "+ans);
                                }
                                System.out.println("\nYou have secured "+marks+" marks in this quiz ");
                                System.out.println("\nThanks for visit");
                            }catch (SQLException ex){
                                System.out.println(ex.getMessage());
                            }
                        }
                    }
                });
            }
        });
    }
}
