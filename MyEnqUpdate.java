import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyEnqUpdate extends JFrame implements ActionListener {
    JTextField t2,t3,t4,t5,t6;
    JLabel l0,l1,l2,l3,l4,l5,l6;
    JComboBox t1;
    JButton bt,bt1,bt2;
    MyEnqUpdate() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("ENQUIRY UPDATE");
        l0.setFont(new Font("Arial",Font.BOLD,24));
        l0.setBounds(250,60,280,60);
        add(l0);
        l1 = new JLabel("ENQUIRY_ID");
        l1.setBounds(80, 150, 100, 50);
        l1.setFont(new Font("Arial",Font.PLAIN,14));
        add(l1);
        l2 = new JLabel("NAME");
        l2.setBounds(80, 210, 100, 50);
        l2.setFont(new Font("Arial",Font.PLAIN,14));
        add(l2);
        l3 = new JLabel("MOBILE NUMBER");
        l3.setBounds(80, 270, 130, 50);
        l3.setFont(new Font("Arial",Font.PLAIN,14));
        add(l3);
        l4 = new JLabel("E-MAIL");
        l4.setBounds(80, 330, 130, 50);
        l4.setFont(new Font("Arial",Font.PLAIN,14));
        add(l4);
        l5 = new JLabel("CITY");
        l5.setBounds(80, 390, 130, 50);
        l5.setFont(new Font("Arial",Font.PLAIN,14));
        add(l5);
        l6 = new JLabel("DATE & TIME");
        l6.setBounds(80, 450, 130, 50);
        l6.setFont(new Font("Arial",Font.PLAIN,14));
        add(l6);

        t1=new JComboBox();
        t1.setBounds(250,150,250,35);
        add(t1);
        t2=new JTextField();
        t2.setBounds(250,210,250,35);
        add(t2);
        t3=new JTextField();
        t3.setBounds(250,270,250,35);
        add(t3);
        t4=new JTextField();
        t4.setBounds(250,330,250,35);
        add(t4);
        t5=new JTextField();
        t5.setBounds(250,390,250,35);
        add(t5);
        t6=new JTextField();
        t6.setBounds(250,450,250,35);
        add(t6);

        filldata();
        bt1=new JButton("Find");
        bt1.setBounds(100, 550, 100, 50);
        bt1.addActionListener(this);
        add(bt1);
        bt=new JButton("Close");
        bt.setBounds(400,550,100,50);
        bt.addActionListener(this);
        add(bt);
        bt.addActionListener(this);
        bt2= new JButton("Update");
        bt2.setBounds(250,550,100,50);
        add(bt2);
        bt2.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyEnqUpdate();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bt){
            System.exit(0);
        }
        if (ae.getSource() == bt1) {
            String id=t1.getSelectedItem().toString();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
                String sql = "select  enname,enmobno,enemail,encity,edatetime from enquiry where enid=?";
                PreparedStatement s = con.prepareStatement(sql);
                s.setString(1, id);
                ResultSet rs = s.executeQuery();
                if (rs.next()) {
                    t2.setText(rs.getString(1));
                    t3.setText(rs.getString(2));
                    t4.setText(rs.getString(3));
                    t5.setText(rs.getString(4));
                    t6.setText(rs.getString(5));



                } else {
                    JOptionPane.showMessageDialog(this, "Record Not found..");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if (ae.getSource()==bt2)
        {
            String a=t1.getSelectedItem().toString();
            String b=t2.getText();
            String c=t3.getText();
            String d=t4.getText();
            String e=t5.getText();
            String f=t6.getText();

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                String sql="update enquiry set enname=?,enmobno=?,enemail=?,encity=?,edatetime=? where enid=?";
                PreparedStatement s=con.prepareStatement(sql);
                s.setString(6,a);
                s.setString(1, b);
//                s.setString(3, c);
                {
                    if(c.length()==10){
                        s.setString(2, c);
                    }
                    else{
                        JOptionPane.showMessageDialog(this,"Mobile no length should be exced," +
                                "itshould be 10");
                    }
                }
//                s.setString(3, d);
                {
                    if (mailvalidtae(d) == true) {
                        s.setString(3, d);
                    } else {
                        JOptionPane.showMessageDialog(this, "please enter valid mail id..");
                    }
                }
                s.setString(4, e);
                s.setString(5, f);


                int h=s.executeUpdate();
                JOptionPane.showMessageDialog(this,"Record Updated");
                con.close();

                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");

            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }	}
    }
    void filldata()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="select enid from enquiry";
            PreparedStatement s=con.prepareStatement(sql);

            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                t1.addItem(rs.getString(1));
            }
        }
        catch(Exception ex) {}
    }
    boolean mailvalidtae(String email) {
        String regex = "[\\w-]{1,30}@\\w{2,20}.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}