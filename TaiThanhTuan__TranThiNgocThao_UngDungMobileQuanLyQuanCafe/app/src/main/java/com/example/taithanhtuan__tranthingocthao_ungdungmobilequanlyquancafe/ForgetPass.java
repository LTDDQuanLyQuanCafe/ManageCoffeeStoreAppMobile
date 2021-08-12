package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Helper;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

public class ForgetPass extends AppCompatActivity {
    private static final String TAG = ForgetPass.class.getSimpleName();

    EditText edtHT, edtPhoneSend, edtNewPass, edtReNewPass;
    Button btnSend, btnConfirm;
    SharedPreferences sharedPreferences;
    ParseJson parseJson = new ParseJson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtHT = findViewById(R.id.edtHT);
        edtPhoneSend = findViewById(R.id.edtPhoneSend);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnSend = findViewById(R.id.btnSendMail);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtReNewPass = findViewById(R.id.edtReNewPass);
        final String[] p = {""};
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Gửi yêu cầu xác nhận thông tin", "Quên mật khẩu.");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String ht = edtHT.getText().toString();
                        String phone = edtPhoneSend.getText().toString();
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String url = String.format(Common.preUrl + "KhachHang/check/forgot/%s/%s", ht, phone);
                        p[0] = parseJson.readStringFileContent(url);
                    }
                }).start();
                if (p[0] == null && p[0].equals("")) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ForgetPass.this, "Thông tin không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    btnConfirm.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.GONE);
                    edtNewPass.setVisibility(View.VISIBLE);
                    edtReNewPass.setVisibility(View.VISIBLE);
                    edtHT.setVisibility(View.GONE);
                    edtPhoneSend.setVisibility(View.GONE);
                }

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (p[0] != null) {
                    Log.i("Xác nhận yêu cầu hợp lệ!", "Quên mật khẩu.");
                    String mk = edtNewPass.getText().toString();
                    String reMK = edtReNewPass.getText().toString();
                    if (!Helper.isPassword(mk)) {
                        Log.d(TAG, "======Password invalid======");
                        Log.e(TAG, String.format("Password %s is not valid. Password include characters: aA->zZ, special characters, number and minimum length is 8.", mk));
//                toast = Toast.makeText(SignupActivity.this, "Password invalid.", Toast.LENGTH_SHORT);
//                toast.show();
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(ForgetPass.this, "Password invalid.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                        return;
                    }
                    if (!mk.equals(reMK)) {
                        Log.d(TAG, "======RePassword not match======");
                        Log.e(TAG, "RePassword unlike does not match the password you just entered.");

                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast toast = Toast.makeText(ForgetPass.this, "RePassword not match.", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                        return;
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String url = String.format(Common.preUrl + "KhachHang/update/pass/%s/%s", p[0], mk);
                            Boolean b = Boolean.valueOf(parseJson.postObjectToDB(url, " "));
                            if (b == true) {
                                Intent intent = new Intent(ForgetPass.this, LoginActivity.class);
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast toast = Toast.makeText(ForgetPass.this, "Mật khẩu cập nhật thay đổi thành công. Mời bạn qua đăng nhập lại.", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                });
                                startActivity(intent);
                            }
                        }
                    }).start();
                }
            }
        });

    }

    public boolean onSupportNavigateUp() {
//        hideSoftKeyboard(searchView);
        Intent intent = new Intent(ForgetPass.this, LoginActivity.class);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

    public String RandomStringVerify(){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            str.append((char) (i + 65));
            str.append(Math.round(Math.random()*9));
            str.append((char) (i + 97));
        }
        return str.toString();
    }

//    private Boolean checkRight(String oldPass, String newPass, String email){
//
//    }
}