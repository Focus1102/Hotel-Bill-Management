DROP SCHEMA IF EXISTS quanlykhachsan;
CREATE SCHEMA quanlykhachsan CHARACTER SET 'utf8mb4';
USE quanlykhachsan;

-- Tạo bảng HoaDon
CREATE TABLE hoadon (
    maHD INT PRIMARY KEY,
    ngayHD DATE,
    hoTen VARCHAR(100),
    kHD VARCHAR(2),
    donGia DOUBLE,
    soGioThue INT,
    soNgayThue INT,
    thanhTien DOUBLE
);

-- Thêm dữ liệu mẫu vào bảng HoaDon
INSERT INTO HoaDon (MaHD, NgayHD, HoTen, kHD, DonGia, SoNgayThue, SoGioThue) VALUES 
(1, '2024-11-01', N'Nguyễn Văn A', 'SN', 300000, 5, NULL),
(2, '2024-11-02', N'Lê Thị B', 'SG', 50000, NULL, 12),
(3, '2024-11-03', N'Phạm Văn C', 'SN', 400000, 10, NULL),
(4, '2024-11-04', N'Hoàng Thị D', 'SG', 70000, NULL, 15),
(5, '2024-11-05', N'Võ Văn E', 'SN', 250000, 3, NULL),
(6, '2024-11-06', N'Nguyễn Thị F', 'SG', 60000, NULL, 8),
(7, '2024-11-07', N'Lý Văn G', 'SN', 320000, 2, NULL),
(8, '2024-11-08', N'Phạm Thị H', 'SG', 55000, NULL, 24),
(9, '2024-11-09', N'Ngô Văn I', 'SN', 400000, 12, NULL),
(10, '2024-11-10', N'Trần Thị J', 'SG', 80000, NULL, 6),
(11, '2024-11-11', N'Bùi Văn K', 'SN', 350000, 6, NULL),
(12, '2024-11-12', N'Đặng Thị L', 'SG', 45000, NULL, 5),
(13, '2024-11-13', N'Phan Văn M', 'SN', 290000, 7, NULL),
(14, '2024-11-14', N'Trương Thị N', 'SG', 65000, NULL, 9),
(15, '2024-11-15', N'Phạm Văn O', 'SN', 310000, 8, NULL),
(16, '2024-11-16', N'Lê Thị P', 'SG', 53000, NULL, 20),
(17, '2024-11-17', N'Nguyễn Văn Q', 'SN', 340000, 9, NULL),
(18, '2024-11-18', N'Trần Thị R', 'SG', 47000, NULL, 13),
(19, '2024-11-19', N'Hoàng Văn S', 'SN', 400000, 4, NULL),
(20, '2024-11-20', N'Nguyễn Thị T', 'SG', 49000, NULL, 22);

-- Tạo bảng HDTheoNgay
CREATE TABLE HDTheoNgay (
    MaHD INT PRIMARY KEY,               -- Sử dụng MaHD từ bảng HoaDon
    TongSoNgay INT NOT NULL,            -- Tổng số ngày thuê
    ThanhTien DECIMAL(18, 2) NOT NULL,  -- Thành tiền
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD) -- Ràng buộc khóa ngoại với bảng HoaDon
);

-- Tạo bảng HDTheoGio
CREATE TABLE HDTheoGio (
    MaHD INT PRIMARY KEY,               -- Sử dụng MaHD từ bảng HoaDon
    TongSoGio INT NOT NULL,             -- Tổng số giờ thuê
    ThanhTien DECIMAL(18, 2) NOT NULL,  -- Thành tiền
    FOREIGN KEY (MaHD) REFERENCES HoaDon(MaHD) -- Ràng buộc khóa ngoại với bảng HoaDon
);

-- Thêm dữ liệu mẫu vào bảng HDTheoNgay
INSERT INTO HDTheoNgay (MaHD, TongSoNgay, ThanhTien) VALUES 
(1, 5, 1500000),
(3, 10, 4000000),
(5, 3, 750000),
(7, 2, 640000),
(9, 12, 4800000),
(11, 6, 2100000),
(13, 7, 2030000),
(15, 8, 2480000),
(17, 9, 3060000),
(19, 4, 1600000);

-- Thêm dữ liệu mẫu vào bảng HDTheoGio
INSERT INTO HDTheoGio (MaHD, TongSoGio, ThanhTien) VALUES 
(2, 12, 600000),
(4, 15, 1050000),
(6, 8, 480000),
(8, 24, 1320000),
(10, 6, 480000),
(12, 5, 225000),
(14, 9, 585000),
(16, 20, 1060000),
(18, 13, 611000),
(20, 22, 1078000);
