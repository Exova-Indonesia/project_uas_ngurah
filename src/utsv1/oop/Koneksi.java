package utsv1.oop;
import javax.swing.*;
import java.sql.*;

public class Koneksi {
    Connection con;
    Statement stm;

    public void config(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ngrh_db?useTimezone=true&serverTimezone=UTC", "root", "Q67Lazcd");
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
}
