package view.dialog;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import dto.HDInputDTO;

public class DialogSearchResult extends JDialog {
    private JLabel maHDLabel, hoTenLabel, ngayHDLabel, loaiHDLabel, donGiaLabel, soThueLabel, thanhTienLabel;
    private JButton closeButton;

    public DialogSearchResult(JFrame parent, HDInputDTO hoaDon) {
        super(parent, "Thông Tin Hóa Đơn", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Định dạng ngày tháng
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Mã HĐ
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Mã Hóa Đơn:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        maHDLabel = new JLabel(String.valueOf(hoaDon.getMaHD()));
        add(maHDLabel, gbc);

        // Họ tên
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Họ Tên:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        hoTenLabel = new JLabel(hoaDon.getHoTen());
        add(hoTenLabel, gbc);

        // Ngày HĐ
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Ngày Hóa Đơn:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        ngayHDLabel = new JLabel(dateFormat.format(hoaDon.getNgayHD()));
        add(ngayHDLabel, gbc);

        // Loại HĐ
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Loại Hóa Đơn:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        loaiHDLabel = new JLabel(hoaDon.getKHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày");
        add(loaiHDLabel, gbc);

        // Đơn giá
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Đơn Giá:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        donGiaLabel = new JLabel(String.format("%,.0fđ", hoaDon.getDonGia()));
        add(donGiaLabel, gbc);

        // Số giờ/ngày
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Số Giờ/Ngày:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        String soThue = hoaDon.getKHD().equals("SG") ? 
            String.format("%d giờ", hoaDon.getSoGioThue()) : 
            String.format("%d ngày", hoaDon.getSoNgayThue());
        soThueLabel = new JLabel(soThue);
        add(soThueLabel, gbc);

        // Thành tiền
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Thành Tiền:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        thanhTienLabel = new JLabel(String.format("%,.0fđ", (double)hoaDon.getThanhTien()));
        add(thanhTienLabel, gbc);

        // Nút đóng
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.gridwidth = 2;
        closeButton = new JButton("Đóng");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, gbc);
    }
} 