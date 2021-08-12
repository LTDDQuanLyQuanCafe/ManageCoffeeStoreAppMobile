package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.SPDaXemAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import org.json.JSONException;

import java.util.ArrayList;

public class SPDaXemActivity extends AppCompatActivity implements OnClickListener {
    ArrayList<DALThucDon> data  ;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    SearchView searchView;
    SPDaXemAdapter utils;
    public ImageView tvNull;
    static RecyclerView.Adapter adaptersp;
    
//    ImageView img_NoPro;
//    static String id;
//    static String url2;
//    SearchView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        utils = new SPDaXemAdapter(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_da_xem);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Sản phẩm đã xem"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        tvNull = findViewById(R.id.img_NoProduct);
        adaptersp = new ProductAdapter(this, data, this);
        data  = new ArrayList<>();

        try {
            data = SPDaXemAdapter.LoadFileInternal();
            adaptersp.notifyDataSetChanged();
            if(data==null){
                Toast.makeText(SPDaXemActivity.this, "Chưa xem sp nào hết á", Toast.LENGTH_SHORT);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        getThucDonData(data);
        checkData();
    }
    private void getThucDonData(ArrayList<DALThucDon> dalThucDonList) {
        recyclerView = findViewById(R.id.listView);
        productAdapter = new ProductAdapter(this, dalThucDonList, this);
        productAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);

    }
    @Override
    public void itemClick(DALThucDon dalThucDon) {
        Intent intent = new Intent(this,ChiTietThucDonActivity.class);
        Common.thucDon = dalThucDon;
        startActivity(intent);
    }

    private void checkData() {
        if(SPDaXemAdapter.thucDonArrayList_History.size() <=0)
        {
            adaptersp.notifyDataSetChanged();
            tvNull.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else
        {
            adaptersp.notifyDataSetChanged();
            tvNull.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}