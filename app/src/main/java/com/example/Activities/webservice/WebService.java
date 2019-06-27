package com.example.Activities.webservice;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    private static Retrofit r;

    public static Retrofit set(String URLBase) {
        if(r== null){
            r=new Retrofit.Builder()
                    .baseUrl(URLBase)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return r;
    }
    public static Retrofit get() {
        if(r== null){
            throw  new RuntimeException("URLBase no declarada");
        }
        return r;
    }

}

