package usecase.search;

import dto.HDInputDTO;
import java.util.List;

public interface SearchHDOutputBoundary {
    void tim(List<HDInputDTO> searchResults);
}