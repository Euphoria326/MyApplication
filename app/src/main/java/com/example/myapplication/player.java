package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;
import androidx.core.graphics.drawable.IconCompat;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static android.os.Build.VERSION_CODES.M;
import static android.os.Build.VERSION_CODES.R;

public class player extends AppCompatActivity {
    private int flag = 1;
    private int pause = 1;
    private MediaPlayer media;
    private SeekBar seekBar;
    private player player1;
    private static final int NOTIFICATION_ID = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.player);
        ImageView im1 = (ImageView) findViewById(com.example.myapplication.R.id.imageView);
        ImageView im2 = (ImageView) findViewById(com.example.myapplication.R.id.imageView2);
        ImageView im3 = (ImageView) findViewById(com.example.myapplication.R.id.imageView3);
        ObjectAnimator anim = ObjectAnimator.ofFloat(im3, "rotation", 0, 360);
        anim.setDuration(8000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        media =MediaPlayer.create(this, com.example.myapplication.R.raw.beautiful);
        SeekBar seekBar = (SeekBar) findViewById(com.example.myapplication.R.id.seekBar);
        seekBar.setMax(media.getDuration());
        TextView text1 = (TextView) findViewById(com.example.myapplication.R.id.textView);
        TextView text2 = (TextView) findViewById(com.example.myapplication.R.id.textView2);
        String duration = musictime(media.getDuration());
        text1.setText(duration);
        List<Song> songList = new ArrayList<>();

        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im1.setVisibility(View.INVISIBLE);
                im2.setVisibility(View.VISIBLE);
                LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
                if (flag == 1) {
                    anim.start();
                    flag = 0;
                } else if (flag == 0) anim.resume();
                if (pause == 1) {
                    media.seekTo(media.getCurrentPosition());
                    media.start();
                    pause = 0;
                } else {
                    media.start();
                }
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                im2.setVisibility(View.INVISIBLE);
                im1.setVisibility(View.VISIBLE);
                anim.pause();
                if (media.isPlaying()) {
                    media.pause();
                    pause = 1;
                }
            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(media.getCurrentPosition());
            }
        }, 0, 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    media.seekTo(i);
                    seekBar.setProgress(i);
                }
                String current= musictime(media.getCurrentPosition());
                text2.setText(current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                media.seekTo(seekBar.getProgress());

            }
        });
        media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                media.setLooping(true);

            }
        });
        NotificationCompat.Builder builder=new NotificationCompat.Builder(player.this);
        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("1");
        }
        builder.setSmallIcon(com.example.myapplication.R.drawable.music);
        builder.setContentTitle("MusicAPP");
        builder.setContentText("您正在收听。。。");
        Intent result=new Intent(player.this,player.class);
        TaskStackBuilder stackBuilder=TaskStackBuilder.create(player.this);
        stackBuilder.addParentStack(player.class);
        stackBuilder.addNextIntent(result);
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notification=builder.build();

        notificationManager.notify(1,notification);

    }

        public String musictime(int t) {
        String time;
        int min=t/1000/60;
        int sec=t/1000%60;
        time="0"+min+":";
        if(sec<10) time+="0";
        time+=sec;
        return time;
    }
}


