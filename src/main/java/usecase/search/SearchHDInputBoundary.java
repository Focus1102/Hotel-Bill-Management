package usecase.search;

import entity.HoaDon;
import java.util.List;

public interface SearchHDInputBoundary {
    void searchById(int maHD);
    List<HoaDon> getAllInvoices();
}
