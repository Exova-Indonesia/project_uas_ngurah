package utsv1.oop;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class order extends JFrame {
    private JComboBox pilih;
    private JPanel orderPanel;
    private JButton bookingButton;
    private JLabel permalam;
    private JLabel subtotal;
    private JLabel judul;
    private JLabel getDiscount;
    private JLabel totalAll;
    private JTextField dates;
    private JButton kalkulasi;

    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;

    public order(String user_id, String username) {

        Random r = new Random();
        var id = r.nextInt();

        Koneksi DB = new Koneksi();
        DB.config();
        con = DB.con;
        stat = DB.stm;

        this.setTitle("Silahkan order hotel yang anda inginkan, "+username);
        add(orderPanel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pilih.addItem("Pilih Hotel");
        judul.setText("Booking Hotelmu Sekarang, "+username);


        try {
            sql = "SELECT * FROM hotels";
            rs = stat.executeQuery(sql);
            while(rs.next()) {
                pilih.addItem(rs.getString("hotel_name"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        NumberFormat nf2 = NumberFormat.getInstance(new Locale("en", "US"));
        kalkulasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    sql = "SELECT * FROM hotels WHERE hotel_name='"+pilih.getSelectedItem()+"'";
                    rs = stat.executeQuery(sql);
                    while(rs.next()) {
                        int day = Integer.parseInt(dates.getText());
                        float subtotals = rs.getInt("pernight_price") * day;
                        permalam.setText(String.valueOf("IDR "+nf2.format(rs.getInt("pernight_price"))));
                        subtotal.setText(String.valueOf("IDR "+nf2.format(subtotals)));
                        if(subtotals > 250000) {
                            double discount = 0.15;
                            getDiscount.setText(String.valueOf(discount*100+"%"));
                            double totalDisc = subtotals*discount;
                            double total = subtotals - totalDisc;
                            totalAll.setText(String.valueOf("IDR "+nf2.format(total)));
                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
        bookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    sql = "INSERT INTO orders (order_id, user_id, order_name, order_hotel, order_price, order_discount, order_total) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')";
                    sql = String.format(sql, id,  1, "Pesan Hotel", pilih.getSelectedItem(), permalam.getText().replaceAll("[IDR,]", ""), getDiscount.getText().replaceAll("[%]", ""), totalAll.getText().replaceAll("[IDR,]", ""));
                    stat.execute(sql);
                    dispose();
                    JOptionPane.showMessageDialog(null, "Berhasil memesan");
                    new invoice(id, 1);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        });
    }

//    public static void main(String[] args) {
//        new order(1);
//    }
}

