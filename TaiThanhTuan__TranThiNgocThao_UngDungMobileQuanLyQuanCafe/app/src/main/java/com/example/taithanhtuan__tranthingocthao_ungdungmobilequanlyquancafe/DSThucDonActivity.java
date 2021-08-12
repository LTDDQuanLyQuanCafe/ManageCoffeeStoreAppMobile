package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LoaiTDAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.SPDaXemAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DSThucDonActivity extends AppCompatActivity implements OnClickListener {


    static ArrayList<DALThucDon> data  ;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    static String url = "ThucDon/all";
    SearchView searchView;

    LoaiTD loaiTD;

    static RecyclerView.Adapter adaptersp;
    ImageView img_NoPro;
    static String id;
    static String url2;
    SearchView editText;
    SPDaXemAdapter utils;
    BottomAppBar bottomAppBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        utils = new SPDaXemAdapter(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_thuc_don);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);
        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Danh sách thực đơn"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);


        bottomAppBar = findViewById(R.id.bottomAppBar2);
        img_NoPro = findViewById(R.id.img_NoProduct);
        adaptersp = new ProductAdapter(this, data, this);
        data  = new ArrayList<>();

        id = Common.loaidachon.getMALOAITD();
        if(id == "01"){
            url2 =  "ThucDon/all";
        }
        else {
            url2 = "ThucDon/get/category/" + id;
        }
        try {
            data = LayDanhMucSP();
            if(data==null){
                Toast.makeText(DSThucDonActivity.this, "Lỗi", Toast.LENGTH_SHORT);
                return;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        getThucDonData(data);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        checkDataInRecycle();

        //Xu ly search
        editText = findViewById(R.id.search_view);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DSThucDonActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
        bottomAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                navigation(id);

                return true;
            }
        });
    }
    private void navigation(int mSelectedId) {
        Intent intent = null;
        if(mSelectedId == R.id.account)
        {
            intent = new Intent(this, InfoKhachHangActivity.class);
            startActivity(intent);
        }

        if(mSelectedId == R.id.heart)
        {
            intent = new Intent(this,SPDaXemActivity.class);
            startActivity(intent);
        }
    }


    public static ArrayList<DALThucDon> LayDanhMucSP() throws JSONException {
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
                        data.add(new DALThucDon(jsonObject.getString("maThucDon"), jsonObject.getString("tenMon"), jsonObject.getString("donGia"), jsonObject.getString("dvt"), jsonObject.getString("hinhAnh"), jsonObject.getString("moTa").trim(), jsonObject.getString("maLoaiTD")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adaptersp.notifyDataSetChanged();
            }
        }).start();
        return data;
    }

    private void getThucDonData(ArrayList<DALThucDon> dalThucDonList) {
        recyclerView = findViewById(R.id.listView);
        productAdapter = new ProductAdapter(this, dalThucDonList, this);
        productAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

    }
    private void checkDataInRecycle()
    {
        if (data.size() <0)
        {
            adaptersp.notifyDataSetChanged();
            img_NoPro.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else
        {
            adaptersp.notifyDataSetChanged();
            img_NoPro.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm
                = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public void itemClick(DALThucDon dalThucDon) {
        Intent intent = new Intent(this,ChiTietThucDonActivity.class);
        Common.thucDon = dalThucDon;
        SPDaXemAdapter.thucDonArrayList_History.add(dalThucDon);
        SPDaXemAdapter.WriteToFileInternal(SPDaXemAdapter.thucDonArrayList_History);
        startActivity(intent);
    }
}





