import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.image.*;
class Usermenu extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu user;
    JMenuItem  newr, find, update, delete, insert;

    JLabel l0, l1;

    Usermenu() {
        setSize(1200, 1200);
        setTitle("user MANAGEMENT");
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
        user = new JMenu("USER");
        newr = new JMenuItem("NEW REGISTRATION");
        find = new JMenuItem("FIND");
        update = new JMenuItem("UPDATE");
        delete = new JMenuItem("DELETE");
        insert = new JMenuItem("INSERT");
        user.add(newr);
        user.addSeparator();
        user.add(find);
        user.addSeparator();
        user.add(update);
        user.addSeparator();
        user.add(delete);
        user.addSeparator();
        user.add(insert);
        mb.add(user);
        newr.addActionListener(this);
        find.addActionListener(this);
        update.addActionListener(this);
        insert.addActionListener(this);
        delete.addActionListener(this);

        setJMenuBar(mb);
        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
    }

    public static void main(String[] args) {
        new Usermenu();
    }
}
