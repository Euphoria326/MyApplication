package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity implements View.OnClickListener {
    private Button login,register,manager;
    private EditText name,password;
    private SQLite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.activity_login);
        sqlite = new SQLite(this);
        find();
    }

    private void find(){
        name = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button1);
        register = findViewById(R.id.button2);
        manager = findViewById(R.id.button3);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
        manager.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.button1) {
            String s1 = name.getText().toString();
            String s2 = password.getText().toString();

            boolean login = sqlite.login(s1, s2);
            if (login) {
                Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.button2) {
            Intent j = new Intent(this, com.example.myapplication.register.class);
            startActivity(j);
        }
        else if(id == R.id.button3) {
            Intent k = new Intent(this,malogin.class);
            startActivity(k);
        }
    }
}


