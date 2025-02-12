package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DialogEdit extends JDialog {
    private JTextField maHDField, hoTenField, donGiaField, soThueField;
    private JFormattedTextField ngayHDField;
    private JButton editButton, cancelButton;
    private JComboBox<String> loaiHDComboBox;
    private QuanLyHoaDon parent;

    public DialogEdit(QuanLyHoaDon parent) {
        this.parent = parent;
        setTitle("Sửa Hóa Đơn");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setModal(true);

        // Sử dụng GridBagLayout để căn chỉnh giao diện
        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần

        // Mã HĐ
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(new JLabel("Mã HĐ:"), constraints);
        constraints.gridx = 1;
        maHDField = new JTextField(20);
        add(maHDField, constraints);

        // Họ tên
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(new JLabel("Họ Tên:"), constraints);
        constraints.gridx = 1;
        hoTenField = new JTextField(20);
        add(hoTenField, constraints);

        // Ngày HĐ (Sử dụng JFormattedTextField để đảm bảo định dạng ngày)
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("Ngày HĐ:"), constraints);
        constraints.gridx = 1;
        ngayHDField = new JFormattedTextField(new java.text.SimpleDateFormat("dd/MM/yyyy"));
        ngayHDField.setValue(new Date()); // Ngày hiện tại mặc định
        add(ngayHDField, constraints);

        // Loại HĐ (Theo ngày hoặc theo giờ)
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(new JLabel("Loại HĐ:"), constraints);
        constraints.gridx = 1;
        loaiHDComboBox = new JComboBox<>(new String[]{"Theo Ngày", "Theo Giờ"});
        add(loaiHDComboBox, constraints);

        // Số ngày/giờ thuê
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(new JLabel("Số Ngày Thuê/ Số Giờ Thuê:"), constraints);
        constraints.gridx = 1;
        soThueField = new JTextField(20);
        add(soThueField, constraints);

        // Đơn giá
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(new JLabel("Đơn Giá:"), constraints);
        constraints.gridx = 1;
        donGiaField = new JTextField(20);
        add(donGiaField, constraints);

        // Nút sửa và hủy
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Sửa");
        cancelButton = new JButton("Hủy");
        buttonPanel.add(editButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, constraints);

        // Xử lý sự kiện
        editButton.addActionListener(e -> editHoaDon());
        cancelButton.addActionListener(e -> setVisible(false));
    }

    private void editHoaDon() {
        try {
            int maHD = Integer.parseInt(maHDField.getText());
            String hoTen = hoTenField.getText();
            double donGia = Double.parseDouble(donGiaField.getText());
            String loaiHD = (String) loaiHDComboBox.getSelectedItem();
            int soThue = Integer.parseInt(soThueField.getText());
            Date ngayHD = (Date) ngayHDField.getValue(); // Lấy ngày từ JFormattedTextField

            // Kiểm tra và thực hiện chỉnh sửa hóa đơn
            parent.editHD(maHD, soThue, hoTen, ngayHD, donGia, loaiHD, soThue);
            setVisible(false); // Đóng dialog
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng các giá trị.");
        }
    }
}
