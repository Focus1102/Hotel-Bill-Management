package usecase.edit;

import database.HDDAOMemory;
import dto.HDInputDTO;
import entity.HoaDon;
import java.util.Date;

public class EditHDUseCase implements EditHDInputBoundary {
    private final EditHDDatabaseBoundary database;
    private final EditHDOutputBoundary output;

    public EditHDUseCase(HDDAOMemory hdDatabase, EditHDOutputBoundary output) {
        this.database = (EditHDDatabaseBoundary) hdDatabase;
        this.output = output;
    }

    @Override
    public void execute(HDInputDTO inputDTO) {
        // Kiểm tra ngày tháng với thời gian hiện tại
        Date currentDate = new Date();
        if (inputDTO.getNgayHD().after(currentDate)) {
            output.presentEditResult(false, "Ngày hóa đơn không được lớn hơn ngày hiện tại!", inputDTO);
            return;
        }

        HoaDon hoaDon = database.findHoaDonById(inputDTO.getMaHD());
        if (hoaDon != null) {
            // Update fields of `hoaDon` based on `inputDTO`
            hoaDon.setHoTen(inputDTO.getHoTen());
            hoaDon.setNgayHD(inputDTO.getNgayHD());
            hoaDon.setDonGia(inputDTO.getDonGia());
            hoaDon.setKHD(inputDTO.getKHD());
            
            // Attempt to update the invoice in the database
            boolean success = database.updateHoaDon(inputDTO.getMaHD(), hoaDon);
            output.presentEditResult(success, success ? "Invoice updated successfully" : "Failed to update invoice", inputDTO);
        } else {
            output.presentEditResult(false, "Invoice not found", inputDTO);
        }
    }

    @Override
    public void Editusecase(HDInputDTO inputDTO) {
        execute(inputDTO);
    }
}
