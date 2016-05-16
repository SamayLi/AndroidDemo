package com.example.retrofitdemo.utils;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shaohua.li on 5/16/16.
 */
public class Network {
    private static ZhuangbiApi zhuangbiApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhuangbiApi getZhuangbiApi(){
        if(zhuangbiApi==null){
            Retrofit retrofit=new Retrofit.Builder()
                                .client(okHttpClient)
                                .baseUrl("http://zhuangbi.info/")
                                .addConverterFactory(gsonConverterFactory)
                                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                                .build();
            zhuangbiApi=retrofit.create(ZhuangbiApi.class);
        }
        return zhuangbiApi;
    }
}
