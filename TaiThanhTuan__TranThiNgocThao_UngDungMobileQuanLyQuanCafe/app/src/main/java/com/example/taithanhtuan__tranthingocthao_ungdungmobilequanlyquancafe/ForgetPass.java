package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.send_gmail.GMail;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.send_gmail.SendMailTask;
import com.facebook.appevents.ml.Utils;

import java.io.Console;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Properties;
public class ForgetPass extends AppCompatActivity {

    EditText edtEmail,edtVerify,edtNewPass,edtReNewPass;
    Button btnSend,btnConfirm;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtEmail = findViewById(R.id.edtEmailSend);
        edtVerify = findViewById(R.id.edtVerifyMail);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnSend = findViewById(R.id.btnSendMail);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtReNewPass = findViewById(R.id.edtReNewPass);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("SendMailActivity", "Send Button Clicked.");

                String fromEmail = ("tttuan2703@gmail.com");
                String fromPassword = ("0129885916");
                String toEmails = edtEmail.getText().toString();
                List<String> toEmailList = Arrays.asList(toEmails
                        .split("\\s*,\\s*"));
                Log.i("SendMailActivity", "To List: " + toEmailList);
                String emailSubject = "Gửi mã xác nhận";
                String emailBody = "Cửa hàng Coffee Store gửi mã nhận quên mật khẩu tới email của bạn.";
                new SendMailTask(ForgetPass.this).execute(fromEmail,
                        fromPassword, toEmailList, emailSubject, emailBody);

                // TODO - prompts email client only
                sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

//                editor.putString("code_verify", codeVerify);
                editor.commit();

//                startActivity(sendEmail);
                edtVerify.setEnabled(true);
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