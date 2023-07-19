package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.Adapter.com_adminAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class com_admin extends AppCompatActivity {

    private ArrayList<com_adminbean> data ;

    @Override
    protected void onCreate(Bundle savedInstanceStaet){
        super.onCreate(savedInstanceStaet);
        setContentView(R.layout.comment_admin);
        ListView listview = findViewById(R.id.com_admin_listview);
        data= new ArrayList<>();
        data.add(new com_adminbean("用户1","有点难听"));
        data.add(new com_adminbean("用户2","真的不好听"));
        data.add(new com_adminbean("用户3","别唱了"));
        com_adminAdapter comAdminAdapter=new com_adminAdapter(this,R.layout.com_admin_list,data);
        listview.setAdapter(comAdminAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(com_admin.this);
                builder.setMessage("该条评论是否通过审核");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        comAdminAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.remove(position);
                        comAdminAdapter.notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        });
    }

}
