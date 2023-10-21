import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.image.*;
public class Mymainmenu extends JFrame implements ActionListener {

    JMenuBar mb;
    JMenu admin,user,course,fee;
    JMenuItem adminlogin,newr,find,update,delete,insert;
    JMenuItem userlogin,newr1,find1,update1,delete1,insert1;
    JMenuItem Coursefind,Courseupdate,Coursedelete,Courseinsert;
    JMenuItem feefind,feeinsert,feeupdate,feedelete;
    JLabel l0,l1,l2,l3;
    ImageIcon im1,im2;
    Mymainmenu(){
        setSize(1200,1200);
        setTitle("FRONT OFFICE MANAGEMENT");
        setLayout(null);
        mb=new JMenuBar();

        l0=new JLabel("E-GAIN CODING INSTITUTE");
        l0.setBounds(250,80,500,70);
        l0.setFont(new Font("ARIAL BLACK",Font.BOLD,24));
        add(l0);
        l1=new JLabel("- B.Y :- T.J ARORA ");
        l1.setBounds(450,110,500,70);
        l1.setFont(new Font("ARIAL BLACK",Font.BOLD,20));
        add(l1);
        im1=new ImageIcon(this.getClass().getResource("/eg.jpg"));
        l2=new JLabel(im1);
        l2.setBounds(10,50,150,150);
        add(l2);
        im2=new ImageIcon(this.getClass().getResource("/eggg.jpg"));
        l3=new JLabel(im2);
        l3.setBounds(10,200,1080,500);
        add(l3);
//***********************************************************************************************************************************************
        admin=new JMenu("ADMIN");
        adminlogin=new JMenuItem("ADMIN LOGIN");
//        newr=new JMenuItem("NEW REGISTRATION");
//        find=new JMenuItem("FIND");
//        update=new JMenuItem("UPDATE");
//        delete=new JMenuItem("DELETE");
//        insert=new JMenuItem("INSERT");
        admin.add(adminlogin);
//        admin.addSeparator();
//        admin.add(newr);
//        admin.addSeparator();
//        admin.add(find);
//        admin.addSeparator();
//        admin.add(update);
//        admin.addSeparator();
//        admin.add(delete);
//        admin.addSeparator();
//        admin.add(insert);
////******************************************************************************************************************************************************
        user=new JMenu("USER");
        userlogin=new JMenuItem("USER LOGIN");
//        newr1=new JMenuItem("NEW REGISTRATION");
//        find1=new JMenuItem("FIND");
//        update1=new JMenuItem("UPDATE");
//        delete1=new JMenuItem("DELETE");
//        insert1=new JMenuItem("INSERT");
        user.add(userlogin);
//        user.addSeparator();
//        user.add(newr1);
//        user.addSeparator();
//        user.add(find1);
//        user.addSeparator();
//        user.add(update1);
//        user.addSeparator();
//        user.add(delete1);
//        user.addSeparator();
//        user.add(insert1);
//***********************************************************************************************************************************************
        mb.add(admin);
        mb.add(user);
//*********************************************************************************
        adminlogin.addActionListener(this);

//        newr.addActionListener(this);
//        find.addActionListener(this);
//        update.addActionListener(this);
//        insert.addActionListener(this);
//        delete.addActionListener(this);
//****************************************************************************************************************************************************
        setJMenuBar(mb);
        userlogin.addActionListener(this);
//        newr1.addActionListener(this);
//        find1.addActionListener(this);
//        update1.addActionListener(this);
//        insert1.addActionListener(this);
//        delete1.addActionListener(this);
//...................................................................................................................................................
        course=new JMenu("COURSE");
//        Coursedelete=new JMenuItem("DELETE");
        Coursefind=new JMenuItem("FIND_COURSE");
//        Courseinsert=new JMenuItem("INSERT_NEW COURSE");
//        Courseupdate=new JMenuItem("UPDATION_COURSE");
        course.add(Coursefind);
//        course.addSeparator();
//        course.add(Courseupdate);
//        course.addSeparator();
//        course.add(Courseinsert);
//        course.addSeparator();
//        course.add(Coursedelete);
        mb.add(course);
//        Courseupdate.addActionListener(this);
//        Courseinsert.addActionListener(this);
        Coursefind.addActionListener(this);
//        Coursedelete.addActionListener(this);
//.......................................................................................................................................
        fee=new JMenu("FEE");
//        feedelete=new JMenuItem("DELETE");
        feefind=new JMenuItem("FIND_COURSE_FEE");
//        feeinsert=new JMenuItem("INSERT_NEW COURSE");
//        feeupdate=new JMenuItem("UPDATION_COURSE_FEE");
        fee.add(feefind);
//        fee.addSeparator();
//        fee.add(feeupdate);
//        fee.addSeparator();
//        fee.add(feeinsert);
//        fee.addSeparator();
//        fee.add(feedelete);
        mb.add(fee);
        feefind.addActionListener(this);
//       feedelete.addActionListener(this);
//        feeupdate.addActionListener(this);
//        feeinsert.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==adminlogin){
            new Adminlogin();
        }
        if(e.getSource()==userlogin){
            new Userlogin();
        }
        if(e.getSource()==find){
            new MyDataFind();
        }
        if(e.getSource()==newr){
            new Newregister();
        }
        if(e.getSource()==delete){
            new MyDataDelete();
        }
        if(e.getSource()==update){
            new MyDataupdate();
        }
        if(e.getSource()==insert){
            new MyDataInsert();
        }
//...................................................................................................................................................
        if(e.getSource()==find1){
            new MyDataFind();
        }
        if(e.getSource()==newr1){
            new Newregister();
        }
        if(e.getSource()==delete1){
            new MyDataDelete();
        }
        if(e.getSource()==update1){
            new MyDataupdate();
        }
        if(e.getSource()==insert1){
            new MyDataInsert();
        }
//.......................................................................................................................................
        if(e.getSource()==Coursefind){
            new MyCourseFind();
        }
        if(e.getSource()==feefind){
            new MyFeeFind();
        }
    }

    public static void main(String[] args) {
        new Mymainmenu();
    }
}
