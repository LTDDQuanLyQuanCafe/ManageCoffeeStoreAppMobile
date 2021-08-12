package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.DonGiaoHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LSMuaHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.SPTrangChuAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TinTucActivity extends AppCompatActivity {


    ArrayList<TinTuc> data  ;

    RecyclerView.Adapter adaptersp;
    String url2;

    TinTucAdapter tinTucAdapter;
    RecyclerView recyclerView_TinTuc;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(TinTucActivity.this, TrangChuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Trang tin tức"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);


        adaptersp = new TinTucAdapter(data, this);
        data  = new ArrayList<>();

        url2 =  "TinTuc/all/" ;

        try {
            data = LayDanhMucSP();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTinTucRecycler(data);

//        ArrayList<TinTuc> tinTucArrayList = new ArrayList<>();
//        tinTucArrayList.add(new TinTuc("","", ""));
//        tinTucArrayList.add(new TinTuc("","", ""));
//        tinTucArrayList.add(new TinTuc("","", ""));
//        setTinTucRecycler(tinTucArrayList);

    }

    private void setTinTucRecycler(ArrayList<TinTuc> tinTucArrayList) {
        recyclerView_TinTuc = findViewById(R.id.recyclerview_TinTuc);
        recyclerView_TinTuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        tinTucAdapter = new TinTucAdapter(  tinTucArrayList, this);
        recyclerView_TinTuc.setAdapter(tinTucAdapter);
    }

    public ArrayList<TinTuc> LayDanhMucSP() throws JSONException {
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
                        data.add(new TinTuc (jsonObject.getString("hinhAnh"), jsonObject.getString("tieuDe"), jsonObject.getString("noiDung")));

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