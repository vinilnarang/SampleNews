package com.vinilnarang.news.API;

import com.vinilnarang.news.Models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vinilnarang on 2/25/18.
 */

public interface GeneralAPI {

    @GET("d7qol")
    Call<NewsResponse> loadNews();

}
