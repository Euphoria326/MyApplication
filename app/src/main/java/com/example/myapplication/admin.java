package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlayout);
        Button music_admin = findViewById(R.id.admin_button);
        Button com_admin = findViewById(R.id.admin_button2);
        //点击音乐审核按钮
        music_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(admin.this,com.example.myapplication.music_admin.class));
             }
        });
        //点击评论审核按钮你
        com_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent j = new Intent(admin.this, com.example.myapplication.com_admin.class);
            startActivity(j);
            }
        });
    }
}





