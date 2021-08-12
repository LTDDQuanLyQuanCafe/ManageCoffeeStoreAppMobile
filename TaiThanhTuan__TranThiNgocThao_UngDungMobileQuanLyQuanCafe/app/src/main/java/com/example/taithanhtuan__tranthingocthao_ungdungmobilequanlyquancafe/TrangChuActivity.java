package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LSMuaHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.LoaiTDAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.SPTrangChuAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.TinTucAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.LoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.TinTuc;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListenerLoaiTD;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

import retrofit2.http.HEAD;

public class TrangChuActivity extends AppCompatActivity implements OnClickListenerLoaiTD, NavigationView.OnNavigationItemSelectedListener, OnClickListener{
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
    private int mSelectedId;
    RecyclerView recyclerView_SP;
    private static final String SELECTED_ITEM_ID = "selected";
    LoaiTDAdapter loaiTDAdapter;
    SPTrangChuAdapter spTrangChuAdapter;
    RecyclerView.Adapter adaptersp;
    String url2;
    ArrayList<DALThucDon> data;

    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    ArrayList<LoaiTD> loaiTDArrayList = new ArrayList<>();
    Toolbar toolbar;
//    RequestQueue mQueue;
    NavigationView navigationLeft;
    String urlLoai = Common.preUrl + "get/category" ;

    Animation ani;
    ImageView icon;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Menu menu;
    MenuItem mnuLogin;
    BottomAppBar bottomAppBar;
    FloatingActionButton floatingActionButton;
    SearchView editText;
    MenuItem mnuTTCN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        icon = findViewById(R.id.imageView_logo);

        navigationLeft = findViewById(R.id.nagivationviewLeft);
        mnuLogin = navigationLeft.findViewById(R.id.mnu_login);

//        mnuLogin = navigationLeft.getMenu().getItem(4);
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        bottomAppBar = findViewById(R.id.bottomAppBar2);
        //animation
        ani = AnimationUtils.loadAnimation(this, R.anim.rotate_logo_home);
        icon.startAnimation(ani);

        if(mnuLogin != null) {
            invalidateOptionsMenu();
            if (loadPreferences("my_email") != null) {
                mnuLogin.setTitle("Đăng xuất");
            } else {
                mnuLogin.setTitle("Đăng nhập");
            }
            mnuLogin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (mnuLogin.getTitle().toString().equals("Đăng nhập")) {
                        Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.TrangChuActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(TrangChuActivity.this, LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    return false;
                }
            });
        }

        navigationLeft.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.mnu_login:
                        MenuItem mnuLogin = navigationLeft.getMenu().getItem(8);
                        if(mnuLogin.getTitle().toString().equals("Đăng nhập")){
                            Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.TrangChuActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            editor.clear();
                                            editor.commit();
                                            Intent intent = new Intent(TrangChuActivity.this,LoginActivity.class);
                                            startActivity(intent);
                                            break;

                                        case DialogInterface.BUTTON_NEGATIVE:
                                            break;
                                    }
                                }
                            };
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setMessage("Bạn muốn đăng xuất?").setPositiveButton("Đồng ý", dialogClickListener)
                                    .setNegativeButton("Hủy", dialogClickListener).show();
                        }
                        return true;

                }
                return false;
            }
        });

        // Khởi tạo RecyclerView.
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        navigationLeft.bringToFront();

        url2 = "ThucDon/get/category/L06";

        loadViewFlipper();

        loaiTDArrayList = getMockDataLoaiTD();
        setLoaiTDRecycler(loaiTDArrayList);


        //Luu su kien tu acvivity da chon
        navigationLeft.setNavigationItemSelectedListener(this);
        mSelectedId = savedInstanceState == null ? R.id.nagivationviewLeft : savedInstanceState.getInt(SELECTED_ITEM_ID);
        navigation(mSelectedId);

        data = new ArrayList<>();
        adaptersp = new ProductAdapter(this, data, this);
        try {
            data = LayDanhMucSP();
            if(data==null){
                Toast.makeText(TrangChuActivity.this, "Lỗi", Toast.LENGTH_SHORT);
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setSanPhamRecycler(data);

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrangChuActivity.this, GioHangActivity.class);
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

        //Xu ly search
        editText = findViewById(R.id.searchview);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
            }
        });
