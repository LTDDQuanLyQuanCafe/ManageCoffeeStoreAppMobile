package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model;

public class GioHang {

    String idsp;
    String hinhsp;
    String tensp;
    String giasp;
    int soluong;

    public String getIdsp() {
        return idsp;
    }

    public void setIdsp(String idsp) {
        this.idsp = idsp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public GioHang(String idsp, String hinhsp, String tensp, String giasp, int soluong) {
        this.idsp = idsp;
        this.hinhsp = hinhsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.soluong = soluong;
    }


}
