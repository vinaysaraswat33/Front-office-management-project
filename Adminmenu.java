import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.image.*;
 class Adminmenu extends JFrame implements ActionListener {
     JMenuBar mb;
     JMenu admin,course,fee,enquiry;
     JMenuItem  newr, find, update, delete, insert;
     JMenuItem Coursefind,Courseupdate,Coursedelete,Courseinsert;
     JMenuItem feefind,feeinsert,feeupdate,feedelete;
     JMenuItem EnquiryInsert,EnquiryFind,Enquiryupdate,EnquiryDelete;
     JLabel l0, l1;

     Adminmenu() {
         setSize(1200, 1200);
         setTitle("ADMIN MANAGEMENT");
         setLayout(null);
         mb = new JMenuBar();

         l0 = new JLabel("E-GAIN CODING INSTITUTE");
         l0.setBounds(250, 80, 500, 70);
         l0.setFont(new Font("ARIAL BLACK", Font.BOLD, 24));
         add(l0);
         l1 = new JLabel("- B.Y :- T.J ARORA ");
         l1.setBounds(450, 110, 500, 70);
         l1.setFont(new Font("ARIAL BLACK", Font.BOLD, 20));
         add(l1);

//***********************************************************************************************************************************************
         admin = new JMenu("ADMIN");
         newr = new JMenuItem("NEW REGISTRATION");
         find = new JMenuItem("FIND");
         update = new JMenuItem("UPDATE");
         delete = new JMenuItem("DELETE");
         insert = new JMenuItem("INSERT");
         admin.add(newr);
         admin.addSeparator();
         admin.add(find);
         admin.addSeparator();
         admin.add(update);
         admin.addSeparator();
         admin.add(delete);
         admin.addSeparator();
         admin.add(insert);
         mb.add(admin);
         newr.addActionListener(this);
         find.addActionListener(this);
         update.addActionListener(this);
         insert.addActionListener(this);
         delete.addActionListener(this);
//...............................................................................................................................................................
         course=new JMenu("COURSE");
        Coursedelete=new JMenuItem("DELETE");
         Coursefind=new JMenuItem("FIND_COURSE");
        Courseinsert=new JMenuItem("INSERT_NEW COURSE");
        Courseupdate=new JMenuItem("UPDATION_COURSE");
         course.add(Coursefind);
        course.addSeparator();
        course.add(Courseupdate);
        course.addSeparator();
        course.add(Courseinsert);
        course.addSeparator();
        course.add(Coursedelete);
         mb.add(course);
        Courseupdate.addActionListener(this);
        Courseinsert.addActionListener(this);
         Coursefind.addActionListener(this);
        Coursedelete.addActionListener(this);
//.......................................................................................................................................
         fee=new JMenu("FEE");
        feedelete=new JMenuItem("DELETE");
         feefind=new JMenuItem("FIND_COURSE_FEE");
        feeinsert=new JMenuItem("INSERT_NEW COURSE");
        feeupdate=new JMenuItem("UPDATION_COURSE_FEE");
         fee.add(feefind);
        fee.addSeparator();
        fee.add(feeupdate);
        fee.addSeparator();
        fee.add(feeinsert);
        fee.addSeparator();
        fee.add(feedelete);
         mb.add(fee);
         feefind.addActionListener(this);
       feedelete.addActionListener(this);
        feeupdate.addActionListener(this);
        feeinsert.addActionListener(this);
  //................................................................................................................................
         enquiry=new JMenu("ENQUIRY");
         Enquiryupdate=new JMenuItem("ENQUIRY UPDATE");
         EnquiryDelete=new JMenuItem("ENQUIRY DELETE");
         EnquiryFind=new JMenuItem("ENQUIRY FIND");
         EnquiryInsert=new JMenuItem("ENQUIRY INSERT");
         enquiry.add(EnquiryInsert);
         enquiry.addSeparator();
         enquiry.add(EnquiryFind);
         enquiry.addSeparator();
         enquiry.add(Enquiryupdate);
         enquiry.addSeparator();
         enquiry.add(EnquiryDelete);
         EnquiryInsert.addActionListener(this);
         Enquiryupdate.addActionListener(this);
         EnquiryFind.addActionListener(this);
         EnquiryDelete.addActionListener(this);
         mb.add(enquiry);
         setJMenuBar(mb);
         setVisible(true);
//         setDefaultCloseOperation(EXIT_ON_CLOSE);
     }

     @Override
     public void actionPerformed(ActionEvent e) {
         if(e.getSource()==find){
             new MyDataFind();
         }
         if(e.getSource()==newr){
             new MyDataInsert();
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
  //...........................................................................................................................................................
         if(e.getSource()==Coursedelete){
             new MyCourseDelete();
         }
         if(e.getSource()==Coursefind){
             new MyCourseFind();
         }
         if(e.getSource()==Courseinsert){
             new MyCourseInsert();
         }
         if(e.getSource()==Courseupdate){
             new MyCourseUpdate();
         }
         //..............................................................................................................................................
         if(e.getSource()==feedelete){
             new MyFeesDelete();
         }
         if(e.getSource()==feefind){
             new MyFeeFind();
         }
         if(e.getSource()==feeinsert){
             new MyFeesInsert();
         }
         if(e.getSource()==feeupdate){
             new MyFeesUpdate();
         }
//........................................................................................................................................................
         if(e.getSource()==EnquiryInsert){
             new MyEnqInsert();
         }
         if(e.getSource()==EnquiryFind){
             new MyEnqFind();
         }
         if(e.getSource()==Enquiryupdate){
             new MyEnqUpdate();
         }
         if(e.getSource()==EnquiryDelete){
             new MyEnqDelete();
         }
     }

     public static void main(String[] args) {
         new Adminmenu();
     }
 }
