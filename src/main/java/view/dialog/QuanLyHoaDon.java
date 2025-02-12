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
    ArrayList<HoaDon> hoaDons;

    public QuanLyHoaDon() {
        setTitle("Quản Lý Hóa Đơn");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Khởi tạo danh sách hóa đơn
        hoaDons = new ArrayList<>();

        // Bảng hiển thị hóa đơn
        tableModel = new DefaultTableModel(new String[]{"Mã HĐ", "Họ Tên", "Ngày HĐ", "Loại HĐ", "Thanh Tiền"}, 0);
        hoaDonTable = new JTable(tableModel);
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
            // Sử dụng controller trong các nút
            addButton.addActionListener(e -> new DialogAdd(this, controller).setVisible(true));
            deleteButton.addActionListener(e -> new DialogDelete(this, controller).setVisible(true));
            editButton.addActionListener(e -> new DialogEdit(this).setVisible(true));
            searchButton.addActionListener(e -> new DialogSearch(this, controller).setVisible(true));
        }
    
    // Phương thức thêm hóa đơn vào bảng
    public void addInvoiceToTable(HDOutputDTO outputDTO) {
        if (outputDTO != null) {
            tableModel.addRow(new Object[]{
                outputDTO.getmaHD(),
                outputDTO.gethoTen(),
                outputDTO.getngayHD(),
                outputDTO.getkHD(),
                outputDTO.getdonGia(),
                outputDTO.getthanhTien()
            });
        }
    }

    // Phương thức xóa hóa đơn khỏi bảng
    public void removeInvoiceFromTable(int maHD) {
        // Tìm và xóa dòng tương ứng với mã hóa đơn
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (Integer.parseInt(tableModel.getValueAt(row, 0).toString()) == maHD) {
                tableModel.removeRow(row);
                break;
            }
        }
    }

    
    public void clearTable() {
        tableModel.setRowCount(0); // Xóa tất cả các hàng trong bảng
    }
    
    

    // Phương thức sửa hóa đơn trong bảng
    public void editHD(int rowIndex, int maHD, String hoTen, Date ngayHD, double donGia, String loaiHD, int soThue) {
        HoaDon hoaDon;
        if (loaiHD.equals("SN")) {
            hoaDon = new HDTheoNgay(maHD, ngayHD, hoTen, donGia, soThue);
        } else {
            hoaDon = new HDTheoGio(maHD, ngayHD, hoTen, donGia, soThue);
        }
        hoaDons.set(rowIndex, hoaDon);
        tableModel.setValueAt(maHD, rowIndex, 0);
        tableModel.setValueAt(hoTen, rowIndex, 1);
        tableModel.setValueAt(ngayHD, rowIndex, 2);
        tableModel.setValueAt(loaiHD, rowIndex, 3);
        tableModel.setValueAt(hoaDon.tinhThanhTien(), rowIndex, 4);
    }

    

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void updateInvoiceInTable(HDInputDTO dto) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 0).equals(dto.getmaHD())) {
                tableModel.setValueAt(dto.gethoTen(), i, 1);
                tableModel.setValueAt(dto.getngayHD(), i, 2);
                tableModel.setValueAt(dto.getkHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày", i, 3);
                tableModel.setValueAt(dto.getkHD().equals("SG") ? dto.getsoGioThue() : dto.getsoNgayThue(), i, 4);
                tableModel.setValueAt(dto.getdonGia(), i, 5);
                tableModel.setValueAt(dto.getthanhTien(), i, 6); // Update total amount here
                break;
            }
        }
    }

    public void updateSearchResults(List<HDInputDTO> searchResults) {
        tableModel.setRowCount(0); // Xóa bảng hiện tại
        for (HDInputDTO dto : searchResults) {
            tableModel.addRow(new Object[]{
                dto.getmaHD(),
                dto.gethoTen(),
                dto.getngayHD(),
                dto.getkHD().equals("SG") ? "Hóa Đơn Theo Giờ" : "Hóa Đơn Theo Ngày",
                dto.getdonGia(),
                dto.getthanhTien()
            });
        }
    }
	public void setAddHDController(HDController addHDController) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setAddHDController'");
	}
   
    
}
