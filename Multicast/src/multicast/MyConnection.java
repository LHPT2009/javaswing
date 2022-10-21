package multicast;

import java.sql.*;
import javax.swing.*;

public class MyConnection {

//    public Connection getConnection() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String URL = "jdbc:sqlserver://localhost:1433;Database=QLUser;user=an;password=123";
//            Connection con = DriverManager.getConnection(URL);
//            return con;
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return null;
//        }
//    }
    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:1433;Database=QLSinhVien;user=an;password=123";
            Connection con = DriverManager.getConnection(URL);
            return con;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}
