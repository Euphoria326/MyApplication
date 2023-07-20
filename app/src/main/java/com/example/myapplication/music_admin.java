package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
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
        data.add(new music_adminbean(R.drawable.music1,"ギターと孤独と蒼い惑星","結束バンド"));
        data.add(new music_adminbean(R.drawable.music1,"Parties","Jake Miller"));
        data.add(new music_adminbean(R.drawable.music1,"唯一","告五人"));
        music_adminAdapter musicAdminAdapter=new music_adminAdapter(this,R.layout.music_admin_list,data);
        listview.setAdapter(musicAdminAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(music_admin.this);
                builder.setMessage("该条评论是否通过审核");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        musicAdminAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        musicAdminAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        });
        ImageView exit1=(ImageView) findViewById(R.id.exitadmin1);
        exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exita1=new Intent(music_admin.this,admin.class);
                startActivity(exita1);
            }
        });
    }

}
