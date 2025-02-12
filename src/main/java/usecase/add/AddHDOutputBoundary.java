package usecase.add;

import dto.HDInputDTO;
import dto.HDOutputDTO;

public interface AddHDOutputBoundary {
    void present(int newHoaDonId);

    void present(HDOutputDTO addOutputDTO);

    void onSuccess(HDInputDTO hdInputDTO);
}
