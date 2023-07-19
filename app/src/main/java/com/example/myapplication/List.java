package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.myapplication.javabean.List_item;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private ImageButton ib1,ib2,ib3,ib4;
    private ListView Lv1;
    private List list1;
    private player player1;
    private View view;
    private ArrayList<List_item> arrayList1;
    //创建歌曲的String数组和歌手图片的int数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ib1=findViewById(R.id.playthelist);
        ib2=findViewById(R.id.random_playlist);
        ib3=findViewById(R.id.exit);
        Lv1=findViewById(R.id.songlist);
        arrayList1=new ArrayList<List_item>();
        arrayList1.add(new List_item("1","Steal The Show","Lauv"));
        arrayList1.add(new List_item("2","WALLFLOWER","TWICE"));
        arrayList1.add(new List_item("3","虫儿飞","儿童合唱团"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(List.this );
        /*ListAdapter listAdapter = new com.example.myapplication.ListAdapter(this,R.layout.recycler_item,arrayList1);
        Rv1.setAdapter((RecyclerView.Adapter) listAdapter);
        listAdapter.setOnItemClickListener(new );*/
        ListAdapter listAdapter = new ListAdapter(this,R.layout.recycler_item,arrayList1);
        Lv1.setAdapter(listAdapter);
        Lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent openplayer = new Intent(List.this,player.class);
                openplayer.putExtra("pose",position);
            }
        });

        //Rv1.setOnItemClickListenner;



        /*ib1.setOnClickListener(new View.OnClickListener(){

            /**
             * @param view

           /* @Override
            public void onClick(View view) {

            }
        };*/
        ib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent exitmain= new Intent(List.this,MainActivity.class);
                startActivity(exitmain);
            }
        });

    }


}