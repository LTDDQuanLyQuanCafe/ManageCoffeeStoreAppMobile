package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.util.ArrayList;

public class Common {
    //y : 192.168.22.102
    //thao: 192.168.1.4
    public static String preUrl = "https://192.168.1.2:5566/api/";

    //    public static NGUOIDUNG USER;
    public static ArrayList<GioHang> carts = new ArrayList<>();
    public static DALThucDon thucDon;
    public static LoaiTD loaidachon;

    public static double ThanhToan = 0;

}
