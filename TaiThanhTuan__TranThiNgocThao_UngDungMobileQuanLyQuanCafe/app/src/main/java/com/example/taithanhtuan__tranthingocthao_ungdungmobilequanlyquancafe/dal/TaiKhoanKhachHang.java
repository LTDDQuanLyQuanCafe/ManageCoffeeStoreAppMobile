package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal;

public class TaiKhoanKhachHang {
    public  TaiKhoanKhachHang(){

    }
    public TaiKhoanKhachHang(String maKH, String tenTaiKhoan, String matKhau, String ngayTao, String maKhachHang, String hoTen, String ngaySinh, String gioiTinh, String email, String dienThoai, String diaChi, int diemTL, String hinhAnh) {
        MaKH = maKH;
        TenTaiKhoan = tenTaiKhoan;
        MatKhau = matKhau;
        NgayTao = ngayTao;
        MaKhachHang = maKhachHang;
        HoTen = hoTen;
        NgaySinh = ngaySinh;
        GioiTinh = gioiTinh;
        Email = email;
        DienThoai = dienThoai;
        DiaChi = diaChi;
        DiemTL = diemTL;
        HinhAnh = hinhAnh;
    }

    String MaKH;
    String TenTaiKhoan;
    String MatKhau;
    String NgayTao;
    String MaKhachHang;
    String HoTen;
    String NgaySinh;
    String GioiTinh;
    String Email;
    String DienThoai;
    String DiaChi;
    int DiemTL;
    public String HinhAnh;

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getTenTaiKhoan() {
        return TenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        TenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String ngayTao) {
        NgayTao = ngayTao;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getDiemTL() {
        return DiemTL;
    }

    public void setDiemTL(int diemTL) {
        DiemTL = diemTL;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

}
