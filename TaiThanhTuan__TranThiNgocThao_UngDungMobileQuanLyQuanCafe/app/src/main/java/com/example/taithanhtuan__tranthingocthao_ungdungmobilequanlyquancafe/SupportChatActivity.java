package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.ChatsModel;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.MessageModel;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.adapter.ChatAdapter_RecycleView;
import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common.RetrofitAPI;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupportChatActivity extends AppCompatActivity {
    RecyclerView chatsRV;
    EditText userMsgEdit;
    TextView textView;
    FloatingActionButton sendMsgFAB;
    final  String BOT_KEY = "bot";
    final  String USER_KEY = "user";
    ArrayList<ChatsModel> chatsModelsArrayList;
    ChatAdapter_RecycleView chatAdapter_recycleView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent intent = new Intent(SupportChatActivity.this, TrangChuActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_chat);

        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        //thanh tro ve home
        actionBar.setDisplayHomeAsUpEnabled(true);

        //doi mau thanh action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EA8734"));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Hỗ trợ khách hàng"); //Thiết lập tiêu đề
        //Doi mau
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);


        //Anh xa
        chatsRV = findViewById(R.id.idRVChats);
        userMsgEdit = findViewById(R.id.idEditMessage);
        sendMsgFAB = findViewById(R.id.idFABSend);
        textView = findViewById(R.id.tv_BotChao);

        chatsModelsArrayList = new ArrayList<>();
        chatAdapter_recycleView = new ChatAdapter_RecycleView(chatsModelsArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatAdapter_recycleView);

        sendMsgFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMsgEdit.getText().toString().isEmpty())
                {
                    Toast.makeText(SupportChatActivity.this, "Please enter your message", Toast.LENGTH_SHORT).show();
                    return;
                }
                textView.setVisibility(View.GONE);
                getRespone(userMsgEdit.getText().toString());
                userMsgEdit.setText("");

            }
        });
    }
    private void getRespone(String message)
    {
        chatsModelsArrayList.add(new ChatsModel(message,USER_KEY));
        chatAdapter_recycleView.notifyDataSetChanged();
        String url ="http://api.brainshop.ai/get?bid=157880&key=MTwipFYfsqMxL6Xn&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<MessageModel> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if(response.isSuccessful())
                {
                    MessageModel messageModel =  response.body();
                    chatsModelsArrayList.add(new ChatsModel(messageModel.getCnt(),BOT_KEY));
                    chatAdapter_recycleView.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                chatsModelsArrayList.add(new ChatsModel("Please revert your question",BOT_KEY));
                chatAdapter_recycleView.notifyDataSetChanged();
            }
        });
    }
}