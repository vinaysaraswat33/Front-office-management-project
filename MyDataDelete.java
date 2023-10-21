import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyDataDelete extends JFrame implements ActionListener {
    JComboBox t1;
    JButton bt,bt1;
    JLabel l0,l1;
    public MyDataDelete() {
        setSize(600, 600);
        setLayout(null);
        l0=new JLabel("STUDENT DATA DELETE");
        l0.setFont(new Font("Arial Black",Font.BOLD,24));
        l0.setBounds(200,30,250,70);
        add(l0);
        l1=new JLabel("STUDENT_Id");
        l1.setFont(new Font("Arial Black",Font.PLAIN,16));
        l1.setBounds(120,130,200,70);
        add(l1);
        t1=new JComboBox();
        t1.setBounds(250,150,100,40);
        add(t1);
        filldata();
        bt=new JButton("Delete..");
        bt.setBounds(100,300,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("CLOSE");
        bt1.setBounds(200,300,100,50);
        add(bt1);
        bt1.addActionListener(this);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MyDataDelete();
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource()==bt1){
            this.hide();
        }
        String id = t1.getSelectedItem().toString();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            String sql="delete from estudent where eid=?";
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
            String sql="select  eid from estudent";
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