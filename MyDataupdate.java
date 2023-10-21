import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyDataupdate extends JFrame implements ActionListener {
    JTextField t2,t3,t4,t5,t6;
    JLabel l0,l1,l2,l3,l4,l5,l6;
    JComboBox t1;
    JButton bt,btf,btn1;
    MyDataupdate() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("DATA UPDATE");
        l0.setFont(new Font("Arial",Font.BOLD,24));
        l0.setBounds(250,60,200,60);
        add(l0);
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
        t1=new JComboBox();
        t1.setBounds(200,150,250,35);
        add(t1);
        t2=new JTextField();
        t2.setBounds(200,210,250,35);
        add(t2);
        t3=new JTextField();
        t3.setBounds(200,270,250,35);
        add(t3);
        t4=new JTextField();
        t4.setBounds(200,330,250,35);
        add(t4);
        t5=new JTextField();
        t5.setBounds(200,390,250,35);
        add(t5);
        t6=new JTextField();
        t6.setBounds(200,450,250,35);
        add(t6);
        filldata();
        btf=new JButton("Find");
        btf.setBounds(100, 550, 100, 50);
        btf.addActionListener(this);
        add(btf);
        bt=new JButton("Close");
        bt.setBounds(400,550,100,50);
        bt.addActionListener(this);
        add(bt);
        bt.addActionListener(this);
        btn1= new JButton("Update");
        btn1.setBounds(250,550,100,50);
        add(btn1);
        btn1.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyDataupdate();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bt){
            this.hide();
        }
        if (ae.getSource()==btf)
        {
            int a=Integer.parseInt(t1.getSelectedItem().toString());
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                String sql="select esname,ecollege,eemail,ephone,egovid from estudent where eid=?";
                PreparedStatement s=con.prepareStatement(sql);
                s.setInt(1, a);
                ResultSet rs=s.executeQuery();
                if (rs.next())
                {
                    t2.setText(rs.getString(1));
                    t3.setText(rs.getString(2));
                    t4.setText(rs.getString(3));
                    t5.setText((String.valueOf(rs.getLong(4))));
                    t6.setText(rs.getString(5));
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
        if (ae.getSource()==btn1)
        {
            int a=Integer.parseInt(t1.getSelectedItem().toString());
            String b=t2.getText();
            String c=t3.getText();
            String d=t4.getText();
            Long e=Long.parseLong(t5.getText());
            String f=t6.getText();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
                String sql="update estudent set esname=?,ecollege=?,eemail=?,ephone=?,egovid=? where eid=?";
                PreparedStatement s=con.prepareStatement(sql);
                s.setInt(6,a);
                s.setString(1, b);
                s.setString(2, c);
                s.setString(3, d);
                s.setLong(4, e);
                s.setString(5,f);

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
            String sql="select  eid from estudent";
            PreparedStatement s=con.prepareStatement(sql);

            ResultSet rs=s.executeQuery();
            while(rs.next())
            {
                t1.addItem(String.valueOf(rs.getInt(1)));
            }
        }
        catch(Exception ex) {}
    }
}