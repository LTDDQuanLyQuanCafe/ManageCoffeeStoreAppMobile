package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.OnClickListener;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity implements OnClickListener {
    TagGroup mTagGroup;
    SearchView searchView;

    ArrayList<DALThucDon> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ImageView img_NoPro;
    String  url2 =  "ThucDon/all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        mTagGroup = findViewById(R.id.tag_group);
        img_NoPro = findViewById(R.id.img_NoProduct);
        mTagGroup.setTags(new String[] {"Trà sữa", "Trà", "Bánh", "Cà phê","Matcha", "Trái cây", "Đào", "Bánh mì"});

        img_NoPro = findViewById(R.id.img_NoProduct);
        searchView = findViewById(R.id.searchview);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        try {
            arrayList = LayDanhMucSP();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.listView);
        productAdapter = new ProductAdapter(this, arrayList,this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!= ""){
                    productAdapter.getFilter().filter(query);
                    if(productAdapter.getItemCount() > 0){
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
                if(query.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!= ""){
                    productAdapter.getFilter().filter(newText);
                    if(productAdapter.getItemCount() > 0){
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                }
                if(newText.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                }
                return false;
            }
        });
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                if(productAdapter.getItemCount() > 0){
                    recyclerView.setVisibility(View.VISIBLE);
                }
                hideSoftKeyboard(searchView);
            }
        });


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
                        arrayList.add(new DALThucDon(jsonObject.getString("maThucDon"), jsonObject.getString("tenMon"), jsonObject.getString("donGia"), jsonObject.getString("dvt"), jsonObject.getString("hinhAnh"), jsonObject.getString("moTa").trim(), jsonObject.getString("maLoaiTD")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                productAdapter.notifyDataSetChanged();
            }
        }).start();
        return arrayList;
    }

    private void getThucDonData(ArrayList<DALThucDon> dalThucDonList) {
        recyclerView = findViewById(R.id.listView);
        productAdapter = new ProductAdapter(this, dalThucDonList, this);
        productAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(productAdapter);
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    public void itemClick(DALThucDon dalThucDon) {
        Intent intent = new Intent(this,ChiTietThucDonActivity.class);
        Common.thucDon = dalThucDon;
        startActivity(intent);
    }
}