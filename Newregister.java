import oracle.net.ns.NetException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Newregister extends JFrame implements ActionListener {
        JLabel l0, l1, l2, l3, l4, l5,l6;
        JTextField t1, t2, t3, t4, t5,t6;
        JButton b0, b1;
        Newregister() {
        setTitle("NEW REGISTER");
        setSize(800, 800);
        setLayout(null);
        l0 = new JLabel("E-GAIN CODDING INSTITUE  BY-T.J SIR");
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
        l5.setBounds(30, 390, 150, 50);
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

        b0 = new JButton("SAVE");
        b0.setBounds(200, 500, 70, 30);
        b0.addActionListener(this);
        add(b0);
        b1 = new JButton("CLOSE");
        b1.setBounds(350, 500, 90, 30);
        b1.addActionListener(this);
        add(b1);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

        public static void main (String[]args){
        Newregister obj=new Newregister();
    }

        @Override
        public void actionPerformed (ActionEvent e)
        {
            if(e.getSource()==b0) {
                int id = Integer.parseInt(t1.getText());
                String name = t2.getText();
                String college = t3.getText();
                String email = t4.getText();
                Long mob = Long.parseLong(t5.getText());
                String gov = t6.getText();
                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "System", "manager");
                    String sql = "insert into estudent values(?,?,?,?,?,?)";
                    PreparedStatement s = con.prepareStatement(sql);
                    s.setInt(1, id);
                    s.setString(2, name);
                    s.setString(3, college);
                    s.setString(4, email);
                    s.setLong(5, mob);
                    s.setString(6, gov);
                    int f = s.executeUpdate();
                    JOptionPane.showMessageDialog(this, "record saved.");

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            if(e.getSource()==b1){
                this.hide();
            }

    }

}
