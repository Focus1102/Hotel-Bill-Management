package usecase.add;

import entity.HoaDon;
import dto.HDInputDTO;

public interface AddHDInputBoundary {
    HoaDon execute(HDInputDTO hdInputDTO);

 
}
