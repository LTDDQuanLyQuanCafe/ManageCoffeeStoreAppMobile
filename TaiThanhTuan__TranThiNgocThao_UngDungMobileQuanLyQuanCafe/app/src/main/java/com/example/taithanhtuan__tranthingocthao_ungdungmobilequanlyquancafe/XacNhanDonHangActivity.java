package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.GioHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.GioHangThanhToanAdapter;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XacNhanDonHangActivity extends AppCompatActivity {

    ListView lvCartTT;
    GioHangThanhToanAdapter cartAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ParseJson parseJson = new ParseJson();
    EditText edtNoiNhan, edtGhiChu;
    TextView tvHoTen, tvSdt, tv_Cart_Total, linkDelAll;
    ImageView back2;
    Button btn_Cart_Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_don_hang);

        lvCartTT = findViewById(R.id.lst_CartTT);
        edtGhiChu = findViewById(R.id.edtGhiChu);
        edtNoiNhan = findViewById(R.id.edtDiaChi);
        tvHoTen = findViewById(R.id.tvHoTen);
        tvSdt = findViewById(R.id.tvPhone);
        tv_Cart_Total = findViewById(R.id.tv_Cart_Total);
        back2 = findViewById(R.id.back2);
        linkDelAll = findViewById(R.id.linkDelAll);
        btn_Cart_Pay = findViewById(R.id.btn_Cart_Pay);

        cartAdapter = new GioHangThanhToanAdapter(XacNhanDonHangActivity.this, Common.carts);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XacNhanDonHangActivity.this, TrangChuActivity.class);
                startActivity(intent);
            }
        });

        linkDelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.carts.clear();
                Intent intent = new Intent(XacNhanDonHangActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });

        lvCartTT.setAdapter(cartAdapter);
        double tongTien = 0;
        for (int i = 0; i < Common.carts.size(); i++) {
            tongTien += Common.carts.get(i).soluong * Double.parseDouble(Common.carts.get(i).giasp);
        }
        tv_Cart_Total.setText(converMoneyToString(tongTien) + " VND");

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        TaiKhoanKhachHang kh = new TaiKhoanKhachHang();
        String email = loadPreferences("my_email");
        if (email != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                    String url = String.format(Common.preUrl + "khachhang/get/%s", email);
                    String p = parseJson.readStringFileContent(url);
                    JSONObject response = null;
                    try {
                        response = new JSONObject(p);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (response != null) {
                        try {
                            kh.setEmail(response.getString("email"));
                            kh.setMaKH(response.getString("maKhachHang"));
                            kh.setHoTen(response.getString("tenKhachHang"));
                            kh.setDienThoai(response.getString("dienThoai"));

                            tvHoTen.setText(kh.getHoTen());
                            tvSdt.setText(kh.getDienThoai());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } else {
            Toast.makeText(XacNhanDonHangActivity.this, "Bạn vui lòng đăng nhập trước khi thanh toán.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(XacNhanDonHangActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        double finalTongTien = tongTien;
        btn_Cart_Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ngayTao = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                String diaChi = "";
                String ghiChu = "";
                if (!edtNoiNhan.getText().toString().equals("")) {
                    diaChi = edtNoiNhan.getText().toString();
                    if (!edtGhiChu.getText().toString().equals("")) {
                        ghiChu = edtGhiChu.getText().toString();
                    }
                    String jsonInput = String.format("{" +
                            "    'MaKhachHang': %s," +
                            "    'TongTien': %f,"+
                            "    'DiaChiGiao': '%s'," +
                            "    'GhiChu':'%s'" +
                            "}", kh.getMaKH(), finalTongTien, ngayTao, diaChi, ghiChu);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = Common.preUrl + "DonGiaoHang/create/";
                            _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                            String str = parseJson.postObjectToDB(url, jsonInput);
                            if (str != null) {
                                for(int i =0; i<Common.carts.size();i++) {
                                    GioHang dgh = Common.carts.get(i);
                                    String url2 = Common.preUrl + "DonGiaoHang/create/details/"+str;
                                    String jsonInputDetails = String.format(
                                            "{    'MaGiaoHang': %s,    'MaThucDon': %s,    'SLGiao': '1',    'ThanhTien': 20000}"
                                            ,str, dgh.getIdsp(), dgh.getSoluong(),(dgh.getSoluong()*Double.parseDouble(dgh.getGiasp())));
                                    Boolean checkDetail = Boolean.valueOf(parseJson.postObjectToDB(url2, jsonInputDetails));
                                    if(!checkDetail){
                                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast toast = Toast.makeText(XacNhanDonHangActivity.this, "Tạo chi tiết đơn giao hàng thất bại.", Toast.LENGTH_SHORT);
                                                toast.show();
                                            }
                                        });
                                    }
                                }
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(XacNhanDonHangActivity.this, "Chúc mừng bạn vừa mua hàng thành công.", Toast.LENGTH_SHORT);
                                        toast.show();
                                        // Show lich su mua hang

                                    }
                                });
                            }
                        }
                    }).start();
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast toast = Toast.makeText(XacNhanDonHangActivity.this, "Bạn vui lòng bổ sung thêm thông tin nơi nhận hàng.", Toast.LENGTH_SHORT);
                            toast.show();
                            return;
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(XacNhanDonHangActivity.this, TrangChuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public String loadPreferences(String key) {
        SharedPreferences p = getSharedPreferences("data", Context.MODE_PRIVATE);
        return p.getString(key, null);
    }


    private String converMoneyToString(double money) {
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(money);
        return formattedNumber;
    }
}