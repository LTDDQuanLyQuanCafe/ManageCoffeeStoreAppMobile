package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class DALThucDon {

    public int getMaThucDon() {
        return MaThucDon;
    }

    public void setMaThucDon(int maThucDon) {
        MaThucDon = maThucDon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String getMaLoaiTD() {
        return MaLoaiTD;
    }

    public void setMaLoaiTD(String maLoaiTD) {
        MaLoaiTD = maLoaiTD;
    }

    int MaThucDon ;

    String TenMon ;

    double DonGia ;

    String DVT ;

    String HinhAnh ;

    String MoTa ;

    String MaLoaiTD ;

}
