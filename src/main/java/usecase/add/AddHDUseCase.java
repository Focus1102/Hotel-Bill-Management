package usecase.add;

import entity.HoaDon;
import dto.HDInputDTO;
import dto.HDOutputDTO;
import entity.HDTheoGio;
import entity.HDTheoNgay;

public class AddHDUseCase implements AddHDInputBoundary {
    private final AddHDDatabaseBoundary addHDDBB;
    private final AddHDOutputBoundary addHDOB;

    public AddHDUseCase(AddHDDatabaseBoundary addHDDBB, AddHDOutputBoundary addHDOB) {
        this.addHDDBB = addHDDBB;
        this.addHDOB = addHDOB;
    }

    @Override
    public HoaDon execute(HDInputDTO hdInputDTO) {
        if (hdInputDTO.getmaHD() <= 0 || hdInputDTO.getdonGia() < 0 || hdInputDTO.gethoTen().isEmpty()) {
            throw new IllegalArgumentException("Dữ liệu không hợp lệ.");
        }
    
        HoaDon hoaDon;
        if ("SG".equals(hdInputDTO.getkHD())) {
            hoaDon = new HDTheoGio(
                hdInputDTO.getmaHD(),
                hdInputDTO.getngayHD(),
                hdInputDTO.gethoTen(),
                hdInputDTO.getdonGia(),
                hdInputDTO.getsoGioThue()
            );
        } else {
            hoaDon = new HDTheoNgay(
                hdInputDTO.getmaHD(),
                hdInputDTO.getngayHD(),
                hdInputDTO.gethoTen(),
                hdInputDTO.getdonGia(),
                hdInputDTO.getsoNgayThue()
            );
        }
    
        double thanhTien = hoaDon.tinhThanhTien();
        addHDDBB.addHoadon(hoaDon);
    
        HDOutputDTO outputDTO = new HDOutputDTO(
            hoaDon.getmaHD(),
            hoaDon.getngayHD(),
            hoaDon.gethoTen(),
            hoaDon.getkHD(),
            hoaDon.donGia(),
            thanhTien
        );
    
        addHDOB.present(outputDTO);
        addHDOB.onSuccess(hdInputDTO);
    
        return hoaDon;
    }
    
}
