package com.vinilnarang.news.Models;

import java.util.List;

/**
 * Created by vinilnarang on 2/24/18.
 */

public class NewsResponse {
    String status;
    int totalResults;
    List<Article> articles;

    public NewsResponse(String status, int totalResults, List<Article> articleList) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articleList;
    }

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticleList() {
        return articles;
    }
}
