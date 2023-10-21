import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyCourseDelete extends JFrame implements ActionListener {
    JComboBox t1;
    JLabel l1;
    JButton bt,bt1;
    MyCourseDelete() {
        setSize(600, 600);
        setLayout(null);
        l1=new JLabel("COURSE_ID");
        l1.setBounds(100,150,100,50);
        add(l1);
        t1=new JComboBox();
        t1.setBounds(250,150,100,40);
        add(t1);
        filldata();
        bt=new JButton("Delete..");
        bt.setBounds(150,300,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("Close");
        bt1.setBounds(250,300,100,50);
        bt1.addActionListener(this);
        add(bt1);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);/
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyCourseDelete();
    }
    @Override
    public void actionPerformed(ActionEvent ar) {
        if(ar.getSource()==bt) {
            String id = t1.getSelectedItem().toString();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "manager");
                String sql = "delete from course where ecourseid=?";
                PreparedStatement s = con.prepareStatement(sql);
                s.setString(1, id);
                int f = s.executeUpdate();
                JOptionPane.showMessageDialog(this, "record deleted.");
                this.hide();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if(ar.getSource()==bt1){
            this.hide();
        }
    }
    void filldata()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="select  ecourseid from course";
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