package com.example.myapplication;

public class Songlist {
    private int picld;
    private String name;
    public Songlist(int picld, String name) {
        this.picld = picld;
        this.name = name;
    }
    public String getName()  {return name;}
    public int getPicld() {  return picld;}
}
