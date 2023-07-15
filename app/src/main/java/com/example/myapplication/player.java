package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class player extends AppCompatActivity {
    private int flag = 1;
    private int pause = 1;

    private ImageView im1,im2,im3;
    private MediaPlayer media;
    private SeekBar seekBar;
    private player player1;
    private static final int NOTIFICATION_ID = 123;
    private static final int NOTIFICATION_REQUEST_CODE=1;
    private NotificationManager notificationManager;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.player);
        im1 = (ImageView) findViewById(com.example.myapplication.R.id.imageView);
        im2 = (ImageView) findViewById(com.example.myapplication.R.id.imageView2);
        im3 = (ImageView) findViewById(com.example.myapplication.R.id.imageView3);
        ObjectAnimator anim = ObjectAnimator.ofFloat(im3, "rotation", 0, 360);
        anim.setDuration(8000);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        media =MediaPlayer.create(this,R.raw.music1);
        SeekBar seekBar = (SeekBar) findViewById(com.example.myapplication.R.id.seekBar);
        seekBar.setMax(media.getDuration());
        TextView text1 = (TextView) findViewById(com.example.myapplication.R.id.textView);
        TextView text2 = (TextView) findViewById(com.example.myapplication.R.id.textView2);
        String duration = musictime(media.getDuration());
        text1.setText(duration);
        List<Song> songList = new ArrayList<>();
        ImageView exit=(ImageView) findViewById(R.id.exit);
        ImageView next=(ImageView) findViewById(R.id.next);
        ImageView forward=(ImageView) findViewById(R.id.forward);
        TextView title1=(TextView) findViewById(R.id.title);
        TextView singer1=(TextView) findViewById(R.id.singer);
        ArrayList<Integer> commendsongs=new ArrayList<>();
        commendsongs.add(0,R.raw.music1);
        commendsongs.add(1,R.raw.music2);
        commendsongs.add(2,R.raw.music3);
        Intent intent=getIntent();
        Song song=(Song) intent.getSerializableExtra("song");
        String title=song.getTitle();
        String singer=song.getArtist();
        title1.setText(title);
        singer1.setText(singer);

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
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitmain=new Intent(player.this,MainActivity.class);
                startActivity(exitmain);
            }
        });
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(media!=null)
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

        //通知
        NotificationCompat.Builder builder=new NotificationCompat.Builder(player.this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_MIN);
             notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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
        PendingIntent pendingIntent=PendingIntent.getActivity(this,NOTIFICATION_REQUEST_CODE,result,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        Notification notification=builder.build();
        notificationManager.notify(1,notification);
    }
    @Override
    protected void onStart() {
        if (media!=null){
            im1.setVisibility(View.INVISIBLE);
            im2.setVisibility(View.VISIBLE);
            media.start();
        }
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
            if (media!=null){
                media.stop();
                media.release();
                media=null;
            }
        super.onPause();
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


