package com.example.lists.models;

public class Movie {
    String title;
    int imgUrl;

    public Movie(String title, int imgUrl) {
        extracted(title);
        this.imgUrl = imgUrl;
    }

    private void extracted(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getImgUrl() {
        return imgUrl;
    }
}
