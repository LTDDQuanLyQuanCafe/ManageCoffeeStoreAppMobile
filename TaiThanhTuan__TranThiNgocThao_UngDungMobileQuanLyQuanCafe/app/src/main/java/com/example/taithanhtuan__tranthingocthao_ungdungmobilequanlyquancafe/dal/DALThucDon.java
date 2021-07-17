package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class DALThucDon implements Serializable {

    public String getMaThucDon() {
        return MaThucDon;
    }

    public void setMaThucDon(String maThucDon) {
        MaThucDon = maThucDon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
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

    public DALThucDon() {
    }

    public DALThucDon(String maThucDon, String tenMon, String donGia, String DVT, String hinhAnh, String moTa, String maLoaiTD) {
        MaThucDon = maThucDon;
        TenMon = tenMon;
        DonGia = donGia;
        this.DVT = DVT;
        HinhAnh = hinhAnh;
        MoTa = moTa;
        MaLoaiTD = maLoaiTD;
    }

    public String MaThucDon ;

    public String TenMon ;



    public String DonGia ;

    public String DVT ;

    public String HinhAnh ;

    public String MoTa ;

    public String MaLoaiTD ;

}
