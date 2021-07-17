package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DSThucDonActivity extends AppCompatActivity {

    String ip = "192.168.1.2";
    ArrayList<DALThucDon> data;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    String url = "ThucDon/all";
    String url2 = "https://" + ip + ":5566/api/ThucDon/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_thuc_don);
        data = new ArrayList<>();

//        ArrayList<DALThucDon> loaiTDArrayList = new ArrayList<>();
//        DALThucDon da = new DALThucDon("001","Trà", "1000", "Cốc","cb4.jpg","Hahahha", "01");
//        loaiTDArrayList.add(da);
//        loaiTDArrayList.add(da);
//        loaiTDArrayList.add(da);
//        loaiTDArrayList.add(da);
//        getThucDonData(loaiTDArrayList);
        try {
            data = LayDanhMucSP();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getThucDonData(data);
    }
//    private void  getThucDonData(List<DALThucDon> dalThucDonList){
//
//        listView = findViewById(R.id.listView);
//        arrayList = new ArrayList<>();
//        productAdapter = new ProductAdapter(this, dalThucDonList);
//        listView.setAdapter(productAdapter);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
////        recommendedRecyclerView.setLayoutManager(layoutManager);
////        recommendedRecyclerView.setAdapter(recommendedAdapter);
//
//    }

    public ArrayList<DALThucDon> LayDanhMucSP() throws JSONException {
        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();

        new Thread(new Runnable() {

            @Override
            public void run() {
                ParseJson parseJson = new ParseJson();
                String p = parseJson.readStringFileContent(Common.preUrl+url);
                JSONArray response = null;
                try {
                    response = new JSONArray(p);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        data.add(new DALThucDon(jsonObject.getString("maThucDon"), jsonObject.getString("tenMon"), jsonObject.getString("donGia"), jsonObject.getString("dvt"), jsonObject.getString("hinhAnh"), jsonObject.getString("moTa").trim(), jsonObject.getString("maLoaiTD")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return data;
    }

    private void  getThucDonData(List<DALThucDon> dalThucDonList){
        recyclerView = findViewById(R.id.listView);
        productAdapter = new ProductAdapter(this, dalThucDonList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void itemClick(DALThucDon products) {
        Intent intent = new Intent(this,ChiTietThucDonActivity.class);
//        intent.putExtra("key1",products);
        Common.thucDon = products;
        startActivity(intent);
    }

}