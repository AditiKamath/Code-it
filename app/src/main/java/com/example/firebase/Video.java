package com.example.firebase;

public class Video {
    private String title;
    private String url;

    private Video() {

    }

    public Video(String title, String url) {
        if (title.trim().equals("")) {
            title= "not available";
        }
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
