package usecase.add;

import entity.HoaDon;


public interface AddHDDatabaseBoundary {
    int addHoadon(HoaDon hoaDon);
    HoaDon findHoaDonById(int id);
    
}
