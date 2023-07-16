package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class List extends AppCompatActivity {

    private ImageButton ib1,ib2,ib3,ib4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ib1=findViewById(R.id.playthelist);
        ib2=findViewById(R.id.random_playlist);
        ib3=findViewById(R.id.exit);


    }


}