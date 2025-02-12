    package view;

    import java.util.Date;


    import usecase.add.AddHDUseCase;
    import usecase.delete.DeleteHDUseCase;
    import usecase.edit.EditHDUseCase;
    import usecase.search.SearchHDUseCase;
    import dto.HDInputDTO;

    public class HDController {
        private final AddHDUseCase addHDUseCase;
        private final DeleteHDUseCase deleteHDUseCase;
        private final EditHDUseCase editHDUseCase;  
        private final SearchHDUseCase searchHDUseCase;

        public HDController(AddHDUseCase addHDUseCase, DeleteHDUseCase deleteHDUseCase, EditHDUseCase editHDUseCase, SearchHDUseCase searchHDUseCase) {
            this.addHDUseCase = addHDUseCase;
            this.deleteHDUseCase = deleteHDUseCase;
            this.editHDUseCase = editHDUseCase;
            this.searchHDUseCase = searchHDUseCase;
        }

        public void addHoaDon(String maHDStr, String hoTen, Date ngayThue, boolean isHourBased, String durationStr, String donGiaStr) {
            int maHD = Integer.parseInt(maHDStr);
            int duration = Integer.parseInt(durationStr);
            double donGia = Double.parseDouble(donGiaStr);
        
            HDInputDTO inputDTO = new HDInputDTO(
                maHD,
                ngayThue,
                hoTen,
                isHourBased ? "SG" : "NG",
                donGia,
                isHourBased ? duration : 0,
                isHourBased ? 0 : duration,
                0
            );
        
            // Gọi UseCase để xử lý logic
            addHDUseCase.execute(inputDTO);
        }
        
        public void deleteHoaDon(int maHD) {
            // Gọi UseCase để xóa hóa đơn
            deleteHDUseCase.execute(maHD);
        }
        

        public void editHoaDon(String maHDStr, String hoTen, Date ngayThue, boolean isHourBased, String durationStr, String donGiaStr) {
            int maHD = Integer.parseInt(maHDStr);
            int duration = Integer.parseInt(durationStr);
            double donGia = Double.parseDouble(donGiaStr);
        
            HDInputDTO inputDTO = new HDInputDTO(maHD, ngayThue, hoTen, isHourBased ? "SG" : "NG", donGia, isHourBased ? duration : 0, isHourBased ? 0 : duration, duration);
            editHDUseCase.execute(inputDTO);  
        }

        public void searchById(int maHD) {
            searchHDUseCase.searchById(maHD);
        }
        

        public HDInputDTO[] getAllHD() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getAllHD'");
        }
    }
