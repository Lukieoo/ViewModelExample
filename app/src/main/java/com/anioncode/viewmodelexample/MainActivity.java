package com.anioncode.viewmodelexample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.StrictMode;

import com.anioncode.viewmodelexample.ApiConnect.ModelNews;
import com.anioncode.viewmodelexample.ApiConnect.ReturnResponse;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView NewsRecycler;
    NewsAdapter adapter;
    NewsViewModel model;
    public static SwipeRefreshLayout swipeRefreshLayoutNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        swipeRefreshLayoutNews = findViewById(R.id.swipe_layout);
        swipeRefreshLayoutNews.setRefreshing(true);
        NewsRecycler = findViewById(R.id.NewsRecycler);
        NewsRecycler.setHasFixedSize(true);
        NewsRecycler.setLayoutManager(new LinearLayoutManager(this));
        model = ViewModelProviders.of(this).get(NewsViewModel.class);

        model.getHeroes().observe(this, new Observer<List<ModelNews>>() {
            @Override
            public void onChanged(@Nullable List<ModelNews> heroList) {
                adapter = new NewsAdapter(MainActivity.this, heroList);

//        ReturnResponse response=new ReturnResponse();
//        response.requestInit();
//        response.getDataApi();
            }
        });

        swipeRefreshLayoutNews.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutNews.setRefreshing(true);
                model.loadHeroes();
            }
        });
        swipeRefreshLayoutNews.post(new Runnable() {

            @Override
            public void run() {

                swipeRefreshLayoutNews.setRefreshing(true);

                model.loadHeroes();
            }
        });
        NewsRecycler.setAdapter(adapter);
    }
}