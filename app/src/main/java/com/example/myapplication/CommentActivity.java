package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;
import android.widget.AdapterView;

import com.example.myapplication.Adapter.CommentAdapter;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private EditText com1;
    private Button button;
    private ImageView exit;
    private String com2;
    private List<Comment> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ListView listView=findViewById(com.example.myapplication.R.id.list2);
        data=new ArrayList<>();
        //commentList.add(new Comment("帅哥","11" ));
        //commentList.add(new Comment("WALLFLOWER","TWICE"));
        CommentAdapter commentAdapter=new CommentAdapter(getApplicationContext(), data);
        listView.setAdapter(commentAdapter);
        com1=(EditText)findViewById(R.id.comment1);
        button=(Button)findViewById(R.id.sure);
        exit=(ImageView) findViewById(R.id.exitplayer);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment comment1=new Comment();
                comment1.setUser("123");
                comment1.setComment(com1.getText().toString());
                commentAdapter.addcomment(comment1);
                com1.setText("");
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(CommentActivity.this);
                builder.setMessage("是否删除该条评论");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        commentAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit1=new Intent(CommentActivity.this,player.class);
                startActivity(exit1);
            }
        });
    }



}
