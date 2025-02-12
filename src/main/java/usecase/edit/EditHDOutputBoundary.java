package usecase.edit;

import dto.HDInputDTO;

public interface EditHDOutputBoundary {
    void presentEditResult(boolean success, String message, HDInputDTO inputDTO);
}

