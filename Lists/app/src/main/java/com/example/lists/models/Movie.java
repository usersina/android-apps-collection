package com.example.lists.models;

public class Movie {
    String title;
    int imgUrl;

    public Movie(String title, int imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public int getImgUrl() {
        return imgUrl;
    }
}
