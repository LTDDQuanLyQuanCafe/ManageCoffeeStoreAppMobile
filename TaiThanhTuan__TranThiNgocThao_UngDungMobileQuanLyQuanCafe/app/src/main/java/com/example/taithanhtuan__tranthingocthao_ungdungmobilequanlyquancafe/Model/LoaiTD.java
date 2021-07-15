package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class LoaiTD {
    public String MALOAITD;
    public String TENLOAITD;
    public int SOLUONGMON;
    public int HINHANH;

    public LoaiTD(String MALOAITD, String TENLOAITD, int SOLUONGMON, int HINHANH) {
        this.MALOAITD = MALOAITD;
        this.TENLOAITD = TENLOAITD;
        this.SOLUONGMON = SOLUONGMON;
        this.HINHANH = HINHANH;
    }

    public int getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(int HINHANH) {
        this.HINHANH = HINHANH;
    }

    public String getMALOAITD() {
        return MALOAITD;
    }

    public void setMALOAITD(String MALOAITD) {
        this.MALOAITD = MALOAITD;
    }

    public String getTENLOAITD() {
        return TENLOAITD;
    }

    public void setTENLOAITD(String TENLOAITD) {
        this.TENLOAITD = TENLOAITD;
    }

    public int getSOLUONGMON() {
        return SOLUONGMON;
    }

    public void setSOLUONGMON(int SOLUONGMON) {
        this.SOLUONGMON = SOLUONGMON;
    }

    public static Bitmap convertStringToBitmapFromAccess(Context context, String
            filename) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
