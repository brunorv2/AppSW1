package com.example.Activities.Login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface Login {


    @FormUrlEncoded
    @POST("/AppLogin")
    public  Call<LoginData> LoginFun(
            @Field("email") String email,
            @Field("password") String password
    );



}
