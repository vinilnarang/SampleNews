package com.vinilnarang.news.Models;

/**
 * Created by vinilnarang on 2/24/18.
 */

public class Source {
    String id;
    String name;

    public Source(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
