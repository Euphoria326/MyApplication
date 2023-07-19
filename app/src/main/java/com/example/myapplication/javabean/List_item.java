package com.example.myapplication.javabean;

import java.io.Serializable;

public class List_item implements Serializable {
    private String  count;
    private String title;
    private String artist;
    public List_item(String count , String title, String artist){
        this.count = count;
        this.title = title;
        this.artist = artist;
    }

    public String getCount() {
        return count;
    }
    public void setCount(){
        this.count = count;
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
}
