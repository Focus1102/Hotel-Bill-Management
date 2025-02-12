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
            // Khởi tạo DAO và Presenter
            HDDAOMemory daoMemory = new HDDAOMemory();
            QuanLyHoaDon view = new QuanLyHoaDon(); // Giao diện chính

            // Khởi tạo Presenter với view
            HDPresenter presenter = new HDPresenter(view);

            // Khởi tạo các UseCase
            AddHDUseCase addUseCase = new AddHDUseCase(daoMemory, presenter);
            DeleteHDUseCase deleteUseCase = new DeleteHDUseCase(daoMemory, presenter);
            EditHDUseCase editUseCase = new EditHDUseCase(daoMemory, presenter);
            SearchHDUseCase searchUseCase = new SearchHDUseCase(daoMemory, presenter);

            // Khởi tạo Controller với các UseCase
            HDController controller = new HDController(addUseCase, deleteUseCase, editUseCase, searchUseCase);

            // Gán controller vào view
            view.setController(controller);

            // Hiển thị giao diện
            view.setVisible(true);
        });
    }
}
