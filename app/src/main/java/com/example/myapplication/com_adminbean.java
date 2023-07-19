package com.example.myapplication;
import java.io.Serializable;
public class com_adminbean implements Serializable{
    private String username;
    private String comments;
    public com_adminbean(String username, String comments){
        this.username = username;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
