package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

public class LoginActivity extends AppCompatActivity {

    EditText edtName,edtPass;
    TextView linkSU;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName=findViewById(R.id.editTextUserName);
        edtPass=findViewById(R.id.editTextPassword);
        btnLogin =findViewById(R.id.btnLogin);
        edtName.setFocusable(true);
        linkSU = findViewById(R.id.linkSignUp);
        linkSU.setTextColor(Color.BLUE);
        linkSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseJson parseJson = new ParseJson();
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        // Do network action in this function
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String userName = edtName.getText().toString();
                        String password = edtPass.getText().toString();
                        String url = String.format("https://192.168.1.9:5566/api/TaiKhoanKhachHang/login?tenTaiKhoan=%s&matKhau=%s",userName,password);
                        String p = parseJson.readStringFileContent(url);
                        if(p.equals("true")){
                            Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, SignupActivity.class);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }
}