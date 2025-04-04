package view.dialog;

import entity.HoaDon;
import usecase.add.AddHDUseCase;
import usecase.delete.DeleteHDUseCase;
import usecase.search.SearchHDUseCase;
import view.HDController;
import view.HDPresenter;
import entity.HDTheoNgay;
import entity.HDTheoGio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import database.HDDAOMemory;
import dto.HDInputDTO;
import dto.HDOutputDTO;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuanLyHoaDon extends JFrame {
    private JTable hoaDonTable;
    private DefaultTableModel tableModel;
    private JPanel tablePanel;
    private JButton addButton, deleteButton, editButton, searchButton;
    private HDController controller;
    ArrayList<HoaDon> hoaDons;

    public QuanLyHoaDon() {
        setTitle("Quản Lý Hóa Đơn");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Khởi tạo danh sách hóa đơn
        hoaDons = new ArrayList<>();

        // Bảng hiển thị hóa đơn
        String[] columnNames = {"Mã HĐ", "Họ Tên", "Ngày HĐ", "Loại HĐ", "Đơn Giá", "Số Giờ/Ngày", "Thành Tiền"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        hoaDonTable = new JTable(tableModel);
        hoaDonTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(hoaDonTable), BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        // Các nút chức năng
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Thêm");
        deleteButton = new JButton("Xóa");
        editButton = new JButton("Sửa");
        searchButton = new JButton("Tìm");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(editButton);
        buttonPanel.add(searchButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void setController(HDController controller) {
        this.controller = controller;
        // Sử dụng controller trong các nút
        addButton.addActionListener(e -> {
            DialogAdd dialog = new DialogAdd(this, controller);
            dialog.setVisible(true);
        });
        
        deleteButton.addActionListener(e -> {
            int selectedRow = hoaDonTable.getSelectedRow();
            if (selectedRow >= 0) {
                int maHD = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                DialogDelete dialog = new DialogDelete(this, controller, maHD);
                dialog.setVisible(true);
            } else {
                showMessage("Vui lòng chọn hóa đơn cần xóa!");
            }
        });
        
        editButton.addActionListener(e -> {
            int selectedRow = hoaDonTable.getSelectedRow();
            if (selectedRow >= 0) {
                int maHD = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                String hoTen = tableModel.getValueAt(selectedRow, 1).toString();
                Date ngayHD = (Date) tableModel.getValueAt(selectedRow, 2);
                String loaiHD = tableModel.getValueAt(selectedRow, 3).toString();
                double donGia = Double.parseDouble(tableModel.getValueAt(selectedRow, 4).toString());
                int soThue = Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString());
                
                DialogEdit dialog = new DialogEdit(this, controller, maHD, hoTen, ngayHD, loaiHD, donGia, soThue);
                dialog.setVisible(true);
            } else {
                showMessage("Vui lòng chọn hóa đơn cần sửa!");
            }
        });
        
        searchButton.addActionListener(e -> {
            DialogSearch dialog = new DialogSearch(this, controller);
            dialog.setVisible(true);
        });
    }

    // Phương thức thêm hóa đơn vào bảng
    public void addInvoiceToTable(HDOutputDTO outputDTO) {
        if (outputDTO != null) {
            tableModel.addRow(new Object[]{
                outputDTO.getMaHD(),
                outputDTO.getHoTen(),
                outputDTO.getNgayHD(),
                outputDTO.getKHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày",
                outputDTO.getDonGia(),
                outputDTO.getKHD().equals("SG") ? outputDTO.getSoGioThue() : outputDTO.getSoNgayThue(),
                outputDTO.getThanhTien()
            });
        }
    }

    // Phương thức xóa hóa đơn khỏi bảng
    public void removeInvoiceFromTable(int maHD) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (Integer.parseInt(tableModel.getValueAt(row, 0).toString()) == maHD) {
                tableModel.removeRow(row);
                break;
            }
        }
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void updateInvoiceInTable(HDInputDTO dto) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (Integer.parseInt(tableModel.getValueAt(i, 0).toString()) == dto.getMaHD()) {
                String loaiHD = dto.getKHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày";
                String soThue = dto.getKHD().equals("SG") ? 
                    String.format("%d giờ", dto.getSoGioThue()) : 
                    String.format("%d ngày", dto.getSoNgayThue());
                
                tableModel.setValueAt(dto.getHoTen(), i, 1);
                tableModel.setValueAt(dto.getNgayHD(), i, 2);
                tableModel.setValueAt(loaiHD, i, 3);
                tableModel.setValueAt(String.format("%,.0fđ", dto.getDonGia()), i, 4);
                tableModel.setValueAt(soThue, i, 5);
                tableModel.setValueAt(String.format("%,.0fđ", (double)dto.getThanhTien()), i, 6);
                break;
            }
        }
    }

    public void updateSearchResults(List<HDInputDTO> searchResults) {
        tableModel.setRowCount(0);
        for (HDInputDTO dto : searchResults) {
            String loaiHD = dto.getKHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày";
            String soThue = dto.getKHD().equals("SG") ? 
                String.format("%d giờ", dto.getSoGioThue()) : 
                String.format("%d ngày", dto.getSoNgayThue());
            
            tableModel.addRow(new Object[]{
                dto.getMaHD(),
                dto.getHoTen(),
                dto.getNgayHD(),
                loaiHD,
                dto.getDonGia(),
                soThue,
                dto.getThanhTien()
            });
        }
    }

    public HDController getController() {
        return controller;
    }
}
