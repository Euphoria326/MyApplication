package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class player extends AppCompatActivity {
    private int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        ImageView im1=(ImageView) findViewById(R.id.imageView);
        ImageView im2=(ImageView) findViewById(R.id.imageView2);
        ImageView im3=(ImageView)findViewById(R.id.imageView3);
        ObjectAnimator anim = ObjectAnimator.ofFloat(im3,"rotation",0,360);
        anim.setDuration(3000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(5);
        anim.setRepeatMode(ObjectAnimator.RESTART);

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                im1.setVisibility(View.INVISIBLE);
                im2.setVisibility(View.VISIBLE);
                LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
                if(flag ==1) {
                    anim.start();
                    flag=0;
                }
                else if (flag ==0) anim.resume();
            }
        });

        im2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                im2.setVisibility(View.INVISIBLE);
                im1.setVisibility(View.VISIBLE);
                anim.pause();


            }
        });
    }
}