package usecase.search;

import dto.HDInputDTO;
import entity.HDTheoGio;
import entity.HDTheoNgay;
import entity.HoaDon;

import java.util.ArrayList;
import java.util.List;


public class SearchHDUseCase implements SearchHDInputBoundary {
    private final SearchHDDatabaseBoundary searchHDDBB;
    private final SearchHDOutputBoundary presenter;

    public SearchHDUseCase(SearchHDDatabaseBoundary searchHDDBB, SearchHDOutputBoundary presenter) {
        this.searchHDDBB = searchHDDBB;
        this.presenter = presenter;
    }

    @Override
    public void searchById(int maHD) {
    HoaDon hoaDon = searchHDDBB.findHoaDonById(maHD);
    List<HDInputDTO> resultList = new ArrayList<>();
    if (hoaDon != null) {
        resultList.add(new HDInputDTO(
            hoaDon.getmaHD(),
            hoaDon.getngayHD(),
            hoaDon.gethoTen(),
            hoaDon instanceof HDTheoGio ? "SG" : "NG",
            hoaDon.donGia(),
            hoaDon instanceof HDTheoGio ? ((HDTheoGio) hoaDon).getSoGioThue() : 0,
            hoaDon instanceof HDTheoNgay ? ((HDTheoNgay) hoaDon).getSoNgayThue() : 0,
            hoaDon.tinhThanhTien()
        ));
    }
    presenter.tim(resultList);
}



    
    
}
