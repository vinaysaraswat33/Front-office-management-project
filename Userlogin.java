import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Userlogin extends JFrame implements ActionListener {

    JLabel l1,l2;
    JTextField t1;
    JPasswordField t2;
    JButton b1,b2,b3;


    Userlogin(){
        setTitle("USER LOGIN");
        setLayout(null);
        setSize(800,800);
        l1=new JLabel("USER ID");
        l1.setBounds(150,200,100,30);
        l1.setFont(new Font("ARIAL",Font.BOLD,18));
        add(l1);
        l2=new JLabel("PASSWORD");
        l2.setBounds(150,300,150,30);
        l2.setFont(new Font("ARIAL",Font.BOLD,18));
        add(l2);
        t1=new JTextField();
        t1.setBounds(300,200,150,30);
        add(t1);
        t2=new JPasswordField();
        t2.setBounds(300,300,150,30);
        add(t2);
        b1=new JButton("LOGIN");
        b1.setBounds(350,400,100,50);
        b1.addActionListener(this);
        add(b1);
        b2=new JButton("Close");
        b2.setBounds(200,400,100,50);
        b2.addActionListener(this);
        add(b2);
        b3=new JButton("Newregister");
        b3.setBounds(270,500,150,50);
        b3.addActionListener(this);
        add(b3);

        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)  {
        Userlogin obj=new Userlogin();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String m=t1.getText();
            String n= t2.getText();
            if(m.equals("admin") && n.equals("123")) {
                JOptionPane.showMessageDialog(this, "Login successfully");
                new Usermenu();
            }
            else
                JOptionPane.showMessageDialog(this,"Failed");
        }

        if(e.getSource()==b2)
            this.hide();
        if(e.getSource()==b3){
            new Newregister();
        }
    }


}

