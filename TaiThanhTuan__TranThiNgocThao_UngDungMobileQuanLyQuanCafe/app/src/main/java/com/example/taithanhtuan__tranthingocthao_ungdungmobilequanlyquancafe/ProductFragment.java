package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ProductAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    ListView listView;
    ArrayList<DALThucDon> arrayList;
    ProductAdapter productAdapter;
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.listView_Product);

    }

//    private void getAllThucDon(){
//        ParseJson parseJson = new ParseJson();
//        String url = Common.preUrl+"ThucDon/All";
//        try {
//            _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
//            String p = parseJson.readStringFileContent(url);
//            JSONObject json = new JSONObject(p);
//            JSONArray arrayMa = json.getJSONArray("MaThucDon");
//            for(int i =0 ; i<arrayMa.length();i++){
//                DALThucDon thucDon = new DALThucDon();
//                thucDon.setMaThucDon(Integer.parseInt(arrayMa.get(i).toString()));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
}