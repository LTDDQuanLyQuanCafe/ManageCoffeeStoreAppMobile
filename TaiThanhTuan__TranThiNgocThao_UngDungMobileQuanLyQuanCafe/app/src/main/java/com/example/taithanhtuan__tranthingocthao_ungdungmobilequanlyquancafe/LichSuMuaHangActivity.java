package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LSMuaHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListenerLS;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;
import com.facebook.login.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LichSuMuaHangActivity extends AppCompatActivity implements OnClickListenerLS {

    ArrayList<DonGiaoHang> data  ;
    RecyclerView recyclerView;
    LSMuaHangAdapter productAdapter;
    SearchView searchView;

    RecyclerView.Adapter adaptersp;
    String url2;
    SearchView editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_mua_hang);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Lịch sử mua hàng"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);


        adaptersp = new LSMuaHangAdapter(this, data, this);
        data  = new ArrayList<>();

        if(Common.USER.getDienThoai()==null){
            Toast.makeText(LichSuMuaHangActivity.this, "Bạn vui lòng đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LichSuMuaHangActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }else {
            url2 =  "DonGiaoHang/get/" + Common.USER.getDienThoai();

            try {
                data = LayDanhMucSP();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getThucDonData(data);
        }
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
                        data.add(new DonGiaoHang(Integer.parseInt(jsonObject.getString("maGiaoHang")) ,Integer.parseInt( jsonObject.getString("maKhachHang")),Integer.parseInt( jsonObject.getString("maNhanVien")),  jsonObject.getString("ngayGiao"), jsonObject.getString("diaChiGiao").trim(), jsonObject.getString("tongTien"),jsonObject.getString("trangThai"), jsonObject.getString("ghiChu"), Integer.parseInt(jsonObject.getString("mathucdon")), Integer.parseInt(jsonObject.getString("soluonggiao")), jsonObject.getString("thanhtien"), jsonObject.getString("hinhanh"), jsonObject.getString("tenmon" ),  Integer.parseInt(jsonObject.getString("dongia")),  jsonObject.getString("hoten"),jsonObject.getString("dienthoai")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adaptersp.notifyDataSetChanged();
            }
        }).start();
        return data;
    }

    private void getThucDonData(ArrayList<DonGiaoHang> dalThucDonList) {
        recyclerView = findViewById(R.id.listView);
        productAdapter = new LSMuaHangAdapter(this, dalThucDonList, this);
        productAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

    }


    @Override
    public void ItemClick(DonGiaoHang donGiaoHang) {
        Intent intent = new Intent(this,ChiTietLSMuaHangActivity.class);
        Common.donGiaoHang = donGiaoHang;
        startActivity(intent);
    }
}





