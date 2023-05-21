import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Component;
import java.awt.FlowLayout;

public class ComboBoxWithTitleExample {
    public static void main(String[] args) {
        String[] items = {"Item 1", "Item 2", "Item 3"};

        JComboBox<String> comboBox = new JComboBox<>(items);

        // Đặt đường viền và tiêu đề cho JComboBox
        comboBox.setBorder(BorderFactory.createTitledBorder("Combo Box Title"));

        // Đặt Renderer tùy chỉnh cho JComboBox
        comboBox.setRenderer(new CustomComboBoxRenderer());

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);
        frame.add(comboBox);
        frame.setVisible(true);
    }

    // Renderer tùy chỉnh để điều chỉnh giao diện của các item trong JComboBox
    static class CustomComboBoxRenderer extends JLabel implements ListCellRenderer<Object> {
        public CustomComboBoxRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            setFont(getFont().deriveFont(12.0f)); // Điều chỉnh kích thước phông chữ của item

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
}
