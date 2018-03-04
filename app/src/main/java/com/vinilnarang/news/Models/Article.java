package com.vinilnarang.news.Models;

/**
 * Created by vinilnarang on 2/24/18.
 */

public class Article {
    private String author, title, description, url, urlToImage, publishedAt;
    private Source source;

    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt, Source source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getSourceId() {
        return source.getId();
    }

    public String getSourceName(){
        return source.getName();
    }
}
