package entity;

import java.util.Date;

public class HDTheoGio extends HoaDon {
    private int soGioThue;

    public HDTheoGio(int maHD, Date ngayHD, String hoTen, double donGia, int soGioThue) {
        super(maHD, ngayHD, hoTen, "SG", donGia);
        this.soGioThue = soGioThue; 
    }

    @Override
    public int tinhThanhTien() {
        if (soGioThue > 30) {
            System.out.println("Không áp dụng loại hóa đơn theo giờ.");
            return 0;
        } else {
            int gioTinhTien = Math.min(soGioThue, 24);
            return (int) (gioTinhTien * donGia);
        }
    }

    public int getSoGioThue() {
        return soGioThue;
    }
}
