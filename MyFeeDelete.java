import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
 class MyFeesDelete extends JFrame implements ActionListener {
    JComboBox t1;
    JLabel l0,l1;
    JButton bt,bt1;
    MyFeesDelete() {
        setSize(600, 600);
        setLayout(null);
        l0=new JLabel("DELETE COURSE FEE");
        l0.setFont(new Font("Arial",Font.BOLD,24));
        l0.setBounds(150,60,300,60);
        add(l0);
        l1=new JLabel("COURSE_ID");
        l1.setBounds(100,200,100,50);
        add(l1);
        t1=new JComboBox();
        t1.setBounds(250,200,100,40);
        add(t1);
        filldata();
        bt=new JButton("Delete..");
        bt.setBounds(100,300,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("Close");
        bt1.setBounds(250,300,100,50);
        bt1.addActionListener(this);
        add(bt1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyFeesDelete();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bt) {
            String id = t1.getSelectedItem().toString();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
                String sql = "delete from fee where ecourseid=?";
                PreparedStatement s = con.prepareStatement(sql);
                s.setString(1, id);
                int f = s.executeUpdate();
                JOptionPane.showMessageDialog(this, "record deleted.");
                this.hide();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if(ae.getSource()==bt1)
        {
            this.hide();
        }
    }
     void filldata()
     {
         try {
             Class.forName("oracle.jdbc.driver.OracleDriver");
             Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
             String sql="select  ecourseid from fee";
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