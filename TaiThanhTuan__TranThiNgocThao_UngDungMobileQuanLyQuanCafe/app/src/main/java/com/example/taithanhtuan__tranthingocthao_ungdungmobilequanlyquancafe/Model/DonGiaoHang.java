package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model;

import java.util.Date;

public class DonGiaoHang {
    public int MAGIAOHANG;
    public int MAKHACHHANG;
    public int MANHANVIEN;
    public String NGAYGIAO;
    public String DIACHIGIAO;
    public double TONGTIEN;
    public int TRANGTHAI;
    public String GHICHU;

    public DonGiaoHang(int MAGIAOHANG, int MAKHACHHANG, int MANHANVIEN, String NGAYGIAO, String DIACHIGIAO, double TONGTIEN, int TRANGTHAI, String GHICHU) {
        this.MAGIAOHANG = MAGIAOHANG;
        this.MAKHACHHANG = MAKHACHHANG;
        this.MANHANVIEN = MANHANVIEN;
        this.NGAYGIAO = NGAYGIAO;
        this.DIACHIGIAO = DIACHIGIAO;
        this.TONGTIEN = TONGTIEN;
        this.TRANGTHAI = TRANGTHAI;
        this.GHICHU = GHICHU;
    }

    public int getMAGIAOHANG() {
        return MAGIAOHANG;
    }

    public void setMAGIAOHANG(int MAGIAOHANG) {
        this.MAGIAOHANG = MAGIAOHANG;
    }

    public int getMAKHACHHANG() {
        return MAKHACHHANG;
    }

    public void setMAKHACHHANG(int MAKHACHHANG) {
        this.MAKHACHHANG = MAKHACHHANG;
    }

    public int getMANHANVIEN() {
        return MANHANVIEN;
    }

    public void setMANHANVIEN(int MANHANVIEN) {
        this.MANHANVIEN = MANHANVIEN;
    }

    public String getNGAYGIAO() {
        return NGAYGIAO;
    }

    public void setNGAYGIAO(String NGAYGIAO) {
        this.NGAYGIAO = NGAYGIAO;
    }

    public String getDIACHIGIAO() {
        return DIACHIGIAO;
    }

    public void setDIACHIGIAO(String DIACHIGIAO) {
        this.DIACHIGIAO = DIACHIGIAO;
    }

    public double getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(double TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public int getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(int TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getGHICHU() {
        return GHICHU;
    }

    public void setGHICHU(String GHICHU) {
        this.GHICHU = GHICHU;
    }
}
