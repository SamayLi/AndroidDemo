package com.example.retrofitdemo;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/5/15.
 */
public interface Weather {
    @GET("101010100.html")
    Observable<WeatherInfo> weather();
}
