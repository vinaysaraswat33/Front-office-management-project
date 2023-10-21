import java.sql.*;
public class jdbc1 {

    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
            Statement s=c.createStatement();
            String sql="select * from estudent";
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()) {
                System.out.printf(rs.getInt(1)+" "+rs.getString(2)+"  "+ rs.getString(3)+"    "+rs.getString(4)+"    "+rs.getLong(5)+"  " +rs.getString(6));
                System.out.println("");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }

}

