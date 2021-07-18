package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPass extends AppCompatActivity {

    EditText edtOldPass,edtNewPass,edtEmail,edtVerify;
    Button btnSend,btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtOldPass = findViewById(R.id.edtOldPass);
        edtOldPass.findFocus();
        edtNewPass = findViewById(R.id.edtNewPass);
        edtEmail = findViewById(R.id.edtEmailSend);
        edtVerify = findViewById(R.id.edtVerifyMail);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnSend = findViewById(R.id.btnSendMail);



    }

    public boolean onSupportNavigateUp() {
//        hideSoftKeyboard(searchView);
        Intent intent = new Intent(ForgetPass.this, LoginActivity.class);
        startActivity(intent);
        finish();
        return super.onSupportNavigateUp();
    }

//    private Boolean checkRight(String oldPass, String newPass, String email){
//
//    }
}