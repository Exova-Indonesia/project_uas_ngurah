package utsv1.oop;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class invoice extends JFrame {
    private JPanel invoice;
    private JLabel discount;
    private JLabel subtotal;
    private JLabel cin;
    private JLabel cout;
    private JLabel pernight;
    private JLabel name_hotel;
    private JLabel name_c;
    private JLabel total;
    private JButton done;

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    public invoice(int order, int id) {

        setContentPane(invoice);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        try {
            sql = "SELECT * FROM orders AS A INNER JOIN users AS B ON B.id = A.user_id WHERE A.order_id='"+order+"'";
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                name_c.setText(rs.getString("username"));
                name_hotel.setText(rs.getString("order_hotel"));
                pernight.setText(rs.getString("order_price"));
                discount.setText(rs.getString("order_discount"));
                total.setText(rs.getString("order_total"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        done.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Terima Kasih atas Kepercayaan Anda Bersama Booking.id");
            }
        });
    }

//    public static void main(String[] args) {
//        new invoice(892387070, 1);
//    }
}
