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
        if (success) {
            view.updateInvoiceInTable(inputDTO);
            JOptionPane.showMessageDialog(view, "Cập nhật hóa đơn thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Không thể cập nhật hóa đơn: " + message, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onDeleteSuccess(int maHD) {
        view.removeInvoiceFromTable(maHD);
        view.showMessage("Xóa hóa đơn thành công!");
    }

    @Override
    public void onDeleteFailure(int maHD, String message) {
        view.showMessage("Không thể xóa hóa đơn: " + message);
    }


    @Override
    public void present(HDOutputDTO addHDOutputDTO) {
        if (addHDOutputDTO != null) {
            view.addInvoiceToTable(addHDOutputDTO);
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể thêm hóa đơn. Dữ liệu không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void onSuccess(HDInputDTO hdInputDTO) {
        JOptionPane.showMessageDialog(view, "Hóa đơn đã được thêm thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    public HDOutputDTO getAddHDOutputDTO() {
        return addHDOutputDTO;
    }

}