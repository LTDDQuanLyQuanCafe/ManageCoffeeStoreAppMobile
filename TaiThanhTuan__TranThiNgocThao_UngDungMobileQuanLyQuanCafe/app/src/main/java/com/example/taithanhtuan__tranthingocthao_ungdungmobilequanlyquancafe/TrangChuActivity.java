package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LoaiTDAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LoaiTDAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TrangChuActivity extends AppCompatActivity {
    //
//    Toolbar toolbar;
//    DrawerLayout drawerLayout;
//    NavigationView navigationView;
//    ListView listView;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_trang_chu);
//    }
    RecyclerView recyclerView;
    RecyclerView recyclerView_TinTuc;

    LoaiTDAdapter loaiTDAdapter;
    TinTucAdapter tinTucAdapter;

    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;

    Toolbar toolbar;
    NavigationView navigationLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        // Khởi tạo RecyclerView.



        drawerLayout = findViewById(R.id.drawerlayout);
        toolbar = findViewById(R.id.toolbar);
        navigationLeft = findViewById(R.id.nagivationviewLeft);

        loadViewFlipper();

        ArrayList<LoaiTD> loaiTDArrayList = new ArrayList<>();
        loaiTDArrayList.add(new LoaiTD("01","TẤT CẢ", 0, R.drawable.ic__10));
        loaiTDArrayList.add(new LoaiTD("L07","CÀ PHÊ MÁY", 0, R.drawable.ic__7));
        loaiTDArrayList.add(new LoaiTD("L08","COLD BREW", 0, R.drawable.ic__3));
        loaiTDArrayList.add(new LoaiTD("L09","CÀ PHÊ GÓI", 0, R.drawable.ic__9));
        loaiTDArrayList.add(new LoaiTD("L01","CÀ PHÊ VIỆT NAM", 0, R.drawable.ic__2));
        loaiTDArrayList.add(new LoaiTD("L02","TRÀ TRÁI CÂY", 0, R.drawable.ic__1));
        loaiTDArrayList.add(new LoaiTD("L03","TRÀ SỮA MACCHIATO", 0, R.drawable.ic__33));
        loaiTDArrayList.add(new LoaiTD("L04","THỨC UỐNG TRÁI CÂY", 0, R.drawable.ic__4));
        loaiTDArrayList.add(new LoaiTD("L05","MATCHA - SÔCÔLA", 0, R.drawable.ic__5));
        loaiTDArrayList.add(new LoaiTD("L06","BÁNH & SNACK", 0, R.drawable.ic__6));
        setLoaiTDRecycler(loaiTDArrayList);
        //m4BMked
        ArrayList<TinTuc> tinTucArrayList = new ArrayList<>();
        tinTucArrayList.add(new TinTuc("xZ6tZS6","(*) Giảm 40% cho thành viên thân thiết", "Nhập mã DONGHANH khi order món ngon qua app, Nhà mời liền 40% (tối đa 50.000đ), áp dụng cho đơn từ 4 món đó nha."));
        tinTucArrayList.add(new TinTuc("tNyqGb4","Mua 1 tặng 1 tinh dầu", "Nhập mã TINHDAU, bạn sẽ được tặng ngay 1 ly Trà sữa hoặc Mochi mát lạnh tùy bạn chọn đó nha."));
        tinTucArrayList.add(new TinTuc("xZ6tZS6","Combo sáng ưu đãi giá 39k", "Nạp căng miếng năng lượng cùng combo NĂNG LƯỢNG SÁNG 39k ngay thôi. Với cà phê sữa đá đậm đầ kết hợp 2 bánh mì que thơm ngậy, combo Năng lượng của Nhà sẽ cùng bạn:"));
        setTinTucRecycler(tinTucArrayList);
    }
    private void setLoaiTDRecycler(ArrayList<LoaiTD> loaiTDArrayList) {
        recyclerView = findViewById(R.id.recycleView_option);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loaiTDAdapter = new LoaiTDAdapter(loaiTDArrayList, this);
        recyclerView.setAdapter(loaiTDAdapter);
    }

    private void setTinTucRecycler(ArrayList<TinTuc> tinTucArrayList) {
        recyclerView_TinTuc = findViewById(R.id.recycleView_tintuc);
        recyclerView_TinTuc.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        tinTucAdapter = new TinTucAdapter(tinTucArrayList, this);
        recyclerView_TinTuc.setAdapter(tinTucAdapter);
    }
    void loadViewFlipper() {
        viewFlipper = findViewById(R.id.viewflipper);
        // Y: 192.168.22.102    //Ru: 192.168.1.5
        String urlslide = "https://imgur.com/";
        ArrayList<String> mangslide = new ArrayList<>();

        mangslide.add(urlslide + "ZFXvtU7.jpg");
        mangslide.add(urlslide + "ImE6pzo.jpg");
        mangslide.add(urlslide + "C9hfUxk.jpg");
        mangslide.add(urlslide + "VV3cjR9.jpg");
        mangslide.add(urlslide + "C1sA23O.jpg");
        mangslide.add(urlslide + "iwZZjT9.jpg");
        for (int i = 0; i < mangslide.size(); i++) {
            ImageView imageView = new ImageView(this);
            Picasso.with(this).load(mangslide.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }

        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.startFlipping();
    }


    //dieu huong navigation
    private void navigation(int mSelectedId) {
//        Intent intent = null;
//        if (mSelectedId == R.id.mnu_user) {
//            intent = new Intent(TrangChuActivity.this, InfoKhachHangActivity.class);
//            startActivity(intent);
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
//        if (mSelectedId == R.id.mnu_cart) {
//            intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
//            startActivity(intent);
//
//            drawerLayout.closeDrawer(GravityCompat.START);
//        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart)
        {
            Intent intent = new Intent(this, GioHangActivity.class);
            startActivity(intent);
        }

        return true;
    }

    //nut tro ve navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

}