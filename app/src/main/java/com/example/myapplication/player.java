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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private TextView title1,singer1,text1,text2;
    private MediaPlayer media,mp1;
    private SeekBar seekBar;
    private player player1;
    private ArrayList<Song> arrayList;
    private ArrayList<Integer> commendsongs;
    private static final int NOTIFICATION_ID = 123;
    private static final int NOTIFICATION_REQUEST_CODE=1;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private NotificationChannel channel;
    private Notification notification;
    private TaskStackBuilder stackBuilder;
    private PendingIntent pendingIntent;
    private Intent result;
    private int num;
    private String duration;
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
        anim.start();
        commendsongs=new ArrayList<>();
        commendsongs.add(0,R.raw.music1);
        commendsongs.add(1,R.raw.music2);
        commendsongs.add(2,R.raw.music3);
        arrayList=new ArrayList<>();
        arrayList.add(0,new Song(R.drawable.music1,"Steal The Show","Lauv"));
        arrayList.add(1,new Song(R.drawable.music2,"WALLFLOWER","TWICE"));
        arrayList.add(2,new Song(R.drawable.music3,"虫儿飞","儿童合唱团"));
        title1=(TextView) findViewById(R.id.title);
        singer1=(TextView) findViewById(R.id.singer);
        Intent intent=getIntent();
        int songnum=intent.getIntExtra("pos",0);
        String title=arrayList.get(songnum).getTitle();
        String singer=arrayList.get(songnum).getArtist();
        title1.setText(title);
        singer1.setText(singer);
        media =MediaPlayer.create(this,commendsongs.get(songnum));
        seekBar = (SeekBar) findViewById(com.example.myapplication.R.id.seekBar);
        seekBar.setMax(media.getDuration());
        text1 = (TextView) findViewById(com.example.myapplication.R.id.textView);
        text2 = (TextView) findViewById(com.example.myapplication.R.id.textView2);
        duration = musictime(media.getDuration());
        text1.setText(duration);
        List<Song> songList = new ArrayList<>();
        ImageView exit=(ImageView) findViewById(R.id.exit);
        ImageView next=(ImageView) findViewById(R.id.next);
        ImageView forward=(ImageView) findViewById(R.id.forward);

        //通知
        builder=new NotificationCompat.Builder(player.this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_MIN);
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("1");
        }
        builder.setSmallIcon(com.example.myapplication.R.drawable.music);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),arrayList.get(songnum).getImage()));
        builder.setContentTitle("MusicAPP");
        builder.setContentText("您正在收听"+arrayList.get(songnum).getTitle());
        result=new Intent(player.this,player.class);
        stackBuilder=TaskStackBuilder.create(player.this);
        stackBuilder.addParentStack(player.class);
        stackBuilder.addNextIntent(result);
        pendingIntent=PendingIntent.getActivity(this,NOTIFICATION_REQUEST_CODE,result,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        notification=builder.build();
        notificationManager.notify(1,notification);

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
            media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(media.getDuration());
                    duration = musictime(media.getDuration());
                    text1.setText(duration);
                }
            });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(num<commendsongs.size()-1){
                    num++;
                }
                else {
                    num=0;
                }
                init(num);
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>0){
                    num--;
                }
                else num=commendsongs.size()-1;
                init(num);
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

            }
        });

        media.setOnCompletionListener(completionListener);
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
    void loadsong() {
        if(num==0) {
            init(num);
            media.setOnCompletionListener(completionListener);
        }
        if(num==1) {
            init(num);
            media.setOnCompletionListener(completionListener);
        }
        if(num==2) {
            init(num);
            media.setOnCompletionListener(completionListener);
        }
    }
    MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (num < commendsongs.size() - 1) {
                num++;
            } else {
                num = 0;
            }
            loadsong();
        }
    };
    public String musictime(int t) {
        String time;
        int min=t/1000/60;
        int sec=t/1000%60;
        time="0"+min+":";
        if(sec<10) time+="0";
        time+=sec;
        return time;
    }
    public void refreshno(int num){
        //通知
        builder=new NotificationCompat.Builder(player.this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_MIN);
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("1");
        }
        builder.setSmallIcon(com.example.myapplication.R.drawable.music);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),arrayList.get(num).getImage()));
        builder.setContentTitle("MusicAPP");
        builder.setContentText("您正在收听"+arrayList.get(num).getTitle());
        result=new Intent(player.this,player.class);
        stackBuilder=TaskStackBuilder.create(player.this);
        stackBuilder.addParentStack(player.class);
        stackBuilder.addNextIntent(result);
        pendingIntent=PendingIntent.getActivity(this,NOTIFICATION_REQUEST_CODE,result,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        notification=builder.build();
        notificationManager.notify(1,notification);
    }
    public void init(int num){
        refreshno(num);
        title1.setText(arrayList.get(num).getTitle());
        singer1.setText(arrayList.get(num).getArtist());
        media.reset();
        media=MediaPlayer.create(getApplicationContext(),commendsongs.get(num));
        seekBar.setMax(media.getDuration());
        duration = musictime(media.getDuration());
        text1.setText(duration);
        media.start();
    }
}