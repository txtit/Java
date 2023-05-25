import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tính toán");
        frame.setLayout(null);

        // Tạo các thành phần và đặt vị trí
        JLabel labelThanhToan = new JLabel("Thanh toán:");
        labelThanhToan.setBounds(10, 10, 100, 30);

        JTextField textFieldThanhToan = new JTextField();
        textFieldThanhToan.setBounds(120, 10, 100, 30);

        JLabel labelKhachTra = new JLabel("Khách trả:");
        labelKhachTra.setBounds(10, 50, 100, 30);

        JTextField textFieldKhachTra = new JTextField();
        textFieldKhachTra.setBounds(120, 50, 100, 30);

        JLabel labelTienThua = new JLabel("Tiền thừa:");
        labelTienThua.setBounds(10, 90, 100, 30);

        JTextField textFieldTienThua = new JTextField();
        textFieldTienThua.setBounds(120, 90, 100, 30);

        // Thêm các thành phần vào khung giao diện
        frame.add(labelThanhToan);
        frame.add(textFieldThanhToan);
        frame.add(labelKhachTra);
        frame.add(textFieldKhachTra);
        frame.add(labelTienThua);
        frame.add(textFieldTienThua);

        // Xử lý sự kiện khi người dùng nhập giá trị vào ô "Khách Trả"
        textFieldKhachTra.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String thanhToanStr = textFieldThanhToan.getText();
                String khachTraStr = textFieldKhachTra.getText();

                if (!thanhToanStr.isEmpty() && !khachTraStr.isEmpty()) {
                    try {
                        double thanhToan = Double.parseDouble(thanhToanStr);
                        double khachTra = Double.parseDouble(khachTraStr);

                        double tienThua = khachTra - thanhToan;

                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
                        symbols.setDecimalSeparator('.');
                        symbols.setGroupingSeparator(',');
                        DecimalFormat df = new DecimalFormat("#,###.##", symbols);

                        textFieldTienThua.setText(df.format(tienThua));
                    } catch (NumberFormatException ex) {
                        textFieldTienThua.setText("");
                    }
                } else {
                    textFieldTienThua.setText("");
                }
            }
        });

        frame.setSize(250, 170);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
