package view.dialog;

import javax.swing.*;
import database.HDDAOMemory;
import usecase.add.AddHDUseCase;
import usecase.delete.DeleteHDUseCase;
import usecase.edit.EditHDUseCase;
import usecase.search.SearchHDUseCase;
import view.HDController;
import view.HDPresenter;

public class MainQLHD {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Khởi tạo DAO
                HDDAOMemory daoMemory = new HDDAOMemory();
                
                // Khởi tạo View
                QuanLyHoaDon view = new QuanLyHoaDon();
                
                // Khởi tạo Presenter
                HDPresenter presenter = new HDPresenter(view);
                
                // Khởi tạo các UseCase
                AddHDUseCase addUseCase = new AddHDUseCase(daoMemory, presenter);
                DeleteHDUseCase deleteUseCase = new DeleteHDUseCase(daoMemory, presenter);
                EditHDUseCase editUseCase = new EditHDUseCase(daoMemory, presenter);
                
                // Khởi tạo Controller
                HDController controller = new HDController(addUseCase, deleteUseCase, editUseCase, null);
                
                // Khởi tạo SearchUseCase với controller
                SearchHDUseCase searchUseCase = new SearchHDUseCase(daoMemory, presenter);
                
                // Cập nhật controller với searchUseCase
                controller.setSearchUseCase(searchUseCase);
                
                // Gán controller vào view
                view.setController(controller);
                
                // Hiển thị giao diện
                view.setVisible(true);
                
                // Đăng ký shutdown hook để đóng kết nối database
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    daoMemory.closeConnection();
                }));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Lỗi khởi tạo ứng dụng: " + e.getMessage(),
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
