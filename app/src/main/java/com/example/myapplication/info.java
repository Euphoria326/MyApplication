package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.SonglistAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class info extends AppCompatActivity {
    //按钮
    public void ClickButton(View view){
        TextView tv1=(TextView) findViewById(R.id.textView);
        tv1.setText("加油！");
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button bt2 = (Button) findViewById(R.id.button2);
        TextView me2=(TextView) findViewById(R.id.me2);
        TextView main2=(TextView) findViewById(R.id.main2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitmain=new Intent(info.this,password.class);
                startActivity(exitmain);
            }
        });
        List<Songlist> songlistList =new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.listViewData);
        Songlist songlist1 =new Songlist(R.drawable.gedan2,names[0]);
        songlistList.add(songlist1);
        Songlist songlist2 =new Songlist(R.drawable.gedan4,names[1]);
        songlistList.add(songlist2);
        ImageView exit2=(ImageView) findViewById(R.id.exit2);
        SonglistAdapter adapter=new SonglistAdapter(info.this,R.layout.info_list, songlistList);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Songlist songlist = songlistList.get(position);
                Toast.makeText(info.this, songlist.getName(),Toast.LENGTH_LONG).show();
                Intent openlist=new Intent(info.this, com.example.myapplication.List.class);
                startActivity(openlist);
            }
        });
        exit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitmain=new Intent(info.this,MainActivity.class);
                startActivity(exitmain);
            }
        });
        me2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openme=new Intent(info.this, info.class);
                startActivity(openme);
            }
        });
        main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openmain=new Intent(info.this, MainActivity.class);
                startActivity(openmain);
            }
        });
    }
}
