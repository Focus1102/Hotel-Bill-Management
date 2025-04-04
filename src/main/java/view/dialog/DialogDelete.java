package view.dialog;

import javax.swing.*;
import java.awt.*;
import view.HDController;

public class DialogDelete extends JDialog {
    private JButton confirmButton;
    private JButton cancelButton;
    private HDController controller;
    private int maHD;

    public DialogDelete(QuanLyHoaDon parent, HDController controller, int maHD) {
        super(parent, "Xác nhận xóa", true);
        this.controller = controller;
        this.maHD = maHD;
        
        setLayout(new BorderLayout());
        setSize(300, 150);
        setLocationRelativeTo(parent);

        // Panel chứa thông báo
        JPanel messagePanel = new JPanel();
        messagePanel.add(new JLabel("Bạn có chắc chắn muốn xóa hóa đơn này?"));
        add(messagePanel, BorderLayout.CENTER);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        confirmButton = new JButton("Xác nhận");
        cancelButton = new JButton("Hủy");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện
        confirmButton.addActionListener(e -> {
            controller.deleteHoaDon(maHD);
            dispose();
        });

        cancelButton.addActionListener(e -> dispose());
    }
}


