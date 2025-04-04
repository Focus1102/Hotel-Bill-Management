package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import view.HDController;

public class DialogEdit extends JDialog {
    private JTextField maHDField, hoTenField, donGiaField, soThueField;
    private JComboBox<String> loaiHDComboBox;
    private JSpinner ngayHDSpinner;
    private HDController controller;
    private QuanLyHoaDon parent;

    public DialogEdit(QuanLyHoaDon parent, HDController controller, int maHD, String hoTen, Date ngayHD, String loaiHD, double donGia, int soThue) {
        super(parent, "Sửa Hóa Đơn", true);
        this.parent = parent;
        this.controller = controller;
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 5, 5));

        // Mã HĐ
        add(new JLabel("Mã HĐ:"));
        maHDField = new JTextField(String.valueOf(maHD));
        maHDField.setEditable(false);
        add(maHDField);

        // Họ tên
        add(new JLabel("Họ tên:"));
        hoTenField = new JTextField(hoTen);
        add(hoTenField);

        // Ngày HĐ
        add(new JLabel("Ngày HĐ:"));
        ngayHDSpinner = new JSpinner(new SpinnerDateModel(ngayHD, null, null, java.util.Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(ngayHDSpinner, "dd/MM/yyyy");
        ngayHDSpinner.setEditor(dateEditor);
        add(ngayHDSpinner);

        // Loại HĐ
        add(new JLabel("Loại HĐ:"));
        loaiHDComboBox = new JComboBox<>(new String[]{"Hóa Đơn Theo Giờ", "Hóa Đơn Theo Ngày"});
        loaiHDComboBox.setSelectedItem(loaiHD);
        add(loaiHDComboBox);

        // Đơn giá
        add(new JLabel("Đơn giá:"));
        donGiaField = new JTextField(String.valueOf(donGia));
        add(donGiaField);

        // Số giờ/ngày
        add(new JLabel("Số giờ/ngày:"));
        soThueField = new JTextField(String.valueOf(soThue));
        add(soThueField);

        // Nút sửa và hủy
        JButton editButton = new JButton("Sửa");
        JButton cancelButton = new JButton("Hủy");

        editButton.addActionListener(e -> editHoaDon());
        cancelButton.addActionListener(e -> dispose());

        add(editButton);
        add(cancelButton);
    }

    private void editHoaDon() {
        try {
            // Kiểm tra các trường bắt buộc
            String hoTen = hoTenField.getText().trim();
            if (hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập họ tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String donGiaStr = donGiaField.getText().trim();
            if (donGiaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đơn giá!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String soThueStr = soThueField.getText().trim();
            if (soThueStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số giờ/ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Lấy và kiểm tra dữ liệu
            int maHD = Integer.parseInt(maHDField.getText());
            Date ngayHD = (Date) ngayHDSpinner.getValue();
            String loaiHD = loaiHDComboBox.getSelectedItem().toString();
            boolean isHourBased = loaiHD.equals("Hóa Đơn Theo Giờ");
            
            double donGia = Double.parseDouble(donGiaStr);
            if (donGia <= 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soThue = Integer.parseInt(soThueStr);
            if (soThue <= 0) {
                JOptionPane.showMessageDialog(this, "Số giờ/ngày phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra ngày tháng với thời gian hiện tại
            Date currentDate = new Date();
            if (ngayHD.after(currentDate)) {
                JOptionPane.showMessageDialog(this, "Ngày hóa đơn không được lớn hơn ngày hiện tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            controller.editHoaDon(String.valueOf(maHD), hoTen, ngayHD, isHourBased, String.valueOf(soThue), String.valueOf(donGia));
            dispose();
        } catch (NumberFormatException ex) {
            String fieldName = "";
            if (ex.getMessage().contains("donGia")) {
                fieldName = "Đơn giá phải là số thực!";
            } else if (ex.getMessage().contains("soThue")) {
                fieldName = "Số giờ/ngày phải là số nguyên!";
            } else {
                fieldName = "Vui lòng nhập đúng định dạng số!";
            }
            JOptionPane.showMessageDialog(this, fieldName, "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