//        floatingActionButton.findViewById(R.id.floatBtn);
//       floatingActionButton.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               Intent intent = new Intent(TrangChuActivity.this, LocationActivity.class);
//               startActivity(intent);
//           }
//       });
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        navigationLeft = findViewById(R.id.nagivationviewLeft);
        MenuItem mnu_user = navigationLeft.getMenu().getItem(1);
        mnuLogin = mnu_user.getSubMenu().getItem(1);
        mnuTTCN = mnu_user.getSubMenu().getItem(0);
        if(mnuLogin != null) {
            invalidateOptionsMenu();
            if (loadPreferences("my_email") != null) {
                mnuTTCN.setVisible(true);
                mnuTTCN.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(TrangChuActivity.this,InfoKhachHangActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });
                mnuLogin.setTitle("Đăng xuất");
            } else {
                mnuLogin.setTitle("Đăng nhập");
                mnuTTCN.setVisible(false);
            }
            mnuLogin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (mnuLogin.getTitle().toString().equals("Đăng nhập")) {
                        Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.TrangChuActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(TrangChuActivity.this,"Đăng xuất thành công!", Toast.LENGTH_SHORT).show();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(TrangChuActivity.this, LoginActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    return false;
                }
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    // Override this method to do what you want when the menu is recreated
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setLoaiTDRecycler(ArrayList<LoaiTD> loaiTDArrayList) {
        recyclerView = findViewById(R.id.recycleView_option);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loaiTDAdapter = new LoaiTDAdapter(loaiTDArrayList, this, this);
        recyclerView.setAdapter(loaiTDAdapter);
    }

    private void setSanPhamRecycler(ArrayList<DALThucDon> tinTucArrayList) {
        recyclerView_SP = findViewById(R.id.recycleView_tintuc);
        recyclerView_SP.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        spTrangChuAdapter = new SPTrangChuAdapter( this, tinTucArrayList, this);
        recyclerView_SP.setAdapter(spTrangChuAdapter);
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
        Intent intent = null;
        if (mSelectedId == R.id.mnu_dssp) {
            ItemClick(loaiTDArrayList.get(0));

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mSelectedId == R.id.mnu_cart) {
            intent = new Intent(this, GioHangActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mSelectedId == R.id.mnu_history) {
            intent = new Intent(this, LichSuMuaHangActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if (mSelectedId == R.id.mnu_login) {
            intent = new Intent(TrangChuActivity.this, LoginActivity.class);
            startActivity(intent);

            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(mSelectedId == R.id.mnu_contact)
        {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0978154394"));
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(mSelectedId == R.id.mnu_aboutus)
        {
            intent = new Intent(this,SupportChatActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        if(mSelectedId == R.id.mnu_new)
        {
            intent = new Intent(this,TinTucActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(mSelectedId == R.id.account)
        {
            intent = new Intent(this, InfoKhachHangActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }

        if(mSelectedId == R.id.heart)
        {
            intent = new Intent(this,SPDaXemActivity.class);
            startActivity(intent);
        }

        if(mSelectedId == R.id.mnu_address)
        {
            intent = new Intent(this,LocationActivity.class);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public ArrayList<LoaiTD> getMockDataLoaiTD(){
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
        return loaiTDArrayList;
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

    @Override
    public void ItemClick(LoaiTD loaiTD) {
        Intent intent = new Intent(this,DSThucDonActivity.class);
//        intent.putExtra("key1",products);
        Common.loaidachon = loaiTD;
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);
        mSelectedId = item.getItemId();
        navigation(mSelectedId);
        return true;
    }


    public ArrayList<DALThucDon> LayDanhMucSP() throws JSONException {
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


    @Override
    public void itemClick(DALThucDon dalThucDon) {
        Intent intent = new Intent(this,ChiTietThucDonActivity.class);
        Common.thucDon = dalThucDon;
        startActivity(intent);
    }

    public String loadPreferences(String key){
        SharedPreferences p = getSharedPreferences("data", Context.MODE_PRIVATE);
        return p.getString(key,null);
    }

}