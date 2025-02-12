package usecase.edit;

import database.HDDAOMemory;
import dto.HDInputDTO;
import entity.HoaDon;

public class EditHDUseCase implements EditHDInputBoundary {
    private final EditHDDatabaseBoundary database;
    private final EditHDOutputBoundary output;

    public EditHDUseCase(HDDAOMemory hdDatabase, EditHDOutputBoundary output) {
        this.database = (EditHDDatabaseBoundary) hdDatabase;
        this.output = output;
    }

    @Override
    public void execute(HDInputDTO inputDTO) {
        HoaDon hoaDon = database.findHoaDonById(inputDTO.getmaHD());
        if (hoaDon != null) {
            // Update fields of `hoaDon` based on `inputDTO`
            hoaDon.sethoTen(inputDTO.gethoTen());
            hoaDon.setngayHD(inputDTO.getngayHD());
            hoaDon.setdonGia(inputDTO.getdonGia());
            hoaDon.setkHD(inputDTO.getkHD());
            
            // Attempt to update the invoice in the database
            boolean success = database.updateHoaDon(inputDTO.getmaHD(), hoaDon);
            output.presentEditResult(success, success ? "Invoice updated successfully" : "Failed to update invoice", inputDTO);
        } else {
            output.presentEditResult(false, "Invoice not found", inputDTO);
        }
    }

    @Override
    public void Editusecase(HDInputDTO inputDTO) {
        
    }
}
