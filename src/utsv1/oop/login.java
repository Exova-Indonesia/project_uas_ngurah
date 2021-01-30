package utsv1.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class login extends JFrame {
    public JButton login;
    public JPanel panel1;
    public JTextField username;
    public JPasswordField password;
    private JButton back;

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;


    public login() {

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    sql = "SELECT * FROM users WHERE username='"+username.getText()+"'AND password='"+password.getText()+"'";
                    rs = stat.executeQuery(sql);
                    if(rs.next()) {
                        if(username.getText().equals(rs.getString("username")) && password.getText().equals(rs.getString("password"))) {
                            dispose();
                            JOptionPane.showMessageDialog(null, "Login Berhasil !");
                            new order(rs.getString("id"), rs.getString("username"));
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Password atau username salah !");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                Main main = new Main();
                main.setVisible(true);
            }
        });
            setContentPane(panel1);
            setLocationRelativeTo(null);
            setPreferredSize(new Dimension( 480, 480));
            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//    public static void main(String[] args) {
//       JFrame panel = new JFrame("Login");
//    }
}
