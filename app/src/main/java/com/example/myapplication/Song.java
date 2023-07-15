package com.example.myapplication;

import java.io.Serializable;

public class Song implements Serializable {
    private String title;
    private String artist;
    private int image;

    public Song(int image, String title, String artist) {
        this.image = image;
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

    public int getImage() {
        return image;
    }

        public void setImage(int image) {
        this.image = image;
    }
}

