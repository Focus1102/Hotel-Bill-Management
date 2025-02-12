package usecase.delete;

import database.HDDAOMemory;

public class DeleteHDUseCase implements DeleteHDInputBoundary {
    private final DeleteDDatabaseBoundary hdDatabase;
    private final DeleteHDOutputBoundary outputBoundary;

    public DeleteHDUseCase(HDDAOMemory hdDatabase, DeleteHDOutputBoundary outputBoundary) {
        this.hdDatabase = hdDatabase;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void execute(int maHD) {
        boolean isDeleted = hdDatabase.deleteHoadon(maHD);
        if (isDeleted) {
            outputBoundary.onDeleteSuccess(maHD); // Thông báo xóa thành công
        } else {
            outputBoundary.onDeleteFailure(maHD, "Hóa đơn không tìm thấy."); // Xóa thất bại
        }
    }
    
    
}
