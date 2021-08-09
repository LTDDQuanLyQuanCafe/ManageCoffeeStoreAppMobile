package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model;

public class CTGiaoHang {
    public int MAGIAOHANG;
    public int MATHUCDON;
    public int SOLUONGGIAO;
    public double THANHTIEN;
    public String HINHANH;
    public String TENMON;
    public int DONGIA;

    public CTGiaoHang(int MAGIAOHANG, int MATHUCDON, int SOLUONGGIAO, double THANHTIEN, String HINHANH, String TENMON, int DONGIA) {
        this.MAGIAOHANG = MAGIAOHANG;
        this.MATHUCDON = MATHUCDON;
        this.SOLUONGGIAO = SOLUONGGIAO;
        this.THANHTIEN = THANHTIEN;
        this.HINHANH = HINHANH;
        this.TENMON = TENMON;
        this.DONGIA = DONGIA;
    }

    public CTGiaoHang(int MAGIAOHANG, int MATHUCDON, int SOLUONGGIAO, double THANHTIEN) {
        this.MAGIAOHANG = MAGIAOHANG;
        this.MATHUCDON = MATHUCDON;
        this.SOLUONGGIAO = SOLUONGGIAO;
        this.THANHTIEN = THANHTIEN;
    }

    public int getMAGIAOHANG() {
        return MAGIAOHANG;
    }

    public void setMAGIAOHANG(int MAGIAOHANG) {
        this.MAGIAOHANG = MAGIAOHANG;
    }

    public int getMATHUCDON() {
        return MATHUCDON;
    }

    public void setMATHUCDON(int MATHUCDON) {
        this.MATHUCDON = MATHUCDON;
    }

    public int getSOLUONGGIAO() {
        return SOLUONGGIAO;
    }

    public void setSOLUONGGIAO(int SOLUONGGIAO) {
        this.SOLUONGGIAO = SOLUONGGIAO;
    }

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }

    public String getTENMON() {
        return TENMON;
    }

    public void setTENMON(String TENMON) {
        this.TENMON = TENMON;
    }

    public int getDONGIA() {
        return DONGIA;
    }

    public void setDONGIA(int DONGIA) {
        this.DONGIA = DONGIA;
    }
}
