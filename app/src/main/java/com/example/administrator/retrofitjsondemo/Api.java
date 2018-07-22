package com.example.administrator.retrofitjsondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


/** 定义一个接口类Api, 以及请求方法getHeros, 通过这个方法来获取返回的JSON数据 */
public interface Api {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    /**
     * 返回一個Call類別
     */
    @GET("marvel")
    Call<List<Hero>> getHeros();
}
