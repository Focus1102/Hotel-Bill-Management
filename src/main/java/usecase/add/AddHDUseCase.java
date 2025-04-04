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
        if (hdInputDTO.getMaHD() <= 0 || hdInputDTO.getDonGia() < 0 || hdInputDTO.getHoTen().isEmpty()) {
            throw new IllegalArgumentException("Dữ liệu không hợp lệ.");
        }
    
        HoaDon hoaDon;
        if ("SG".equals(hdInputDTO.getKHD())) {
            hoaDon = new HDTheoGio(
                hdInputDTO.getMaHD(),
                hdInputDTO.getNgayHD(),
                hdInputDTO.getHoTen(),
                hdInputDTO.getDonGia(),
                hdInputDTO.getSoGioThue()
            );
        } else {
            hoaDon = new HDTheoNgay(
                hdInputDTO.getMaHD(),
                hdInputDTO.getNgayHD(),
                hdInputDTO.getHoTen(),
                hdInputDTO.getDonGia(),
                hdInputDTO.getSoNgayThue()
            );
        }
    
        double thanhTien = hoaDon.tinhThanhTien();
        addHDDBB.addHoadon(hoaDon);
    
        HDOutputDTO outputDTO = new HDOutputDTO(
            hoaDon.getMaHD(),
            hoaDon.getNgayHD(),
            hoaDon.getHoTen(),
            hoaDon.getKHD(),
            hoaDon.getDonGia(),
            hoaDon instanceof HDTheoGio ? ((HDTheoGio)hoaDon).getSoGioThue() : 0,
            hoaDon instanceof HDTheoNgay ? ((HDTheoNgay)hoaDon).getSoNgayThue() : 0,
            (int)thanhTien
        );
    
        addHDOB.present(outputDTO);
        addHDOB.onSuccess(hdInputDTO);
    
        return hoaDon;
    }
    
}
