package view;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import usecase.add.AddHDUseCase;
import usecase.delete.DeleteHDUseCase;
import usecase.edit.EditHDUseCase;
import usecase.search.SearchHDUseCase;
import dto.HDInputDTO;
import entity.HoaDon;
import entity.HDTheoGio;
import entity.HDTheoNgay;

public class HDController {
    private final AddHDUseCase addHDUseCase;
    private final DeleteHDUseCase deleteHDUseCase;
    private final EditHDUseCase editHDUseCase;  
    private SearchHDUseCase searchHDUseCase;
    private List<HDInputDTO> searchResults;

    public HDController(AddHDUseCase addHDUseCase, DeleteHDUseCase deleteHDUseCase, EditHDUseCase editHDUseCase, SearchHDUseCase searchHDUseCase) {
        this.addHDUseCase = addHDUseCase;
        this.deleteHDUseCase = deleteHDUseCase;
        this.editHDUseCase = editHDUseCase;
        this.searchHDUseCase = searchHDUseCase;
        this.searchResults = new ArrayList<>();
    }

    public void setSearchUseCase(SearchHDUseCase searchHDUseCase) {
        this.searchHDUseCase = searchHDUseCase;
    }

    public void addHoaDon(String maHDStr, String hoTen, Date ngayThue, boolean isHourBased, String durationStr, String donGiaStr) {
        try {
            // Kiểm tra ngày tháng với thời gian hiện tại
            Date currentDate = new Date();
            if (ngayThue.after(currentDate)) {
                throw new IllegalArgumentException("Ngày hóa đơn không được lớn hơn ngày hiện tại!");
            }

            int maHD = Integer.parseInt(maHDStr);
            if (maHD <= 0) {
                throw new IllegalArgumentException("Mã hóa đơn phải lớn hơn 0!");
            }

            int duration = Integer.parseInt(durationStr);
            if (duration <= 0) {
                throw new IllegalArgumentException("Số giờ/ngày phải lớn hơn 0!");
            }

            double donGia = Double.parseDouble(donGiaStr);
            if (donGia <= 0) {
                throw new IllegalArgumentException("Đơn giá phải lớn hơn 0!");
            }
    
            HDInputDTO inputDTO = new HDInputDTO(
                maHD,
                ngayThue,
                hoTen,
                isHourBased ? "SG" : "NG",
                donGia,
                isHourBased ? duration : 0,
                isHourBased ? 0 : duration,
                0
            );
    
            // Gọi UseCase để xử lý logic
            addHDUseCase.execute(inputDTO);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Mã HD vui lòng nhập đúng định dạng số nguyên!");
        }
    }
    
    public void deleteHoaDon(int maHD) {
        // Gọi UseCase để xóa hóa đơn
        deleteHDUseCase.execute(maHD);
    }
    

    public void editHoaDon(String maHDStr, String hoTen, Date ngayThue, boolean isHourBased, String durationStr, String donGiaStr) {
        try {
            // Kiểm tra ngày tháng với thời gian hiện tại
            Date currentDate = new Date();
            if (ngayThue.after(currentDate)) {
                throw new IllegalArgumentException("Ngày hóa đơn không được lớn hơn ngày hiện tại!");
            }

            int maHD = Integer.parseInt(maHDStr);
            if (maHD <= 0) {
                throw new IllegalArgumentException("Mã hóa đơn phải lớn hơn 0!");
            }

            int duration = Integer.parseInt(durationStr);
            if (duration <= 0) {
                throw new IllegalArgumentException("Số giờ/ngày phải lớn hơn 0!");
            }

            double donGia = Double.parseDouble(donGiaStr);
            if (donGia <= 0) {
                throw new IllegalArgumentException("Đơn giá phải lớn hơn 0!");
            }
        
            // Tạo đối tượng HoaDon tạm thời để tính thành tiền
            HoaDon tempHoaDon;
            if (isHourBased) {
                tempHoaDon = new HDTheoGio(maHD, ngayThue, hoTen, donGia, duration);
            } else {
                tempHoaDon = new HDTheoNgay(maHD, ngayThue, hoTen, donGia, duration);
            }
            int thanhTien = tempHoaDon.tinhThanhTien();
        
            HDInputDTO inputDTO = new HDInputDTO(
                maHD, 
                ngayThue, 
                hoTen, 
                isHourBased ? "SG" : "NG", 
                donGia, 
                isHourBased ? duration : 0, 
                isHourBased ? 0 : duration, 
                thanhTien
            );
            editHDUseCase.execute(inputDTO);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Vui lòng nhập đúng định dạng số!");
        }
    }

    public void searchById(int maHD) {
        searchHDUseCase.searchById(maHD);
        // Cập nhật kết quả tìm kiếm từ use case
        List<HoaDon> hoaDons = searchHDUseCase.getAllInvoices();
        searchResults.clear();
        for (HoaDon hd : hoaDons) {
            if (hd.getMaHD() == maHD) {
                HDInputDTO dto = new HDInputDTO(
                    hd.getMaHD(),
                    hd.getNgayHD(),
                    hd.getHoTen(),
                    hd.getKHD(),
                    hd.getDonGia(),
                    hd instanceof HDTheoGio ? ((HDTheoGio)hd).getSoGioThue() : 0,
                    hd instanceof HDTheoNgay ? ((HDTheoNgay)hd).getSoNgayThue() : 0,
                    hd.tinhThanhTien()
                );
                searchResults.add(dto);
                break;
            }
        }
    }
    

    public HDInputDTO[] getAllHD() {
        List<HoaDon> hoaDons = searchHDUseCase.getAllInvoices();
        HDInputDTO[] result = new HDInputDTO[hoaDons.size()];
        for (int i = 0; i < hoaDons.size(); i++) {
            HoaDon hd = hoaDons.get(i);
            result[i] = new HDInputDTO(
                hd.getMaHD(),
                hd.getNgayHD(),
                hd.getHoTen(),
                hd.getKHD(),
                hd.getDonGia(),
                hd instanceof HDTheoGio ? ((HDTheoGio)hd).getSoGioThue() : 0,
                hd instanceof HDTheoNgay ? ((HDTheoNgay)hd).getSoNgayThue() : 0,
                0
            );
        }
        return result;
    }

    public List<HDInputDTO> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<HDInputDTO> results) {
        this.searchResults = results;
    }
}
