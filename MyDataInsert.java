import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDataInsert extends JFrame implements ActionListener {
    JTextField t1,t2,t3,t4,t5,t6;
    JLabel l0,l1,l2,l3,l4,l5,l6;
    JButton b0,b1,bt2,bt3;
    public MyDataInsert() {
        setSize(800, 800);
        setLayout(null);
        l0 = new JLabel("STUDENT DATA INSERT");
        l0.setBounds(250, 70, 800, 50);
        l0.setFont(new Font("Arial",Font.BOLD,28));
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

        b0 = new JButton("Close");
        b0.setBounds(500, 550, 80, 50);
        b0.addActionListener(this);
        add(b0);
        b1 = new JButton("Save");
        b1.setBounds(350, 550, 80, 50);
        b1.addActionListener(this);
        add(b1);
        bt2=new JButton("New");
        bt2.setBounds(200,550,80,50);
        bt2.addActionListener(this);
        add(bt2);
        bt3=new JButton("Check");
        bt3.setBounds(500,150,80,50);
        bt3.addActionListener(this);
        add(bt3);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyDataInsert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b0){
           this.hide();
        }
        if(e.getSource()==b1) {
            String id = (t1.getText());
            String name = t2.getText();
            String college = t3.getText();
            String email = t4.getText();
            String mob = (t5.getText());
            String gov = t6.getText();
            try {

                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
                    String sql = "insert into estudent values(?,?,?,?,?,?)";
                    PreparedStatement s = con.prepareStatement(sql);

                    s.setString(1, id);

                    s.setString(2, name);
                    s.setString(3, college);

                {if(mailvalidtae(email)==true){
                    s.setString(4, email);}
                    else{
                        JOptionPane.showMessageDialog(this,"please enter valid email id");
                    }}
                {if(mob.length()==10){
                    s.setString(5, mob);}
                    else{
                        JOptionPane.showMessageDialog(this,"mobile number length should be 10");
                    }}
                    s.setString(6, gov);
                    int f = s.executeUpdate();
                    JOptionPane.showMessageDialog(this, "record saved.");
                  t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");


            } catch (Exception ex) {
                System.out.println(ex);
//                JOptionPane.showMessageDialog(this,"please check fill all data field");
            }
        }
        if(e.getSource()==bt2){
            this.enable();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
        }
        if(e.getSource()==bt3){
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
    boolean mailvalidtae(String email) {
        String regex = "[\\w-]{1,30}@\\w{2,20}.\\w{2,3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    boolean enqid(String id)  {
        boolean st=true;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
            String sql = "select * from estudent where eid=?";
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