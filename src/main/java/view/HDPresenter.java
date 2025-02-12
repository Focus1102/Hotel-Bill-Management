package view;

import java.util.List;

import javax.swing.JOptionPane;

import dto.HDInputDTO;
import dto.HDOutputDTO;
import usecase.delete.DeleteHDOutputBoundary;
import usecase.edit.EditHDOutputBoundary;
import usecase.search.SearchHDOutputBoundary;
import view.dialog.QuanLyHoaDon;
import usecase.add.AddHDOutputBoundary;


public class HDPresenter implements AddHDOutputBoundary, DeleteHDOutputBoundary,EditHDOutputBoundary,SearchHDOutputBoundary {
    private int newHDId;
    private HDOutputDTO addHDOutputDTO = null;
    private  QuanLyHoaDon view;

    public HDPresenter(QuanLyHoaDon  view) {
        
        this.view = view;
    }

    @Override
    public void present(int newHDId) {
        this.newHDId = newHDId;
    }

    public void tim(List<HDInputDTO> results) {
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn!");
        } else {
            view.updateSearchResults(results);
        }
    }

    @Override
    public void presentEditResult(boolean success, String message, HDInputDTO inputDTO) {
        
    }

    @Override
    public void onDeleteSuccess(int maHD) {
        // Successfully deleted the invoice, now update the table in the view
        view.removeInvoiceFromTable(maHD);  // Remove the invoice from the table in the view
        view.showMessage("Xóa hóa đơn thành công!");  // Show success message to the user
    }

    @Override
    public void onDeleteFailure(int maHD, String message) {
        // Deletion failed, show an error message
        view.showMessage("Xóa hóa đơn thành công! ");  // Show failure message to the user
    }


    @Override
    public void present(HDOutputDTO addHDOutputDTO) {
        if (addHDOutputDTO != null) {
            // Gọi phương thức của view để thêm vào bảng
            view.addInvoiceToTable(addHDOutputDTO);
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể thêm hóa đơn. Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onSuccess(HDInputDTO hdInputDTO) {
    // Hiển thị thông báo thành công
    JOptionPane.showMessageDialog(view, "Hóa đơn đã được thêm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

}

    public HDOutputDTO getAddHDOutputDTO() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAddHDOutputDTO'");
    }

}