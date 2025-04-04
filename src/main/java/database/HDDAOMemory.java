package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.HoaDon;
import usecase.delete.DeleteDDatabaseBoundary;
import usecase.edit.EditHDDatabaseBoundary;
import usecase.search.SearchHDDatabaseBoundary;
import usecase.add.AddHDDatabaseBoundary;

public class HDDAOMemory implements AddHDDatabaseBoundary, DeleteDDatabaseBoundary, EditHDDatabaseBoundary, SearchHDDatabaseBoundary {
    private final Map<Integer, HoaDon> mockDatabase;
    private int currentHDID;
    private final Connection connection;

    public HDDAOMemory() {
        this.mockDatabase = new HashMap<>();
        this.currentHDID = 0;
        try {
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/quanlykhachsan",
                "root",
                "123456789"
            );
        } catch (SQLException e) {
            throw new RuntimeException("Không thể kết nối đến cơ sở dữ liệu", e);
        }
    }

    @Override
    public int addHoadon(HoaDon hoaDon) {
        if (hoaDon == null) {
            throw new IllegalArgumentException("Hóa đơn không được null");
        }
        if (mockDatabase.containsKey(hoaDon.getMaHD())) {
            throw new IllegalArgumentException("Hóa đơn với mã " + hoaDon.getMaHD() + " đã tồn tại");
        }
        mockDatabase.put(hoaDon.getMaHD(), hoaDon);
        return hoaDon.getMaHD();
    }

    @Override
    public HoaDon findHoaDonById(int id) {
        HoaDon hoaDon = mockDatabase.get(id);
        if (hoaDon == null) {
            throw new IllegalArgumentException("Không tìm thấy hóa đơn với mã: " + id);
        }
        return hoaDon;
    }

    @Override
    public boolean deleteHoadon(int maHD) {
        if (!mockDatabase.containsKey(maHD)) {
            throw new IllegalArgumentException("Không tìm thấy hóa đơn với mã: " + maHD);
        }
        mockDatabase.remove(maHD);
        return true;
    }

    @Override
    public boolean updateHoaDon(int maHD, HoaDon updatedHoaDon) {
        if (updatedHoaDon == null) {
            throw new IllegalArgumentException("Hóa đơn cập nhật không được null");
        }
        if (!mockDatabase.containsKey(maHD)) {
            throw new IllegalArgumentException("Không tìm thấy hóa đơn với mã: " + maHD);
        }
        if (maHD != updatedHoaDon.getMaHD()) {
            throw new IllegalArgumentException("Mã hóa đơn không khớp");
        }
        mockDatabase.put(maHD, updatedHoaDon);
        return true;
    }

    @Override
    public List<HoaDon> getAllInvoices() {
        return new ArrayList<>(mockDatabase.values());
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Không thể đóng kết nối đến cơ sở dữ liệu", e);
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }
}
