package view.dialog;

import javax.swing.*;

import view.HDController;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogAdd extends JDialog {
    private JTextField maHDField, hoTenField, ngayHDField, donGiaField, soThueField;
    private JButton addButton, cancelButton;
    private JComboBox<String> loaiHDComboBox;

    private HDController addHDController;

    public DialogAdd(QuanLyHoaDon parent,HDController addHDController) {
        this.addHDController = addHDController;
        setTitle("Thêm Hóa Đơn");
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

        // Ngày HĐ
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(new JLabel("Ngày HĐ:"), constraints);
        constraints.gridx = 1;
        ngayHDField = new JTextField(20);
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

        // Nút thêm và hủy
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Thêm");
        cancelButton = new JButton("Hủy");
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, constraints);

        // Xử lý sự kiện
        addButton.addActionListener(e -> addHoaDon());
        cancelButton.addActionListener(e -> setVisible(false));
    }

    private void addHoaDon() {
        try {
            // Thu thập dữ liệu từ giao diện
            String maHD = maHDField.getText().trim();
            String hoTen = hoTenField.getText().trim();
            String ngayThueStr = ngayHDField.getText().trim();
            String donGiaStr = donGiaField.getText().trim();
            String soThueStr = soThueField.getText().trim();
            boolean isHourBased = "Theo Giờ".equals(loaiHDComboBox.getSelectedItem());

            // Kiểm tra các trường bắt buộc
            if (maHD.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (ngayThueStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (donGiaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (soThueStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số giờ/ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Kiểm tra định dạng ngày tháng
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false); // Không cho phép ngày không hợp lệ
            Date ngayThue = dateFormat.parse(ngayThueStr);
    
            // Gọi Controller để thực hiện logic
            addHDController.addHoaDon(maHD, hoTen, ngayThue, isHourBased, soThueStr, donGiaStr);
            dispose();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày hóa đơn không hợp lệ!\nVui lòng nhập theo định dạng dd/MM/yyyy", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

  
}
