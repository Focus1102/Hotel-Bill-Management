package entity;

import java.util.Date;

public class HDTheoNgay extends HoaDon {
    private int soNgayThue;
    private static final int NGAY_KHONG_GIAM = 7;
    private static final double TY_LE_GIAM = 0.8;

    public HDTheoNgay(int maHD, Date ngayHD, String hoTen, double donGia, int soNgayThue) {
        super(maHD, ngayHD, hoTen, "NG", donGia);
        setSoNgayThue(soNgayThue);
    }

    @Override
    public int tinhThanhTien() {
        if (soNgayThue <= NGAY_KHONG_GIAM) {
            return (int) (soNgayThue * getDonGia());
        } else {
            int ngayKhongGiam = NGAY_KHONG_GIAM;
            int ngayGiam = soNgayThue - ngayKhongGiam;
            return (int) ((ngayKhongGiam * getDonGia()) + (ngayGiam * getDonGia() * TY_LE_GIAM));
        }
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public void setSoNgayThue(int soNgayThue) {
        if (soNgayThue <= 0) {
            throw new IllegalArgumentException("Số ngày thuê phải lớn hơn 0");
        }
        this.soNgayThue = soNgayThue;
    }
}
