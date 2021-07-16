package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    public static TaiKhoanKhachHang _taiKhoan;
    EditText edtName,edtPass;
    TextView linkSU;
    Button btnLogin;
    LoginButton btnLoginFacebook;
    CallbackManager callbackManager;
    ParseJson parseJson = new ParseJson();
    Bundle param;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtName=findViewById(R.id.editTextUserName);
        edtPass=findViewById(R.id.editTextPassword);
        sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        btnLogin =findViewById(R.id.btnLogin);
        btnLoginFacebook = findViewById(R.id.btnFacebook);
        btnLoginFacebook.setReadPermissions("email");


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

        btnLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "======Facebook login success======");
                Log.d(TAG, "Facebook Access Token: " + loginResult.getAccessToken().getToken());
                Toast.makeText(LoginActivity.this, "Login Facebook success.", Toast.LENGTH_SHORT).show();
                    getFbInfomation();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String email = param.getString("my_email");
                        String url = String.format("https://192.168.1.9:5566/api/taikhoankhachhang/check/email/%s",email);
                        String p = parseJson.readStringFileContent(url);
                        if(p.equals("true")){
                            Intent intent = new Intent(com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.LoginActivity.this, SignupActivity.class);
                            startActivity(intent);
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
                                Toast.makeText(LoginActivity.this, "ID: " + object.optString("id"), Toast.LENGTH_SHORT).show();
                                param.putString("my_email",object.optString("email"));
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