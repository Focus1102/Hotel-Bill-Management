package usecase.search;

import database.HDDAOMemory;
import dto.HDInputDTO;
import entity.HoaDon;
import entity.HDTheoGio;
import entity.HDTheoNgay;
import java.util.List;

public class SearchHDUseCase implements SearchHDInputBoundary {
    private final SearchHDDatabaseBoundary database;
    private final SearchHDOutputBoundary output;

    public SearchHDUseCase(HDDAOMemory hdDatabase, SearchHDOutputBoundary output) {
        this.database = (SearchHDDatabaseBoundary) hdDatabase;
        this.output = output;
    }

    @Override
    public void searchById(int maHD) {
        try {
            HoaDon hoaDon = database.findHoaDonById(maHD);
            if (hoaDon != null) {
                HDInputDTO dto = new HDInputDTO(
                    hoaDon.getMaHD(),
                    hoaDon.getNgayHD(),
                    hoaDon.getHoTen(),
                    hoaDon.getKHD(),
                    hoaDon.getDonGia(),
                    hoaDon instanceof HDTheoGio ? ((HDTheoGio)hoaDon).getSoGioThue() : 0,
                    hoaDon instanceof HDTheoNgay ? ((HDTheoNgay)hoaDon).getSoNgayThue() : 0,
                    hoaDon.tinhThanhTien()
                );
                output.tim(List.of(dto));
            } else {
                output.tim(List.of());
            }
        } catch (Exception e) {
            output.tim(List.of());
        }
    }

    @Override
    public List<HoaDon> getAllInvoices() {
        return database.getAllInvoices();
    }
}
