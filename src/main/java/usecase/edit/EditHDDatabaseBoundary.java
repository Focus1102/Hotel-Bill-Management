package usecase.edit;

import entity.HoaDon;

public interface EditHDDatabaseBoundary {
    boolean updateHoaDon(int id, HoaDon hoaDon);
    HoaDon findHoaDonById(int id);  // This method might already exist in HDDAOMemory
}
