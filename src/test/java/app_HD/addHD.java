package app_HD;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import database.HDDAOMemory;
import dto.HDInputDTO;
import dto.HDOutputDTO;
import usecase.add.AddHDInputBoundary;
import usecase.add.AddHDUseCase;
import view.HDController;
import view.HDPresenter;
import view.dialog.*;


public class addHD {
    @Test
    public void testAddHD() {
        // Setup in-memory database, view, and controller
        HDDAOMemory addHDDAOMemory = new HDDAOMemory();
        QuanLyHoaDon view = new QuanLyHoaDon();
        
        // Create presenter and use case
        HDPresenter addHDPresenter = new HDPresenter(view);
        AddHDUseCase addHDInputBoundary = new AddHDUseCase(addHDDAOMemory, addHDPresenter);

        // Initialize and connect the controller
        HDController addHDController = new HDController(addHDInputBoundary, null, null, null);
        view.setAddHDController(addHDController);

        // Execute the test
        addHDInputBoundary.execute(getMockHD());
        HDOutputDTO addHDOutputDTO = addHDPresenter.getAddHDOutputDTO();

        assertEquals(getMockHD().gethoTen(), addHDOutputDTO.gethoTen());
    }

    private HDInputDTO getMockHD() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2003);
        calendar1.set(Calendar.MONTH, Calendar.MAY);
        calendar1.set(Calendar.DAY_OF_MONTH, 12);
        Date date = calendar1.getTime();

        return new HDInputDTO(21, date, "teo", "theogio", 20, 200, 0, 0);
    }
}

