package com.example.retrofitdemo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/5/15.
 */
public class Manager {
    static Retrofit providerRestAdapter(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/data/sk/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return  retrofit;
    }

    static Weather proviceWeather(){
        return providerRestAdapter().create(Weather.class);
    }
}
