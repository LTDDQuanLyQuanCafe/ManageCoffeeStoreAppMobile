package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;

public class InfoKhachHangActivity extends AppCompatActivity {

    EditText edtHoTen, edtDiaChi, edtMK, edtNhapLaiMK, editEmail, edtSDT;
    Button btnDangXuat, btnLuu;
    TextView txtTen, txtSDT, txtDC, txtThayDoi;
    ImageView img_Hinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_khach_hang);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);

        actionBar.setTitle("Lịch sử mua hàng"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        anhXa();

        //Gui du lieu tu home qua
        if(Common.USER.getDienThoai() == null)
        {
            Toast.makeText(InfoKhachHangActivity.this, "Bạn vui lòng đăng nhập để sử dụng tính năng này", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(InfoKhachHangActivity.this, LoginActivity.class);
            startActivity(intent);
            return;
        }
        else {
            edtHoTen.setText(Common.USER.getHoTen());
            edtDiaChi.setText(Common.USER.getDiaChi());
            edtMK.setText(Common.USER.getMatKhau());
            editEmail.setText(Common.USER.getEmail());
            edtSDT.setText(Common.USER.getDienThoai());

            txtTen.setText(Common.USER.getHoTen());
            txtSDT.setText(Common.USER.getDienThoai());
            txtDC.setText(Common.USER.getDiaChi());
        }
    }

    public void anhXa(){

        edtHoTen = findViewById(R.id.txt_nameuser);
        edtDiaChi = findViewById(R.id.txt_address);
        edtMK = findViewById(R.id.txt_password);
        edtNhapLaiMK = findViewById(R.id.txt_repass);
        editEmail = findViewById(R.id.txt_email);
        edtSDT = findViewById(R.id.txt_phone);

        btnDangXuat = findViewById(R.id.btn_User_LogOut);
        btnLuu = findViewById(R.id.btn_User_Save);

        txtTen = findViewById(R.id.lblNameUser);
        txtSDT = findViewById(R.id.lblSDTUser);
        txtDC = findViewById(R.id.lblAddress);
        txtThayDoi = findViewById(R.id.btnChangeImage);

        img_Hinh = findViewById(R.id.imageView2);
    }
}