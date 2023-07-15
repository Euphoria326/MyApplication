package com.example.myapplication;

public class Book {
    private int picld;
    private String name;
    public Book(int picld, String name) {
        this.picld = picld;
        this.name = name;
    }
    public String getName()  {return name;}
    public int getPicld() {  return picld;}
}
