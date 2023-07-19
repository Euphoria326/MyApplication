package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.Adapter.com_adminAdapter;
import com.example.myapplication.Adapter.music_adminAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;

public class music_admin extends AppCompatActivity {

    private List<music_adminbean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceStaet){
        super.onCreate(savedInstanceStaet);
        setContentView(R.layout.music_admin);
        ListView listview = findViewById(R.id.music_admin_listview);
        data= new ArrayList<>();
        data.add(new music_adminbean(R.drawable.music1,"有点难听","1"));
        music_adminAdapter musicAdminAdapter=new music_adminAdapter(this,R.layout.music_admin_list,data);
        listview.setAdapter(musicAdminAdapter);

    }

}
