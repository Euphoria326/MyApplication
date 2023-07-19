package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.javabean.List_item;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private ImageButton ib1,ib2,ib3,ib4;
    private List list1;
    private player player1;
    private View view;
    //创建歌曲的String数组和歌手图片的int数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       // ib1=findViewById(R.id.playthelist);
        //ib3=findViewById(R.id.exit);
        ListView lv1=findViewById(R.id.songlist);
        ArrayList<List_item> arrayList1=new ArrayList<>();
        arrayList1.add(new List_item("1","夢灯籠","RADWIMPS"));
        arrayList1.add(new List_item("2","星座になれたら","結束バンド"));
        arrayList1.add(new List_item("3","富士山下","陈奕迅"));
        arrayList1.add(new List_item("4","SUMMER!","PENTAGON"));
        ListAdapter listAdapter = new com.example.myapplication.ListAdapter(this,R.layout.recycler_item,arrayList1);
        lv1.setAdapter(listAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent openplayer=new Intent(List.this, player.class);
                //player.putExtra("song",new Song(0,song.getTitle(),song.getArtist()));
                openplayer.putExtra("pos2",position);
                startActivity(openplayer);
            }
        });
        ImageView exit=(ImageView) findViewById(R.id.exitm);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitmain=new Intent(List.this,MainActivity.class);
                startActivity(exitmain);
            }
        });
/*
        ib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitmain= new Intent(List.this,info.class);
                startActivity(exitmain);
            }
        });
*/
    }


}