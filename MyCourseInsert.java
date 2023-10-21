import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class MyCourseInsert extends JFrame implements ActionListener {
    JLabel l0,l1,l2,l3,l4,l5;
    JTextField t1,t2,t3,t4;
    JButton bt,bt1,bt2,bt3;
    public MyCourseInsert() {
        setSize(800, 800);
        setLayout(null);
        l0=new JLabel("COURSE INSERT");
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
        l3=new JLabel("CS_DURATION");
        l3.setBounds(100,250,150,50);
        l3.setFont(new Font("Arial",Font.BOLD,14));
        add(l3);
        l4=new JLabel("CS_CERTIFIED");
        l4.setBounds(100,310,150,50);
        l4.setFont(new Font("Arial",Font.BOLD,14));
        add(l4);
        l5=new JLabel("e.g:- CS01");
        l5.setBounds(500,125,100,50);
        l5.setFont(new Font("Arial",Font.BOLD,14));
        add(l5);
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
        bt=new JButton("Save");
        bt.setBounds(100,450,100,50);
        add(bt);
        bt.addActionListener(this);
        bt1=new JButton("Close");
        bt1.setBounds(400,450,100,50);
        bt1.addActionListener(this);
        add(bt1);
        bt2=new JButton("New");
        bt2.setBounds(250,450,80,50);
        bt2.addActionListener(this);
        add(bt2);
        bt3=new JButton("Check");
        bt3.setBounds(650,130,100,50);
        bt3.addActionListener(this);
        add(bt3);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyCourseInsert();
    }

    @Override
    public void actionPerformed(ActionEvent ar) {
        if(ar.getSource()==bt) {
            String id = t1.getText();
            String name = t2.getText();
            String duration = t3.getText();
            String certified = t4.getText();
            try {

                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
                String sql = "insert into course values(?,?,?,?)";
                PreparedStatement s = con.prepareStatement(sql);

                s.setString(1, id);
                s.setString(2, name);
                s.setString(3, duration);
                s.setString(4, certified);

                int f = s.executeUpdate();
                JOptionPane.showMessageDialog(this, "record saved.");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        if(ar.getSource()==bt1){
            this.hide();
        }
        if(ar.getSource()==bt2){
            this.enable();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
        }
        if(ar.getSource()==bt3){
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