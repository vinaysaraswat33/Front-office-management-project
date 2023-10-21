import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyCourseUpdate extends JFrame implements ActionListener {
    JTextField t2,t3,t4;
    JLabel l0,l1,l2,l3,l4;
    JComboBox t1;
    JButton bt,bt1,bt2;
    MyCourseUpdate() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("COURSE UPDATE");
        l0.setFont(new Font("Arial",Font.BOLD,24));
        l0.setBounds(250,60,250,60);
        add(l0);
        l1 = new JLabel("CS_ID");
        l1.setBounds(80, 150, 100, 50);
        l1.setFont(new Font("Arial",Font.PLAIN,14));
        add(l1);
        l2 = new JLabel("COURSE Name");
        l2.setBounds(80, 210, 150, 50);
        l2.setFont(new Font("Arial",Font.PLAIN,14));
        add(l2);
        l3 = new JLabel("COURSE_DURATION");
        l3.setBounds(70, 270, 150, 50);
        l3.setFont(new Font("Arial",Font.PLAIN,14));
        add(l3);
        l4 = new JLabel("CERTIFIED");
        l4.setBounds(80, 330, 100, 50);
        l4.setFont(new Font("Arial",Font.PLAIN,14));
        add(l4);

        t1=new JComboBox();
        t1.setBounds(230,150,250,35);
        add(t1);
        t2=new JTextField();
        t2.setBounds(230,210,250,35);
        add(t2);
        t3=new JTextField();
        t3.setBounds(230,270,250,35);
        add(t3);
        t4=new JTextField();
        t4.setBounds(230,330,250,35);
        add(t4);

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
        new MyCourseUpdate();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bt){
            this.hide();
        }
        if (ae.getSource()==bt1)
        {
            String a=t1.getSelectedItem().toString();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                String sql="select ecoursename,eduration,ecertified from course where ecourseid=?";
                PreparedStatement s=con.prepareStatement(sql);
                s.setString(1, a);
                ResultSet rs=s.executeQuery();
                if (rs.next())
                {
                    t2.setText(rs.getString(1));
                    t3.setText(rs.getString(2));
                    t4.setText(rs.getString(3));

                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Record Not found..");
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex);
            }
        }
        if (ae.getSource()==bt2)
        {
            String a=t1.getSelectedItem().toString();
            String b=t2.getText();
            String c=t3.getText();
            String d=t4.getText();

            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                String sql="update course set ecoursename=?,eduration=?,ecertified=? where ecourseid=?";
                PreparedStatement s=con.prepareStatement(sql);
                s.setString(4,a);
                s.setString(1, b);
                s.setString(2, c);
                s.setString(3, d);


                int h=s.executeUpdate();
                JOptionPane.showMessageDialog(this,"Record Updated");
                con.close();

                t2.setText("");
                t3.setText("");
                t4.setText("");

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
            String sql="select ecourseid from course";
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