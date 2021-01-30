package utsv1.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame{
    public JPanel landing;
    private JButton masukButton;
    private JButton registerButton;
    private JButton loginButton;

    public Main() {
        this.setTitle("Selamat Datang di Booking.id");
        setContentPane(landing);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        masukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                new auth();
            }


////        JFrame panel = new JFrame("Selamat datang di Booking.id");
////        panel.setContentPane(new Main().landing);
////        panel.setLocationRelativeTo(null);
////        panel.setSize(600, 600);
////        panel.pack();
////        panel.setVisible(true);
////        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        loginButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                login Login;
//                Login = new login();
//                Login.setVisible(true);
//                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                dispose();
//            }
//        });
//        registerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//            }
//        });
//        setContentPane(landing);
//        setLocationRelativeTo(null);
//        setPreferredSize(new Dimension( 480, 480));
//        pack();
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }


    public static void main(String args[]){
        Main main = new Main();

    }

}
