package usecase.search;

import entity.HoaDon;
import java.util.List;

public interface SearchHDDatabaseBoundary {
    
    List<HoaDon> getAllInvoices();

    HoaDon findHoaDonById(int maHD);
   
}