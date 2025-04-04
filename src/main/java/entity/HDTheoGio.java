package entity;

import java.util.Date;

public class HDTheoGio extends HoaDon {
    private int soGioThue;
    private static final int MAX_GIO_THUE = 30;
    private static final int GIO_TINH_TIEN = 24;

    public HDTheoGio(int maHD, Date ngayHD, String hoTen, double donGia, int soGioThue) {
        super(maHD, ngayHD, hoTen, "SG", donGia);
        setSoGioThue(soGioThue);
    }

    @Override
    public int tinhThanhTien() {
        if (soGioThue > MAX_GIO_THUE) {
            throw new IllegalStateException("Số giờ thuê không được vượt quá " + MAX_GIO_THUE + " giờ");
        }
        return (int) (soGioThue * getDonGia());
    }

    public int getSoGioThue() {
        return soGioThue;
    }

    public void setSoGioThue(int soGioThue) {
        if (soGioThue <= 0) {
            throw new IllegalArgumentException("Số giờ thuê phải lớn hơn 0");
        }
        if (soGioThue > MAX_GIO_THUE) {
            throw new IllegalArgumentException("Số giờ thuê không được vượt quá " + MAX_GIO_THUE + " giờ");
        }
        this.soGioThue = soGioThue;
    }
}
