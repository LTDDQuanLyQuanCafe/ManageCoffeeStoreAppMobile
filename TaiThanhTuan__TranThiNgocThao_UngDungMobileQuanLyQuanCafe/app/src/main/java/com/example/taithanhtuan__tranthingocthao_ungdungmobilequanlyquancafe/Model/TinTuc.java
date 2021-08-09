package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model;

public class TinTuc {
    public TinTuc(String HINHANH, String TIEUDE, String NOIDUNG) {
        this.HINHANH = HINHANH;
        this.TIEUDE = TIEUDE;
        this.NOIDUNG = NOIDUNG;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }

    public String getTIEUDE() {
        return TIEUDE;
    }

    public void setTIEUDE(String TIEUDE) {
        this.TIEUDE = TIEUDE;
    }

    public String getNOIDUNG() {
        return NOIDUNG;
    }

    public void setNOIDUNG(String NOIDUNG) {
        this.NOIDUNG = NOIDUNG;
    }

    public String TIEUDE;

    public String NOIDUNG;

    public String HINHANH;

}
