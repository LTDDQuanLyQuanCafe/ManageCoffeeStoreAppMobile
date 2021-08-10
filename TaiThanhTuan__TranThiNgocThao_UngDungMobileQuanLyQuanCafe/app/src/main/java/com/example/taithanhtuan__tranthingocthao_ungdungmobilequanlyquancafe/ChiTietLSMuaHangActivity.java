package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ChiTietLSMuaHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LSMuaHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietLSMuaHangActivity extends AppCompatActivity {
    Button btnLienHe;
    TextView lbTrangThai, lbNgay, lbHoTen, lbSDT, lbDiaChi, lbTongTien, lbTongCong;
    RecyclerView recyclerView_LS;
    ArrayList<DonGiaoHang> data  ;
    ChiTietLSMuaHangAdapter productAdapter;
    RecyclerView.Adapter adaptersp;
    String url2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_l_s_mua_hang);
        anhXa();

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Chi tiết mua hàng"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        //Gui du lieu
        Intent intent = getIntent();
        if(Common.donGiaoHang != null)
        {

//            products = (Products) intent.getSerializableExtra("key1");
            //Set lại id để load dữ liệu từ HomeActivity qua
            if(Common.donGiaoHang.getTRANGTHAI() == "true"){
                lbTrangThai.setText("Đã giao hàng");
            }
            else {
                lbTrangThai.setText("Chưa hoàn thành");
            }
            lbNgay.setText(Common.donGiaoHang.getNGAYGIAO());
            lbHoTen.setText(Common.donGiaoHang.getHOTEN());
            lbSDT.setText(Common.donGiaoHang.getDIENTHOAI());
            lbDiaChi.setText(Common.donGiaoHang.getDIACHIGIAO());


            lbTongTien.setText( Common.formatNumberCurrency(Common.donGiaoHang.getTONGTIEN()) + " VNĐ");
            lbTongCong.setText( Common.formatNumberCurrency(Common.donGiaoHang.getTONGTIEN()) + " VNĐ");
        }

        adaptersp = new ChiTietLSMuaHangAdapter(this, data);
        data  = new ArrayList<>();

        url2 =  "DonGiaoHang/getCTGH/" + Common.USER.getDienThoai();

        try {
            data = LayDanhMucSP();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getThucDonData(data);
        btnLienHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0978154394"));
                startActivity(intent);
            }
        });
    }


    public boolean onSupportNavigateUp() {
//        hideSoftKeyboard(searchView);
        Intent intent = new Intent(ChiTietLSMuaHangActivity.this, LichSuMuaHangActivity.class);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

    public void anhXa(){
        btnLienHe = findViewById(R.id.btn_Huy);

        lbTrangThai = findViewById(R.id.txt_TrangThai);
        lbNgay = findViewById(R.id.txt_NgayDH);
        lbHoTen = findViewById(R.id.txt_Ten);
        lbSDT = findViewById(R.id.txt_SDT);
        lbDiaChi = findViewById(R.id.txt_DiaChi);
        lbTongTien = findViewById(R.id.txt_TongTien);
        lbTongCong = findViewById(R.id.txt_TongCong);

    }

    private void getThucDonData(ArrayList<DonGiaoHang> dalThucDonList) {
        recyclerView_LS = findViewById(R.id.recycler_LS);
        productAdapter = new ChiTietLSMuaHangAdapter(this, dalThucDonList);
        productAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView_LS.setLayoutManager(layoutManager);
        recyclerView_LS.setAdapter(productAdapter);

    }
    public ArrayList<DonGiaoHang> LayDanhMucSP() throws JSONException {
        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();

        new Thread(new Runnable() {

            @Override
            public void run() {
                ParseJson parseJson = new ParseJson();
                String p = parseJson.readStringFileContent(Common.preUrl + url2);
                JSONArray response = null;
                try {
                    response = new JSONArray(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        data.add(new DonGiaoHang(Integer.parseInt(jsonObject.getString("maGiaoHang")) ,Integer.parseInt( jsonObject.getString("maKhachHang")),Integer.parseInt( jsonObject.getString("maNhanVien")),  jsonObject.getString("ngayGiao"), jsonObject.getString("diaChiGiao").trim(), jsonObject.getString("tongTien"),jsonObject.getString("trangThai"), jsonObject.getString("ghiChu").trim(), Integer.parseInt(jsonObject.getString("mathucdon")), Integer.parseInt(jsonObject.getString("soluonggiao")), jsonObject.getString("thanhtien"), jsonObject.getString("hinhanh").trim(), jsonObject.getString("tenmon" ).trim(),  Integer.parseInt(jsonObject.getString("dongia")),  jsonObject.getString("hoten").trim(),jsonObject.getString("dienthoai")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adaptersp.notifyDataSetChanged();
            }
        }).start();
        return data;
    }



}