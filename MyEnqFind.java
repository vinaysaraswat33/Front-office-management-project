import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyEnqFind extends JFrame implements ActionListener {
    JLabel l0,l1,l2,l3,l4,l5,l6;
    JTextField t2,t3,t4,t5,t6;
    JComboBox t1;
    JButton bt,bt2;

    MyEnqFind() {
        setSize(800, 800);
        // setTitle("Course Find");
        setLayout(null);
        l0=new JLabel("FIND ENQUIRY");
        l0.setFont(new Font("Arial Black",Font.BOLD,24));
        l0.setBounds(200,30,280,70);
        add(l0);
        l1 = new JLabel("ENQUIRY_ID");
        l1.setBounds(100, 130, 100, 50);
        l1.setFont(new Font("Arial", Font.BOLD, 14));
        add(l1);
        l2 = new JLabel("NAME");
        l2.setBounds(100, 190, 100, 50);
        l2.setFont(new Font("Arial", Font.BOLD, 14));
        add(l2);
        l3 = new JLabel("MOBILE NUMBER");
        l3.setBounds(100, 250, 150, 50);
        l3.setFont(new Font("Arial", Font.BOLD, 14));
        add(l3);
        l4 = new JLabel("E-MAIL");
        l4.setBounds(100, 310, 150, 50);
        l4.setFont(new Font("Arial", Font.BOLD, 14));
        add(l4);
        l5 = new JLabel("CITY");
        l5.setBounds(100, 370, 150, 50);
        l5.setFont(new Font("Arial", Font.BOLD, 14));
        add(l5);
        l6 = new JLabel("DATE & TIME");
        l6.setBounds(100, 430, 150, 50);
        l6.setFont(new Font("Arial", Font.BOLD, 14));
        add(l6);
        t1 = new JComboBox();
        t1.setBounds(300, 130, 150, 40);
        add(t1);
        t2 = new JTextField();
        t2.setBounds(300, 190, 150, 40);
        add(t2);
        t3 = new JTextField();
        t3.setBounds(300, 250, 150, 40);
        add(t3);
        t4 = new JTextField();
        t4.setBounds(300, 310, 150, 40);
        add(t4);
        t5 = new JTextField();
        t5.setBounds(300, 370, 150, 40);
        add(t5);
        t6 = new JTextField();
        t6.setBounds(300, 430, 150, 40);
        add(t6);
        filldata();
        bt = new JButton("Find");
        bt.setBounds(100, 550, 100, 50);
        add(bt);
        bt.addActionListener(this);
        bt2 = new JButton("Close");
        bt2.setBounds(400, 550, 100, 50);
        add(bt2);
        bt2.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyEnqFind();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bt) {
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
        if (ae.getSource() == bt2) {
            this.hide();
        }

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
}