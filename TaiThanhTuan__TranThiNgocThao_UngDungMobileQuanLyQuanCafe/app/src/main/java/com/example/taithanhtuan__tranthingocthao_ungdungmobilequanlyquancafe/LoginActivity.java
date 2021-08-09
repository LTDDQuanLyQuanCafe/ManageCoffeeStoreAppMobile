package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.Common;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.dal.TaiKhoanKhachHang;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson.ParseJson;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.processJson._HttpsTrustManager;

import org.json.JSONObject;


import static com.facebook.FacebookSdk.sdkInitialize;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    public TaiKhoanKhachHang _taiKhoan;
    EditText edtName,edtPass;
    TextView linkSU,linkForget;
    Button btnLogin;
    LoginButton btnLoginFacebook;
    CallbackManager callbackManager;
    ParseJson parseJson = new ParseJson();
    Bundle param;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName=findViewById(R.id.editTextUserName);
        edtPass=findViewById(R.id.editTextPassword);
        sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        _taiKhoan = new TaiKhoanKhachHang();
        btnLogin =findViewById(R.id.btnLogin);
        btnLoginFacebook = findViewById(R.id.btnFacebook);
        btnLoginFacebook.setReadPermissions("email");

        linkForget = findViewById(R.id.linkForget);
        linkForget.setTextColor(Color.BLUE);
        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        edtName.setFocusable(true);
        linkSU = findViewById(R.id.linkSignUp);
        linkSU.setTextColor(Color.BLUE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public boolean onSupportNavigateUp() {
//        hideSoftKeyboard(searchView);
        Intent intent = new Intent(LoginActivity.this, TrangChuActivity.class);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

    private void getFbInfomation(){
        if(AccessToken.getCurrentAccessToken()!=null) {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            if (object != null) {
                                Log.i("Login: ", object.optString("name"));
                                Log.i("ID: ", object.optString("id"));
                                Toast.makeText(LoginActivity.this, "Name: " + object.optString("name"), Toast.LENGTH_SHORT).show();
                                _taiKhoan.setEmail(object.optString("email"));
                                _taiKhoan.setHoTen(object.optString("name"));
                                _taiKhoan.setTenTaiKhoan(object.optString("id"));
                            }
                        }
                    });
            param = new Bundle();
            param.putString("fields","id,name,link,email");
            request.setParameters(param);
            request.executeAsync();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
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
                if (edtName.getText().toString().equals("") || edtPass.getText().toString().equals("")) {
                    Log.d(TAG, "======Not enougn======");
                    Log.e(TAG, "User name or password be empty.");
                    Toast.makeText(LoginActivity.this, "Not be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Do network action in this function
                            _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                            String userName = edtName.getText().toString();
                            String password = edtPass.getText().toString();

                            String url = String.format(Common.preUrl + "TaiKhoanKhachHang/login?tenTaiKhoan=%s&matKhau=%s", userName, password);
                            String p = parseJson.readStringFileContent(url);
                            if (Boolean.valueOf(p) == true) {
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                url = String.format((Common.preUrl + "TaiKhoanKhachHang/get-email/%s"), userName);
                                editor.putString("my_email", parseJson.readStringFileContent(url));
                                editor.commit();
                                String t = parseJson.readStringFileContent(url);
                                Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, TrangChuActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d(TAG, "======Login be not success======");
                                Log.e(TAG, "Account login is not success.");
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Login be not success.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });

        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "======Facebook login success======");
//                Toast.makeText(LoginActivity.this, "Login Facebook success.", Toast.LENGTH_SHORT).show();
                getFbInfomation();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String r = _taiKhoan.getEmail();
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String url = String.format(Common.preUrl+"taikhoankhachhang/check/email/%s", _taiKhoan.getEmail());
                        String p = parseJson.readStringFileContent(url);
                        if(p.equals("true")){
                            Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, TrangChuActivity.class);
                             SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("my_email", _taiKhoan.getEmail());
                            editor.commit();
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                            intent.putExtra("Name",_taiKhoan.getHoTen());
                            intent.putExtra("Id",_taiKhoan.getTenTaiKhoan());
                            intent.putExtra("Email",_taiKhoan.getEmail());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("my_email", _taiKhoan.getEmail());

                            editor.commit();
                            startActivityForResult(intent,0);
                        }
                    }
                }).start();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Login Facebook cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "======Facebook login error======");
                Log.e(TAG, "Error: " + error.toString());
                Toast.makeText(LoginActivity.this, "Login Facebook error.", Toast.LENGTH_SHORT).show();
            }
        });

        linkForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, ForgetPass.class);
                startActivity(intent);
            }
        });
        linkSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
