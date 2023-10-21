import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
class MyFeesInsert extends JFrame implements ActionListener {
    JLabel l0,l1,l2,l3,l4,l5,l6;
    JTextField t1,t2,t3,t4,t5;
    JButton bt,bt1,bt2,bt3;
    MyFeesInsert() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("FEES INSERT");
        l0.setFont(new Font("Arial Black",Font.BOLD,24));
        l0.setBounds(200,30,250,70);
        add(l0);
        l1=new JLabel("CS_ID");
        l1.setBounds(100,130,100,50);
        l1.setFont(new Font("Arial",Font.BOLD,14));
        add(l1);
        l2=new JLabel("CS_NAME");
        l2.setBounds(100,190,100,50);
        l2.setFont(new Font("Arial",Font.BOLD,14));
        add(l2);
        l3=new JLabel("CS_FEES");
        l3.setBounds(100,250,150,50);
        l3.setFont(new Font("Arial",Font.BOLD,14));
        add(l3);
        l4=new JLabel("INSTALLMENT_01");
        l4.setBounds(100,310,150,50);
        l4.setFont(new Font("Arial",Font.BOLD,14));
        add(l4);
        l5=new JLabel("INSTALLMENT_02");
        l5.setBounds(100,370,150,50);
        l5.setFont(new Font("Arial",Font.BOLD,14));
        add(l5);
        l6=new JLabel("eg: CS01");
        l6.setBounds(550,120,50,50);
        add(l6);
        t1=new JTextField();
        t1.setBounds(300,130,150,40);
        add(t1);
        t2=new JTextField();
        t2.setBounds(300,190,150,40);
        add(t2);
        t3=new JTextField();
        t3.setBounds(300,250,150,40);
        add(t3);
        t4=new JTextField();
        t4.setBounds(300,310,150,40);
        add(t4);
        t5=new JTextField();
        t5.setBounds(300,370,150,40);
        add(t5);
        bt=new JButton("Save");
        bt.setBounds(100,450,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1 = new JButton("Close");
        bt1.setBounds(400, 450, 100, 50);
        add(bt1);
        bt1.addActionListener(this);
        bt2=new JButton("New");
        bt2.setBounds(250,450,80,50);
        bt2.addActionListener(this);
        add(bt2);
        bt3=new JButton("check");
        bt3.setBounds(650,130,100,50);
        bt3.addActionListener(this);
        add(bt3);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFeesInsert();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==bt) {
            String id = t1.getText();
            String name = t2.getText();
            String fees = t3.getText();
            String installment01 = t4.getText();
            String installment02 = t5.getText();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
                String sql = "insert into fee values(?,?,?,?,?)";
                PreparedStatement s = con.prepareStatement(sql);
                s.setString(1, id);
                if(enqid(id)==true){
                    s.setString(1,id);
                }
                else{
                    JOptionPane.showMessageDialog(this,"this id already exist");
                }
                s.setString(2, name);
                s.setString(3, fees);
                s.setString(4, installment01);
                s.setString(5, installment02);

                int f = s.executeUpdate();
                JOptionPane.showMessageDialog(this, "record saved.");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if(ae.getSource()==bt1){
            this.hide();
        }
        if(ae.getSource()==bt2){
            this.enable();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
        }
        if(ae.getSource()==bt3){
            String id=(t1.getText());
            System.out.println(enqid(id));
            if(enqid(id)==true){
                JOptionPane.showMessageDialog(this,"Valid");
            }
            else {
                JOptionPane.showMessageDialog(this,"IN Evalid id");
            }
        }
    }
     boolean enqid(String id)  {
        boolean st=true;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
            String sql = "select * from course where ecourseid=?";
            PreparedStatement s = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            s.setString(1,id);
            ResultSet rs = s.executeQuery();
            if (rs.next()) {
                st=false;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return st;
    }
}