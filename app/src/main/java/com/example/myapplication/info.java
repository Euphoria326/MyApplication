package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class info extends AppCompatActivity {
    //按钮
    public void ClickButton(View view){
        TextView tv1=(TextView) findViewById(R.id.textView);
        tv1.setText("摆烂啦，不干啦");
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv1 = (TextView) findViewById(R.id.textView);
                tv1.setText("单击按钮2");
            }
        });

        setContentView(R.layout.activity_main);
        List<Book> bookList=new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.listViewData);
        Book book1=new Book(R.drawable.black,names[0]);
        bookList.add(book1);
        Book book2=new Book(R.drawable.black,names[1]);
        bookList.add(book2);
        Book book3=new Book(R.drawable.black,names[2]);
        bookList.add(book3);

        BookAdapter adapter=new BookAdapter(info.this,R.layout.info_list,bookList);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=bookList.get(position);
                Toast.makeText(info.this,book.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
