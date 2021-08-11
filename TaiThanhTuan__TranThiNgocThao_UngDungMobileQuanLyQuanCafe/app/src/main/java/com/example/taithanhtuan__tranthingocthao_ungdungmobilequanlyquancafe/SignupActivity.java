package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Helper;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();
    TextView linkReLI;
    EditText edtHoTen,edtPhone,edtMK,edtReMK,edtEmail;
    TaiKhoanKhachHang _taiKhoan;
    Button btnRegister;
    ParseJson parseJson ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        linkReLI = findViewById(R.id.linkReturnLogIn);
        linkReLI.setTextColor(Color.BLUE);
        edtHoTen = findViewById(R.id.tvHoTen);
        edtHoTen.findFocus();
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail =findViewById(R.id.edtEmail);
        edtMK = findViewById(R.id.edtPass);
        edtReMK = findViewById(R.id.edtRePass);
        btnRegister = findViewById(R.id.btnRegister);
        _taiKhoan = new TaiKhoanKhachHang();
        parseJson = new ParseJson();
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String t =extras.getString("Name");
                edtHoTen.setText(extras.getString("Name"));
                _taiKhoan.setEmail(extras.getString("Email"));
                edtEmail.setEnabled(false);
                edtEmail.setText(extras.getString("Email"));
                _taiKhoan.setTenTaiKhoan(extras.getString("Email"));
            }
        }catch (Exception ex){
            Log.d(TAG,ex.getMessage());
        }
        linkReLI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String fullName = "", phoneNumber = "", mk = "", reMK = "";
                        fullName = edtHoTen.getText().toString();
                        phoneNumber = edtPhone.getText().toString();
                        mk = edtMK.getText().toString();
                        reMK = edtReMK.getText().toString();
                        if (checkHelper(fullName, phoneNumber, mk, reMK)) {
                            final Toast toast;
                            String url = String.format(Common.preUrl + "KhachHang/check/%s/%s", _taiKhoan.getEmail(), phoneNumber);
                            _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                            String result = parseJson.readStringFileContent(url);
                            if (result.equals("true")) {
                                Log.d(TAG, "======Account information be exist======");
                                Log.e(TAG, "This information use email or phone number has been another user use. Please use other information.");

                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(SignupActivity.this, "This information use email or phone number has been another user use.", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                            } else {
                                _taiKhoan.setHoTen(fullName);
                                _taiKhoan.setDienThoai(phoneNumber);
                                _taiKhoan.setEmail(edtEmail.getText().toString());
                                url = Common.preUrl + "KhachHang/";
                                _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                                String jsonIns = "{'HoTen': '"+_taiKhoan.getHoTen()+"', 'DienThoai': '"+_taiKhoan.getDienThoai()+"', 'Email': '"+_taiKhoan.getEmail()+"'}";
                                Boolean checkIns = Boolean.valueOf(parseJson.postObjectToDB(url+"create",jsonIns));
                                if (checkIns.equals(true)) {
                                    //Get new KhachHangID
                                    url = String.format(Common.preUrl + "KhachHang/get-id/%s",_taiKhoan.getDienThoai());
                                    String maKH = parseJson.readStringFileContent(url);
                                    String ngayTao = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
                                    _taiKhoan.setMaKH(maKH);
                                    _taiKhoan.setMatKhau(mk);
                                    _taiKhoan.setNgayTao(ngayTao);
                                    url = Common.preUrl +"KhachHang/update/";
                                    String jsonQuery = String.format("{'MaKH': '%s', 'TenTaiKhoan': '%s', 'matKhau': '%s', 'ngayTao' : '%s'}",_taiKhoan.getMaKH(),_taiKhoan.getTenTaiKhoan(),_taiKhoan.getMatKhau(),_taiKhoan.getNgayTao());
                                    parseJson.postObjectToDB(url,jsonQuery);

                                    Log.d(TAG, "Congratulations on your successful account registration");
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast toast = Toast.makeText(SignupActivity.this, "Register success.", Toast.LENGTH_SHORT);
                                            toast.show();
                                        }
                                    });
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    }
                }).start();
            }
        });
    }

    private boolean checkHelper(String fullName, String phoneNumber, String mk, String reMK){
            if(fullName.equals("") || phoneNumber.equals("") || mk.equals("") || reMK.equals(""))
            {
                Log.d(TAG,"======Information be empty======");
                Log.e(TAG, "Information of user be not empty");

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SignupActivity.this, "Information not enough.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            if(!Helper.isFullname(fullName)){
                Log.d(TAG,"======Fullname invalid======");
                Log.e(TAG, String.format("User Name %s is not valid. User name contains only alphabetic characters a->z.",fullName));

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SignupActivity.this, "Fullname invalid.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            if(fullName.length()>50){
                Log.d(TAG,"======Fullname Length over max======");
                Log.e(TAG, String.format("User Name %s exceed the maximum allowed character length. User name length maximum of only 50 characters.",fullName));

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SignupActivity.this, "Fullname Length over max.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            if(!Helper.isPhoneNumber(phoneNumber)){
                Log.d(TAG,"======PhoneNumber invalid======");
                Log.e(TAG, String.format("Phone number %s is not valid. Phone number should be phone number VietNam.",phoneNumber));
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast= Toast.makeText(SignupActivity.this, "PhoneNumber invalid.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            if(!Helper.isPassword(mk)){
                Log.d(TAG,"======Password invalid======");
                Log.e(TAG, String.format("Password %s is not valid. Password include characters: aA->zZ, special characters, number and minimum length is 8.",mk));
//                toast = Toast.makeText(SignupActivity.this, "Password invalid.", Toast.LENGTH_SHORT);
//                toast.show();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SignupActivity.this, "Password invalid.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            if(!mk.equals(reMK)){
                Log.d(TAG,"======RePassword not match======");
                Log.e(TAG, "RePassword unlike does not match the password you just entered.");

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(SignupActivity.this, "RePassword not match.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                return false;
            }
            return true;
        }

}