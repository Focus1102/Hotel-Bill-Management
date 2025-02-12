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
    private Map<Integer, HoaDon> mockDatabase = new HashMap<>();
    private int currentHDID = 0;
    private Connection connection;
    

public void HoaDonDAOMemory() {
        // Kết nối đến cơ sở dữ liệu
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlykhachsan", "root", "123456789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addHoadon(HoaDon hoaDon) {
        mockDatabase.put(++currentHDID, hoaDon);
        return currentHDID;
    }

    @Override
    public HoaDon findHoaDonById(int id) {
    HoaDon hoaDon = mockDatabase.get(id);
    if (hoaDon == null) {
        
    }
    return hoaDon;
}


    @Override
    public boolean deleteHoadon(int maHD) {
        if (mockDatabase.containsKey(maHD)) {
            mockDatabase.remove(maHD);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateHoaDon(int maHD, HoaDon updatedHoaDon) {
        if (mockDatabase.containsKey(maHD)) {
            mockDatabase.put(maHD, updatedHoaDon);
            return true;
        }
        return false;
    }

    @Override
    public List<HoaDon> getAllInvoices() {
        return new ArrayList<>(mockDatabase.values());
    }

    

}
