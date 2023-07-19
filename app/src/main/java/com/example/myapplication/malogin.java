package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.javabean.user;

public class malogin extends AppCompatActivity implements View.OnClickListener {
    private Button login2;
    private EditText name2,password2;
    private SQLite sqlite2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_malogin);
        sqlite2 = new SQLite(this);
        find();
    }

    private void find(){
        name2 = findViewById(R.id.editTextText2);
        password2 = findViewById(R.id.editTextTextPassword2);
        login2 = findViewById(R.id.button21);

        login2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

            if(id == R.id.button21){
                Intent i = new Intent(this, admin.class);
                startActivity(i);
        }
    }

    public void malo(View view) {
        String s1 = name2.getText().toString();
        String s2 = password2.getText().toString();
        user u = new user(s1,s2);
        boolean l = sqlite2.login(s1,s2);
        if(l){
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,player.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }
    }
}
