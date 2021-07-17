package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.util.ArrayList;

public class Common {
    public static String preUrl = "https://192.168.1.9:5566/api/";

    //    public static NGUOIDUNG USER;
    public static ArrayList<GioHang> carts = new ArrayList<>();
    public static DALThucDon thucDon;
}
