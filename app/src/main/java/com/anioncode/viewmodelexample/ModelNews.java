package com.anioncode.viewmodelexample;

public class ModelNews {

    String title ;
    String thumbnail;
    String date;

    public ModelNews(String title, String thumbnail, String date) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
