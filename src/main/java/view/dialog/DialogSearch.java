package view.dialog;

import javax.swing.*;
import java.awt.*;
import view.HDController;

public class DialogSearch extends JDialog {
    private JTextField idField; // Thay đổi từ hoTenField thành idField
    private JButton searchButton, cancelButton;
    private  HDController searchHDcontroller;

    public DialogSearch(JFrame parent, HDController searchHDcontroller) {
        super(parent, "Tìm Kiếm Hóa Đơn", true);
        this.searchHDcontroller = searchHDcontroller;

        // Cài đặt kích thước và bố cục
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(3, 2));

        // Nhập ID
        add(new JLabel("ID Hóa Đơn:")); // Sửa nhãn cho ID
        idField = new JTextField(); // Thay đổi từ hoTenField sang idField
        add(idField);

        // Nút tìm kiếm và hủy
        searchButton = new JButton("Tìm");
        cancelButton = new JButton("Hủy");

        searchButton.addActionListener(e -> searchHoaDon()); // Sửa lại hành động tìm kiếm
        cancelButton.addActionListener(e -> setVisible(false));

        add(searchButton);
        add(cancelButton);
    }

    private void searchHoaDon() {
        // Kiểm tra đầu vào là ID
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ID để tìm kiếm!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idText); // Chuyển từ chuỗi sang số nguyên
            searchHDcontroller.searchById(id); // Gọi controller để tìm kiếm theo ID
            setVisible(false);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID phải là một số nguyên hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
