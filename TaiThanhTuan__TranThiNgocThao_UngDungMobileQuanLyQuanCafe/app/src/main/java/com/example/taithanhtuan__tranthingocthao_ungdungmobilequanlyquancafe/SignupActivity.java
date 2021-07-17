package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();
    TextView linkReLI;
    EditText edtHoTen,edtPhone,edtMK,edtReMK;
    TaiKhoanKhachHang _TaiKhoan;
    Button btnRegister;
    ParseJson parseJson ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        linkReLI = findViewById(R.id.linkReturnLogIn);
        linkReLI.setTextColor(Color.BLUE);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtHoTen.findFocus();
        edtPhone = findViewById(R.id.edtPhone);
        edtMK = findViewById(R.id.edtPass);
        edtReMK = findViewById(R.id.edtRePass);
        btnRegister = findViewById(R.id.btnRegister);
        parseJson = new ParseJson();
        try {
            Bundle extras = getIntent().getExtras();
            if (extras != null)
                edtHoTen.setText(extras.getString("Name"));
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
                String fullName ="",phoneNumber="",mk="",reMK="";
                fullName= edtHoTen.getText().toString();
                phoneNumber = edtPhone.getText().toString();
                mk = edtMK.getText().toString();
                reMK =  edtReMK.getText().toString();
                checkHelper(fullName,phoneNumber,mk,reMK);

                String url = String.format(Common.preUrl + "KhachHang/check/%s/%s", "tttuan272000@gmail.com", phoneNumber);
                final Boolean[] flag = {true};
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        _HttpsTrustManager.HttpsTrustManager.allowAllSSL();
                        String result = parseJson.readStringFileContent(url);
                        if (result.equals("true")){
                            Log.d(TAG,"======Account information be exist======");
                            Log.e(TAG, "This information has been another user use. Please use other information.");
                            Toast.makeText(SignupActivity.this, "Account information exist.", Toast.LENGTH_SHORT).show();
                            flag[0] = false;
                        }
                    }
                }).start();
                if(flag[0]==true){

                }
            }
        });
    }
        private boolean checkHelper(String fullName, String phoneNumber, String mk, String reMK){
            if(fullName.equals("") || phoneNumber.equals("") || mk.equals("") || reMK.equals(""))
            {
                Log.d(TAG,"======Information be empty======");
                Log.e(TAG, "Information of user be not empty");
                Toast.makeText(SignupActivity.this, "Information not enough.", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!Helper.isFullname(fullName)){
                Log.d(TAG,"======Fullname invalid======");
                Log.e(TAG, String.format("User Name %s is not valid. User name contains only alphabetic characters a->z.",fullName));
                Toast.makeText(SignupActivity.this, "Fullname invalid.", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(fullName.length()>50){
                Log.d(TAG,"======Fullname Length over max======");
                Log.e(TAG, String.format("User Name %s exceed the maximum allowed character length. User name length maximum of only 50 characters.",fullName));
                Toast.makeText(SignupActivity.this, "Fullname Length over max.", Toast.LENGTH_SHORT).show();
                return false;
            }
            if(!Helper.isPhoneNumber(phoneNumber)){
                Log.d(TAG,"======PhoneNumber invalid======");
                Log.e(TAG, String.format("Phone number %s is not valid. Phone number should be phone number VietNam.",phoneNumber));
                Toast.makeText(SignupActivity.this, "PhoneNumber invalid.", Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;
        }

}