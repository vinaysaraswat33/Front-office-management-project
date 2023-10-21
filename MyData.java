import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class MyData extends JFrame implements ActionListener {
    JTextField t1,t2,t3,t4,t6,t5;
    JButton bt,bt1,bt2,bt3;
    JLabel l1,l2,l3,l4,l5,l6;
    Connection con;
    Statement st;ResultSet rs;
    public MyData() {
        setSize(800,800);
        setLayout(null);
        l1 = new JLabel("SID");
        l1.setBounds(80, 150, 100, 50);
        l1.setFont(new Font("Arial",Font.PLAIN,14));
        add(l1);
        l2 = new JLabel("Student Name");
        l2.setBounds(80, 210, 100, 50);
        l2.setFont(new Font("Arial",Font.PLAIN,14));
        add(l2);
        l3 = new JLabel("College Name");
        l3.setBounds(80, 270, 100, 50);
        l3.setFont(new Font("Arial",Font.PLAIN,14));
        add(l3);
        l4 = new JLabel("Email Id");
        l4.setBounds(80, 330, 100, 50);
        l4.setFont(new Font("Arial",Font.PLAIN,14));
        add(l4);
        l5 = new JLabel("Contact no.");
        l5.setBounds(80, 390, 150, 50);
        l5.setFont(new Font("Arial",Font.PLAIN,14));
        add(l5);
        l6 = new JLabel("Gov Id");
        l6.setBounds(80, 450, 100, 50);
        l6.setFont(new Font("Arial",Font.PLAIN,14));
        add(l6);
        t1 = new JTextField();
        t1.setBounds(250, 150, 180, 30);
        add(t1);
        t2 = new JTextField();
        t2.setBounds(250, 210, 180, 30);
        add(t2);
        t3 = new JTextField();
        t3.setBounds(250, 270, 180, 30);
        add(t3);
        t4 = new JTextField();
        t4.setBounds(250, 330, 180, 30);
        add(t4);
        t5 = new JTextField();
        t5.setBounds(250, 390, 180, 30);
        add(t5);
        t6 = new JTextField();
        t6.setBounds(250, 450, 180, 30);
        add(t6);
        bt=new JButton("First");
        bt.setBounds(100,550,60,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("Next");
        bt1.setBounds(300,550,60,50);
        add(bt1);
        bt1.addActionListener(this);
        bt2=new JButton("Last");
        bt2.setBounds(400,550,60,50);
        add(bt2);
        bt2.addActionListener(this);
        bt3=new JButton("Previous");
        bt3.setBounds(480,550,100,50);
        add(bt3);
        bt3.addActionListener(this);
        filldata();
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyData();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource()==bt)
            {
                rs.first();
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));

            }
            if (ae.getSource()==bt1)
            {
                rs.next();
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
            }
            if (ae.getSource()==bt2)
            {
                rs.last();
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
            }
            if (ae.getSource()==bt3)
            {
                rs.previous();
                t1.setText(String.valueOf(rs.getInt(1)));
                t2.setText(rs.getString(2));
                t3.setText(rs.getString(3));
                t4.setText(rs.getString(4));
                t5.setText(rs.getString(5));
                t6.setText(rs.getString(6));
            }
        }
        catch(Exception ex) {
            System.out.println(ex.toString());
        }
    }
    void filldata()
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="select eid,esname,ecollege,eemail,ephone,egovid from estudent";
            st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=st.executeQuery(sql);
            rs.first();
            t1.setText(String.valueOf(rs.getInt(1)));
            t2.setText(rs.getString(2));
            t3.setText(rs.getString(3));
            t4.setText(rs.getString(4));
            t5.setText(rs.getString(5));
            t6.setText(rs.getString(6));
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }

    }
}