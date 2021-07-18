package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.facebook.appevents.ml.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {
    TagGroup mTagGroup;

    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mTagGroup = findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[] {"Trà sữa", "Trà", "Bánh", "Cafe","Matcha"});

        searchView = findViewById(R.id.searchview);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                searchFurniture(newText);
                return false;
            }
        });
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



    //    SearchView searchView;
//    ArrayList<DALThucDon> arrayList;
//    Utils utils;
//    ListView listView;
//    ProductAdapter furnitureAdapter;
//    TagGroup mTagGroup;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_search);
//
////        utils = new Utils( com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.SearchActivity.this);
//        arrayList =new ArrayList<>();
//        listView = findViewById(R.id.listView);
//        furnitureAdapter = new ProductAdapter( com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.SearchActivity.this, arrayList);
//        listView.setAdapter(furnitureAdapter);
//        Log.d("FurnitureApp", utils.LoadFileInternal().size()+"");
//        searchView = findViewById(R.id.search_view);
//        searchView.setIconifiedByDefault(true);
//        searchView.setFocusable(true);
//        searchView.setIconified(false);
//        searchView.requestFocusFromTouch();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////                searchFurniture(newText);
//                return false;
//            }
//        });
//
//        mTagGroup = findViewById(R.id.tag_group);
//        mTagGroup.setTags(new String[] {"Bed", "Living", "Accessories", "Sealy","Christopher"});
//        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
//            @Override
//            public void onTagClick(String tag) {
//                searchView.setQuery(tag,false);
//                hideSoftKeyboard(searchView);
//            }
//        });
//
//    }
////    private void searchFurniture(String newText) {
////        ArrayList<Furniture> tmp = new ArrayList<>();
////        for(Furniture furniture : utils.LoadFileInternal()){
////            if(furniture.getName().toLowerCase().contains(newText.toLowerCase())){
////                tmp.add(furniture);
////            }
////        }
////        Toast.makeText(this, tmp.size()+"", Toast.LENGTH_SHORT).show();
////        if(tmp.size() > 0){
////            furnitureAdapter.clear();
////            furnitureAdapter.addAll(tmp);
////            furnitureAdapter.notifyDataSetChanged();
////            listView.setVisibility(View.VISIBLE);
////            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////                @Override
////                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
////                    Utils.furnitureHistory.add(arrayList.get(i));
////                    //Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
////
////                    Intent intent = new Intent( com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.SearchActivity.this, DetailActivity.class);
////                    intent.putExtra("furniture", arrayList.get(i));
////                    startActivity(intent);
////                }
////            });
////        }
////        if(newText.isEmpty()){
////            listView.setVisibility(View.GONE);
////        }
////    }
//    public void hideSoftKeyboard(View view){
//        InputMethodManager imm
//                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }


}