package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.SPTrangChuAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import java.util.ArrayList;

public class TinTucActivity extends AppCompatActivity {

    TinTucAdapter tinTucAdapter;
    RecyclerView recyclerView_TinTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Trang tin tức THE COFFEE HOUSE"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin_tuc);

        ArrayList<TinTuc> tinTucArrayList = new ArrayList<>();
        tinTucArrayList.add(new TinTuc("","", ""));
        tinTucArrayList.add(new TinTuc("","", ""));
        tinTucArrayList.add(new TinTuc("","", ""));
        setTinTucRecycler(tinTucArrayList);

    }

    private void setTinTucRecycler(ArrayList<TinTuc> tinTucArrayList) {
        recyclerView_TinTuc = findViewById(R.id.recyclerview_TinTuc);
        recyclerView_TinTuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        tinTucAdapter = new TinTucAdapter(  tinTucArrayList, this);
        recyclerView_TinTuc.setAdapter(tinTucAdapter);
    }
}