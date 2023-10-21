import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyDataFind extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l0;
    JTextField t2,t3,t4,t6,t5;
    JComboBox t0;
    JButton bt,btf;
    public MyDataFind() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("DATA FIND");
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
        t0=new JComboBox();
        t0.setBounds(200,150,250,35);
        add(t0);
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
        bt=new JButton("Close");
        bt.setBounds(100,550,100,50);
        add(bt);
        btf=new JButton("Find");
        btf.setBounds(300, 550, 100, 50);
        btf.addActionListener(this);
        add(btf);
        bt.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyDataFind();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==btf)
        {
            int a=Integer.parseInt(t0.getSelectedItem().toString());
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

        if(ae.getSource()==bt){
            this.hide();
        }
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
                t0.addItem(String.valueOf(rs.getInt(1)));
            }
        }
        catch(Exception ex) {}
    }
}