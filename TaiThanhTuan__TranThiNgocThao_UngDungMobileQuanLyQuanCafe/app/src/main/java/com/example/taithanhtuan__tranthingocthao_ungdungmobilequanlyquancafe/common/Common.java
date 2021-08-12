package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Common {
    //y : 192.168.22.102
    //thao: 192.168.1.4
    public static String preUrl = "https://192.168.1.7:5566/api/";

    public static TaiKhoanKhachHang USER = new TaiKhoanKhachHang();
    public static DonGiaoHang donGiaoHang;
    public static ArrayList<GioHang> carts = new ArrayList<>();
    public static DALThucDon thucDon;
    public static LoaiTD loaidachon;

    public static double ThanhToan = 0;


    //Tạo format tiền VND
    public static String formatNumberCurrency(String gia)
    {
        DecimalFormat format = new DecimalFormat("#,###");
        return format.format(Double.parseDouble(gia));
    }
    public static String formatNumberCurrency(double money)
    {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(money);

        return  formattedNumber;
    }
}