package view.dialog;

import javax.swing.*;
import java.awt.*;
import view.HDController;
import java.util.List;
import dto.HDInputDTO;

public class DialogSearch extends JDialog {
    private JTextField maHDField;
    private JButton searchButton, cancelButton;
    private HDController controller;
    private QuanLyHoaDon parent;

    public DialogSearch(QuanLyHoaDon parent, HDController controller) {
        super(parent, "Tìm Kiếm Hóa Đơn", true);
        this.parent = parent;
        this.controller = controller;
        
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Mã HĐ
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Mã Hóa Đơn:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        maHDField = new JTextField(15);
        add(maHDField, gbc);

        // Nút tìm và hủy
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel();
        searchButton = new JButton("Tìm");
        cancelButton = new JButton("Hủy");
        buttonPanel.add(searchButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);

        // Xử lý sự kiện
        searchButton.addActionListener(e -> searchHoaDon());
        cancelButton.addActionListener(e -> dispose());
    }

    private void searchHoaDon() {
        try {
            String maHDStr = maHDField.getText().trim();
            if (maHDStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int maHD = Integer.parseInt(maHDStr);
            if (maHD <= 0) {
                JOptionPane.showMessageDialog(this, "Mã hóa đơn phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Gọi controller để tìm kiếm
            controller.searchById(maHD);
            
            // Lấy kết quả tìm kiếm từ controller
            List<HDInputDTO> searchResults = controller.getSearchResults();
            if (!searchResults.isEmpty()) {
                // Hiển thị kết quả trong dialog mới
                DialogSearchResult resultDialog = new DialogSearchResult(parent, searchResults.get(0));
                resultDialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn với mã: " + maHD, 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn là số!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
