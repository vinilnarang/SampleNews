package com.vinilnarang.news;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vinilnarang.news.API.GeneralAPI;
import com.vinilnarang.news.Adapters.NewsAdapter;
import com.vinilnarang.news.Models.NewsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button loadButton;
    private TextView loadingTextView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadButton = (Button) findViewById(R.id.load_news);
        loadingTextView = (TextView) findViewById(R.id.loading_news);
        recyclerView = (RecyclerView) findViewById(R.id.news_view);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryLoadingNews();
            }
        });
    }

    private void tryLoadingNews(){
        if(isNetworkConnected()){
            loadNews();
        }else{
            Toast.makeText(this, "Please make sure you're connected to internet.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null && networkInfo.isConnected();
    }

    private void loadNews(){
        loadButton.setVisibility(View.GONE);
        loadingTextView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://api.myjson.com/bins/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        GeneralAPI generalAPI = retrofit.create(GeneralAPI.class);
        Call<NewsResponse> loadNewsCall = generalAPI.loadNews();
        loadNewsCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if(!response.isSuccessful()){
                    loadingTextView.setText("Some error occurred. Please try again later.");
                    return;
                }
                NewsResponse news = response.body();
                if("ok".equals(news.getStatus())){
                    loadingTextView.setVisibility(View.GONE);
                    NewsAdapter newsAdapter = new NewsAdapter(MainActivity.this, news.getArticleList());
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(newsAdapter);
                    recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, LinearLayoutManager.VERTICAL));
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                loadingTextView.setText("Some error occurred. Please try again later.");
            }
        });
    }
}
