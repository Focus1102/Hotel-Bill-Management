package dto;

import java.util.Date;

public class HDInputDTO {
    private final int maHD;
    private final Date ngayHD;
    private final String hoTen;
    private final String kHD;
    private final double donGia;
    private final int soGioThue;
    private final int soNgayThue;
    private final int thanhTien;

    public HDInputDTO(int maHD, Date ngayHD, String hoTen, String kHD, double donGia, int soGioThue, int soNgayThue, int thanhTien) {
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
        if (kHD.equals("SG") && soGioThue <= 0) {
            throw new IllegalArgumentException("Số giờ thuê phải lớn hơn 0");
        }
        if (kHD.equals("NG") && soNgayThue <= 0) {
            throw new IllegalArgumentException("Số ngày thuê phải lớn hơn 0");
        }

        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.hoTen = hoTen.trim();
        this.kHD = kHD;
        this.donGia = donGia;
        this.soGioThue = soGioThue;
        this.soNgayThue = soNgayThue;
        this.thanhTien = thanhTien;
    }

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

    public int getSoGioThue() {
        return soGioThue;
    }

    public int getSoNgayThue() {
        return soNgayThue;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    @Override
    public String toString() {
        return String.format("Hóa đơn #%d - %s - %s - %.2fđ - %s", 
            maHD, hoTen, ngayHD, donGia, kHD.equals("SG") ? soGioThue + " giờ" : soNgayThue + " ngày");
    }
}
