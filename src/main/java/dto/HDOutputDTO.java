package dto;

import java.util.Date;

public class HDOutputDTO {

    protected int maHD;
    protected Date ngayHD;
    protected String hoTen;
    protected String kHD;
    protected double donGia;

    private double thanhTien;

    private int soGioThue;
    private int soNgayThue;

    public HDOutputDTO(int maHD, Date ngayHD, String hoTen, String kHD, double donGia, double thanhTien){
        this.maHD = maHD;
        this.ngayHD = ngayHD;
        this.hoTen = hoTen;
        this.kHD = kHD;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public HDOutputDTO(int maHD, Date ngayHD, String hoTen,String kHD, double donGia,double thanhTien, int soGioThue, int soNgayThue){
        this(maHD,ngayHD,hoTen,kHD,donGia, thanhTien);
        this.soGioThue = soGioThue;
        this.soNgayThue = soNgayThue;
    }

  

    public int getmaHD(){
        return maHD;
    }
    public void setmaHD(int maHD){
        this.maHD = maHD;
    }

    public Date getngayHD(){
        return ngayHD;
    }
    public void setngayHD(Date ngayHD){
        this.ngayHD = ngayHD;
    }

    public String gethoTen(){
        return hoTen;
    }
    public void sethoTen(String hoTen){
        this.hoTen = hoTen;
    }
    
    public String getkHD(){
        return kHD;
    }
    public void setkHD(String kHD){
        this.kHD = kHD;
    }


    public double getdonGia(){
        return donGia;
    }
    public void setdonGia(double donGia){
        this.donGia = donGia;
    }

    public double getthanhTien(){
        return thanhTien;
    }
    public void setthanhTien(double thanhTien){
        this.thanhTien = thanhTien;
    }
    public int getsoGioThue(){
        return soGioThue;
    }
    public void setsoGioThue(int soGioThue){
        this.soGioThue = soGioThue;
    }

    public int getsoNgayThue(){
        return soNgayThue;
    }

    public void setsoNgayThue(int soNgayThue){
        this.soNgayThue = soNgayThue;
    }
    
}