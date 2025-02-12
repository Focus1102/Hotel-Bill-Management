package usecase.delete;

public interface DeleteHDOutputBoundary {
    void onDeleteSuccess(int maHD);
    void onDeleteFailure(int maHD, String message);
}
