package entity;

import java.util.Date;

public abstract class HoaDon {
    protected int maHD;
    protected Date ngayHD;
    protected String hoTen;
    protected String kHD; 
    protected double donGia;

    public HoaDon(int maHD, Date ngayHD, String hoTen, String kHD, double donGia) {
        if (maHD <= 0) {
            throw new IllegalArgumentException("Mã hóa đơn phải lớn hơn 0");
        }
        if (ngayHD == null) {
            throw new IllegalArgumentException("Ngày hóa đơn không được null");
        }
        if (hoTen == null || hoTen.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        if (kHD == null || (!kHD.equals("SG") && !kHD.equals("NG"))) {
            throw new IllegalArgumentException("Loại hóa đơn không hợp lệ");
        }
        if (donGia <= 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }

        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.hoTen = hoTen.trim();
        this.kHD = kHD;
        this.donGia = donGia;
    }

    public abstract int tinhThanhTien();
    
    public int getMaHD() {
        return maHD;
    }

    public Date getNgayHD() {
        return ngayHD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getKHD() {
        return kHD;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setMaHD(int maHD) {
        if (maHD <= 0) {
            throw new IllegalArgumentException("Mã hóa đơn phải lớn hơn 0");
        }
        this.maHD = maHD;
    }

    public void setNgayHD(Date ngayHD) {
        if (ngayHD == null) {
            throw new IllegalArgumentException("Ngày hóa đơn không được null");
        }
        this.ngayHD = ngayHD;
    }

    public void setHoTen(String hoTen) {
        if (hoTen == null || hoTen.trim().isEmpty()) {
            throw new IllegalArgumentException("Họ tên không được để trống");
        }
        this.hoTen = hoTen.trim();
    }

    public void setKHD(String kHD) {
        if (kHD == null || (!kHD.equals("SG") && !kHD.equals("NG"))) {
            throw new IllegalArgumentException("Loại hóa đơn không hợp lệ");
        }
        this.kHD = kHD;
    }

    public void setDonGia(double donGia) {
        if (donGia <= 0) {
            throw new IllegalArgumentException("Đơn giá phải lớn hơn 0");
        }
        this.donGia = donGia;
    }
}
