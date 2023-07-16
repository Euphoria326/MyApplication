package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.javabean.user;

public class register extends AppCompatActivity {
    private Button register1;
    private EditText name1,password1;
    private SQLite sqlite1;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqlite1 = new SQLite(this);
        find();
    }

    private void find(){
        name1 = findViewById(R.id.editTextText1);
        password1 = findViewById(R.id.editTextTextPassword1);
        register1 = findViewById(R.id.button11);
    }

    public void regi(View view) {
        String s1 = name1.getText().toString();
        String s2 = password1.getText().toString();
        user u = new user(s1,s2);
        long r = sqlite1.register(u);
        if(r != -1){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,login.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }
}
