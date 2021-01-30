package utsv1.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class auth extends JFrame{
    private JPanel MainPanel;
    private JPanel LoginPanel;
    private JPanel RegisterPanel;
    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField useregister;
    private JTextField emailresgister;
    private JPasswordField passwordregister;
    private JButton register;
    private JButton loginBack;

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    Container panel;

    public auth() {
        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        //JFrame window = new JFrame("Selamat datang di Booking.id");
        switchPanels(LoginPanel);

        // Fungsi Login

        loginButton.addActionListener(new ActionListener() {
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

        // Fungsi Register

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    sql = "INSERT INTO users (username, email, password) VALUES('%s', '%s', '%s')";
                    sql = String.format(sql, useregister.getText(), emailresgister.getText(), passwordregister.getText());
                    stat.execute(sql);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Registrasi Berhasil !, Silahkan Login");
                        switchPanels(LoginPanel);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });


        // Switch Panel Login & Register

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switchPanels(RegisterPanel);
            }


        });
        loginBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switchPanels(LoginPanel);
            }
        });

    }

//    public static void main(String[] args) {
//        new auth();
//    }

    // Fungsi Switch Panel

    public void switchPanels(JPanel panel) {
        this.setTitle("Selamat datang di Booking.id");
        setContentPane(panel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
