package view.dialog;

import java.awt.GridLayout;

import javax.swing.*;

import view.HDController;

public class DialogDelete extends JDialog {
    private JButton deleteButton, cancelButton;
    private JTextField maHDField;
    private QuanLyHoaDon parent;
    private HDController deleteHDUseCase;

    public DialogDelete(QuanLyHoaDon parent,HDController deleteHDUseCase) {
        this.parent = parent;
        this.deleteHDUseCase = deleteHDUseCase;
        setTitle("Xóa Hóa Đơn");
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setModal(true);

        setLayout(new GridLayout(3, 2));

        // Mã HĐ
        add(new JLabel("Mã HĐ:"));
        maHDField = new JTextField();
        add(maHDField);

        // Nút xóa và hủy
        deleteButton = new JButton("Xóa");
        cancelButton = new JButton("Hủy");

        deleteButton.addActionListener(e -> deleteHoaDon());
        cancelButton.addActionListener(e -> setVisible(false));

        add(deleteButton);
        add(cancelButton);
    }

    private void deleteHoaDon() {
        String maHDText = maHDField.getText(); 
        if (maHDText != null && !maHDText.trim().isEmpty()) { 
            try {
                int maHD = Integer.parseInt(maHDText.trim()); 
                deleteHDUseCase.deleteHoaDon(maHD); // Gửi yêu cầu qua Controller
                parent.removeInvoiceFromTable(maHD);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Mã HĐ phải là số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Mã HĐ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
         setVisible(false);
    }
    
    
}


