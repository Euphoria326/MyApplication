package com.example.myapplication.javabean;

public class List_item {
    private String title;
    private String artist;
    private String count;
    public List_item(String count, String title, String artist) {
        this.count = count;
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
