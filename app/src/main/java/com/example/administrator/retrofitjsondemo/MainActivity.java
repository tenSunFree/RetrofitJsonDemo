package com.example.administrator.retrofitjsondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private Api api;
    private Call<List<Hero>> call;
    private List<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        /** 創建Retrofit物件 */
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())                                   // 使用Gson解析
                .build();

        /** 創建要去請求API的Client Service */
        api = retrofit.create(Api.class);
        call = api.getHeros();

        /** 發出請求(異步), onResponse 成功, onFailure 失敗 */
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                /** 將取得的List<Hero> 賦予RecyclerView */
                heroes = response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));  // 这里用线性显示, 类似于listview
                recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, heroes));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
