package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.GioHangAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.DALThucDon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GioHangActivity extends AppCompatActivity {

    Button btnPay, btnContinue, btnAdd, btnMin;
    ListView lvCart;
    ArrayList<DALThucDon> datasp = new ArrayList<>();

    TextView tvThanhtien, tvNull, tvSL, tvGiasp;
    GioHangAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Giỏ hàng"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);



//        int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "Android");
//        TextView titleText = (TextView)findViewById(titleId);
//        titleText.setTextColor(Color.parseColor("#000000"));



        //anh xa
        btnPay = findViewById(R.id.btn_Cart_Pay);
        btnContinue = findViewById(R.id.btn_Cart_Continue);
//        btnAdd = findViewById(R.id.btn_Cart_AddNumber);
//        btnMin = findViewById(R.id.btn_Cart_MinNumber);
        tvNull = findViewById(R.id.lbl_Cart_notificationcart);
        lvCart = findViewById(R.id.lst_Cart);
        tvThanhtien = findViewById(R.id.tv_Cart_Total);
        cartAdapter = new GioHangAdapter(GioHangActivity.this, Common.carts);
        lvCart.setAdapter(cartAdapter);



        //Kiem tra ListView
        checkData();

        //Load thanh tien
        xuLyThanhTien();



        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), DSThucDonActivity.class);
                startActivity(intent1);

            }
        });
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvThanhtien.getText().toString().equals(0+"  VND"))
                {
                    btnPay.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Giỏ hàng bạn chưa có gì để thanh toán", Toast.LENGTH_SHORT).show();
                }

                else
                {

                    Intent intent1 = new Intent(getApplicationContext(),DSThucDonActivity.class);
                    startActivity(intent1);
                }
            }
        });


    }
    private void checkData() {
        if(Common.carts.size() <=0)
        {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.VISIBLE);
            lvCart.setVisibility(View.INVISIBLE);

        }
        else
        {
            cartAdapter.notifyDataSetChanged();
            tvNull.setVisibility(View.INVISIBLE);
            lvCart.setVisibility(View.VISIBLE);


        }
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void xuLyThanhTien() {
        try {
            datasp = DSThucDonActivity.LayDanhMucSP();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Xu ly text view Total
        if(Common.carts.size() <=0)
        {
            tvThanhtien.setText(0+"  VND");
        }
        else
        {
            int Tongtien = 0;
            for (GioHang i : Common.carts)
            {
                for (DALThucDon sp : datasp)
                {
                    if(i.idsp.equals(sp.MaThucDon))
                    {
                        int giasp = Integer.parseInt(sp.DonGia);
                        int soluongsp = i.soluong;
                        Tongtien += giasp * soluongsp;
                        tvThanhtien.setText(Tongtien + "  VND");
                        break;
                    }

                }

            }
        }
    }


}