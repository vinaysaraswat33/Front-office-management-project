import javax.swing.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

public class MyEnqInsert extends JFrame implements ActionListener {
    JLabel l0,l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6;
    JButton bt,bt1,bt2,bt3;
    public MyEnqInsert() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("INSERT ENQUIRY");
        l0.setFont(new Font("Arial Black",Font.BOLD,24));
        l0.setBounds(200,30,250,70);
        add(l0);
        l1=new JLabel("ENQUIRY_ID");
        l1.setBounds(100,130,100,50);
        l1.setFont(new Font("Arial",Font.BOLD,14));
        add(l1);
        l7=new JLabel("e.g:- EN01");
        l7.setBounds(520,130,80,40);
        add(l7);
        l2=new JLabel("NAME");
        l2.setBounds(100,190,100,50);
        l2.setFont(new Font("Arial",Font.BOLD,14));
        add(l2);
        l3=new JLabel("MOBILE NUMBER");
        l3.setBounds(100,250,150,50);
        l3.setFont(new Font("Arial",Font.BOLD,14));
        add(l3);
        l4=new JLabel("E-MAIL");
        l4.setBounds(100,310,150,50);
        l4.setFont(new Font("Arial",Font.BOLD,14));
        add(l4);
        l5=new JLabel("CITY");
        l5.setBounds(100,370,150,50);
        l5.setFont(new Font("Arial",Font.BOLD,14));
        add(l5);
        l6=new JLabel("DATE & TIME");
        l6.setBounds(100,430,150,50);
        l6.setFont(new Font("Arial",Font.BOLD,14));
        add(l6);

        t1=new JTextField();
        t1.setBounds(300,130,150,40);
        add(t1);
        t2=new JTextField();
        t2.setBounds(300,190,150,40);
        add(t2);
        t3=new JTextField();
        t3.setBounds(300,250,150,40);
        add(t3);
        t4=new JTextField();
        t4.setBounds(300,310,150,40);
        add(t4);
        t5=new JTextField();
        t5.setBounds(300,370,150,40);
        add(t5);
        t6=new JTextField();
        t6.setBounds(300,430,150,40);
        add(t6);
        bt=new JButton("Save");
        bt.setBounds(100,530,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("Close");
        bt1.setBounds(400,530,100,50);
        bt1.addActionListener(this);
        add(bt1);
        bt2=new JButton("New");
        bt2.setBounds(250,530,100,50);
        bt2.addActionListener(this);
        add(bt2);
        bt3=new JButton("Check");
        bt3.setBounds(600,130,100,50);
        bt3.addActionListener(this);
        add(bt3);
        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new MyEnqInsert();

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == bt) {
            String id = t1.getText();
            String name = t2.getText();
            String mobno = t3.getText();
            String email = t4.getText();
            String city = t5.getText();
            String datetime = (t6.getText());
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
                String sql = "insert into enquiry values(?,?,?,?,?,?)";
                PreparedStatement s = con.prepareStatement(sql);
                s.setString(1, id);
//                {
//                    if(enqid(id) == true) {
//                        s.setString(1, id);
////                        JOptionPane.showMessageDialog(this,"this is is exist");
//
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(this,"this id is Exist..");
//                    }
//
//                }
                s.setString(2, name);
                {
                    if (mobno.length() == 10) {
                        s.setString(3, mobno);
                    } else {
                        JOptionPane.showMessageDialog(this, "Mobile no length should be exced," +
                                "itshould be 10");
                    }
                }
                {
                    if (mailvalidtae(email) == true) {
                        s.setString(4, email);
                    } else {
                        JOptionPane.showMessageDialog(this, "please enter valid mail id..");
                    }
                }
                s.setString(5, city);
                s.setString(6, datetime);

                int f = s.executeUpdate();
                JOptionPane.showMessageDialog(this, "record saved.");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
            } catch (Exception ex) {
                System.out.println(ex);

            }
        }
        if (ae.getSource() == bt1) {
            this.hide();
        }
        if (ae.getSource() == bt2) {
            this.enable();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");

        }
        if (ae.getSource()==bt3){
            String id=t1.getText();
            System.out.println(enqid(id));
            if(enqid(id)==true){
                JOptionPane.showMessageDialog(this,"Valid");
            }
            else {
                JOptionPane.showMessageDialog(this,"IN Evalid id");
            }
        }
    }




    boolean mailvalidtae(String email) {
        String regex = "[\\w-]{1,30}@\\w{2,20}.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    boolean enqid(String id)  {
        boolean st=true;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
            String sql = "select * from enquiry where enid=?";
            PreparedStatement s = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    s.setString(1,id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                st=false;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return st;
    }

}
