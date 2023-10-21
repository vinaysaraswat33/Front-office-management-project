import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

class MyEnqDelete extends JFrame implements ActionListener {
    JComboBox t1;
    JLabel l0,l1;
    JButton bt;
    MyEnqDelete() {
        setSize(600, 600);
        setLayout(null);
        l0=new JLabel("DELETE ENQUIRY");
        l0.setFont(new Font("Arial",Font.BOLD,24));
        l0.setBounds(150,60,300,60);
        add(l0);
        l1=new JLabel("ENQUIRY_ID");
        l1.setBounds(100,200,100,50);
        add(l1);
        t1=new JComboBox();
        t1.setBounds(250,200,100,40);
        add(t1);
        filldata();
        bt=new JButton("Delete");
        bt.setBounds(150,300,100,50);
        add(bt);
        bt.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyEnqDelete();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String id=t1.getSelectedItem().toString();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="delete from enquiry where enid=?";
            PreparedStatement s=con.prepareStatement(sql);
            s.setString(1, id);
            int f=s.executeUpdate();
            JOptionPane.showMessageDialog(this, "record deleted.");

        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    void filldata()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="select  enid from enquiry";
            PreparedStatement s=con.prepareStatement(sql);

            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                t1.addItem(String.valueOf(rs.getString(1)));
            }
        }
        catch(Exception ex) {}
    }

}