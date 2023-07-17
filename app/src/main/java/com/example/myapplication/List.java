package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class List extends AppCompatActivity {

    private ImageButton ib1,ib2,ib3,ib4;
    private List list1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ib1=findViewById(R.id.playthelist);
        ib2=findViewById(R.id.random_playlist);
        ib3=findViewById(R.id.exit);

        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitmain= new Intent(List.this,MainActivity.class);
                startActivity(exitmain);
            }
        });

    }


}