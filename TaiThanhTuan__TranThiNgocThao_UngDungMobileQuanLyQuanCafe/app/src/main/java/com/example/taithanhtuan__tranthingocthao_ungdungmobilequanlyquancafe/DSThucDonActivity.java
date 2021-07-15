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

    String ip = "192.168.43.90";
    ArrayList<DALThucDon> data = new ArrayList<>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    String url = "https://" + ip +":5566/api/";
    String url2 = "https://" + ip + ":5566/api/ThucDon/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_thuc_don);
        getThucDonData(data);
        LayDanhMucSP();
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

    public void LayDanhMucSP() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
//                        JSONObject jsonObject = response.getJSONObject(i);
//                        data.add(new DALThucDon(jsonObject.getString("MATHUCDON"), jsonObject.getString("TENMON"), jsonObject.getString("DONGIA"),jsonObject.getString("DVT"), jsonObject.getString("HINHANH"),jsonObject.getString("MOTA"),jsonObject.getString("MALOAITD")));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                productAdapter.notifyDataSetChanged();
//            }
//        };
//
//        Response.ErrorListener thatbai = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        };
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, thanhcong, thatbai);
//        requestQueue.add(jsonArrayRequest);


        ParseJson parseJson = new ParseJson();
        String url = Common.preUrl+"ThucDon/All";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                    String p = parseJson.readStringFileContent(url);
                    JSONObject jsonObject = new JSONObject(p);
                    JSONArray arrayMa = jsonObject.getJSONArray("MaThucDon");
                    for(int i =0 ; i<arrayMa.length();i++){
                        DALThucDon thucDon = new DALThucDon();
//                        data.add(new DALThucDon(jsonObject.getString("MATHUCDON"), jsonObject.getString("TENMON"), jsonObject.getString("DONGIA"),jsonObject.getString("DVT"), jsonObject.getString("HINHANH"),jsonObject.getString("MOTA"),jsonObject.getString("MALOAITD")));
                        thucDon.setMaThucDon(arrayMa.get(i).toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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

}