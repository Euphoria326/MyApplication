package com.example.myapplication;
import com.example.myapplication.player;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.activity_main);
        ImageView user=(ImageView) findViewById(com.example.myapplication.R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info=new Intent(MainActivity.this, info.class);
                startActivity(info);
            }
        });
        /*ListView listView=(ListView) findViewById(com.example.myapplication.R.id.list);
        HashMap<String,String> music=new HashMap<>();
        music.put("Steal The Show","Lauv");
        music.put("WALLFLOWER","TWICE");
        music.put("虫儿飞","儿童合唱团");

        List<HashMap<String,String>>  item=new ArrayList<>();
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,item,com.example.myapplication.R.layout.list_item,
                new String[]{"First","Second"},new int[]{com.example.myapplication.R.id.music1, com.example.myapplication.R.id.singer});
        Iterator iterator=music.entrySet().iterator();
        while (iterator.hasNext()){
            HashMap<String,String> finallist =new HashMap<>();
            Map.Entry m=(Map.Entry)iterator.next();
            finallist.put("First",m.getKey().toString());
            finallist.put("Second",m.getValue().toString());
            item.add(finallist);
        }
        listView.setAdapter(simpleAdapter);*/
        Intent intent=getIntent();


        ListView listView=findViewById(com.example.myapplication.R.id.list);
        ArrayList<Song> arrayList=new ArrayList<>();
        arrayList.add(new Song(R.drawable.music1,"Steal The Show","Lauv"));
        arrayList.add(new Song(R.drawable.music2,"WALLFLOWER","TWICE"));
        arrayList.add(new Song(R.drawable.music3,"虫儿飞","儿童合唱团"));
        SongAdapter songAdapter=new SongAdapter(this, R.layout.list_item,arrayList);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song=arrayList.get(position);
                Intent player=new Intent(MainActivity.this, com.example.myapplication.player.class);
                player.putExtra("song",new Song(0,song.getTitle(),song.getArtist()));
                startActivity(player);
            }
        });
    }
@Override
    protected void onStart(){
        super.onStart();
}

}