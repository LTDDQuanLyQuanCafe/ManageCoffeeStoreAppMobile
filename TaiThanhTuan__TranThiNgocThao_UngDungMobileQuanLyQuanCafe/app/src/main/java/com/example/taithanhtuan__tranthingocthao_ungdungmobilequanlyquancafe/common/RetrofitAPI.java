package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

import com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.Model.MessageModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {
    @GET
    Call<MessageModel> getMessage(@Url String url);

}
