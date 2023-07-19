package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class player extends AppCompatActivity {
    private int flag = 1;
    private int pause = 1;
    private int style=1;

    private ImageView im1,im2,im3;
    private TextView title1,singer1,text1,text2;
    private ImageView ps1,ps2,ps3;
    private MediaPlayer media,mp1;
    private SeekBar seekBar;
    private ArrayList<Song> arrayList,arrayList2;
    private ArrayList<Integer> commendsongs,listsongs;
    private static final int NOTIFICATION_ID = 123;
    private static final int NOTIFICATION_REQUEST_CODE=1;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private NotificationChannel channel;
    private Notification notification;
    private TaskStackBuilder stackBuilder;
    private PendingIntent pendingIntent;
    private Intent result;
    private int num,size,songnum,listnum;
    private String duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        im1 = (ImageView) findViewById(R.id.imageView);
        im2 = (ImageView) findViewById(R.id.imageView2);
        im3 = (ImageView) findViewById(R.id.imageView3);
        ps1=(ImageView)findViewById(R.id.imageView8);
        ps2=(ImageView)findViewById(R.id.imageView9);
        ps3=(ImageView)findViewById(R.id.imageView10);
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
        songnum=intent.getIntExtra("pos",-1);
        if(songnum!=-1){
            String title=arrayList.get(songnum).getTitle();
            String singer=arrayList.get(songnum).getArtist();
            title1.setText(title);
            singer1.setText(singer);
            media =MediaPlayer.create(this,commendsongs.get(songnum));
            size=commendsongs.size();
        }
        listsongs=new ArrayList<>();
        listsongs.add(0,R.raw.list1);
        listsongs.add(1,R.raw.list2);
        listsongs.add(2,R.raw.list3);
        listsongs.add(3,R.raw.list4);
        arrayList2=new ArrayList<>();
        arrayList2.add(0,new Song(R.drawable.music1,"夢灯籠","RADWIMPS"));
        arrayList2.add(1,new Song(R.drawable.music2,"星座になれたら","結束バンド"));
        arrayList2.add(2,new Song(R.drawable.music3,"富士山下","陈奕迅"));
        arrayList2.add(3,new Song(R.drawable.music3,"SUMMER!","PENTAGON"));
        Intent intent2=getIntent();
        listnum=intent2.getIntExtra("pos2",-1);
        if(listnum!=-1){
            String title=arrayList2.get(listnum).getTitle();
            String singer=arrayList2.get(listnum).getArtist();
            title1.setText(title);
            singer1.setText(singer);
            media =MediaPlayer.create(this,listsongs.get(listnum));
            size=listsongs.size();
        }
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(media.getDuration());
        text1 = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        duration = musictime(media.getDuration());
        text1.setText(duration);
        List<Song> songList = new ArrayList<>();
        ImageView exit=(ImageView) findViewById(R.id.exit);
        ImageView next=(ImageView) findViewById(R.id.next);
        ImageView forward=(ImageView) findViewById(R.id.forward);
        ImageView cbutton=(ImageView) findViewById(R.id.commentbutton);


        //通知
        builder=new NotificationCompat.Builder(com.example.myapplication.player.this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_MIN);
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("1");
        }
        builder.setSmallIcon(R.drawable.music);
        if(songnum!=-1){
            builder.setSmallIcon(R.drawable.music);
            builder.setContentTitle("MusicAPP");
            builder.setContentText("您正在收听"+arrayList.get(songnum).getTitle());
        }
        if(listnum!=-1){builder.setSmallIcon(R.drawable.music);
            builder.setContentTitle("MusicAPP");
            builder.setContentText("您正在收听"+arrayList2.get(listnum).getTitle());
        }


        result=new Intent(com.example.myapplication.player.this, com.example.myapplication.player.class);
        stackBuilder=TaskStackBuilder.create(com.example.myapplication.player.this);
        stackBuilder.addParentStack(com.example.myapplication.player.class);
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
        ps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style=2;
                ps1.setVisibility(View.INVISIBLE);
                ps2.setVisibility(View.VISIBLE);
            }
        });
        ps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style=3;
                ps2.setVisibility(View.INVISIBLE);
                ps3.setVisibility(View.VISIBLE);
            }
        });
        ps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                style=1;
                ps3.setVisibility(View.INVISIBLE);
                ps1.setVisibility(View.VISIBLE);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exitmain=new Intent(com.example.myapplication.player.this,MainActivity.class);
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
                if (style == 1) {
                    if (num < size - 1) {
                        num++;
                    } else {
                        num = 0;
                    }
                    loadsong();
                }
                else if (style==2){
                    loadsong();
                } else if (style==3) {
                    Random r=new Random();
                    int min = 0;
                    int max = size-1;
                    num = r.nextInt(max - min + 1) ;
                    loadsong();

                }
            }

        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (style == 1) {
                    if (num > 0) {
                        num--;
                    } else num = size-1;
                    loadsong();
                }else if (style==2){
                    loadsong();
                } else if (style==3) {
                    Random r=new Random();
                    int min = 0;
                    int max = size-1;
                    num = r.nextInt(max - min + 1) ;
                    loadsong();
                }
            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(media!=null);
                seekBar.setProgress(media.getCurrentPosition());
            }
        }, 0, 1000);

        cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent com=new Intent(com.example.myapplication.player.this,CommentActivity.class);
                startActivity(com);
            }
        });
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
    protected void onRestart(){
        seekBar.setProgress(media.getCurrentPosition());
        super.onRestart();
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
        if(media!=null){

        }

        super.onResume();
    }
    @Override
    protected void onDestroy() {
        if (media!=null){
            media.stop();
            media.release();
            media=null;
        }
        super.onDestroy();
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
        if(num==3) {
            init(num);
            media.setOnCompletionListener(completionListener);
        }
    }
    MediaPlayer.OnCompletionListener completionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (num < size - 1) {
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
        builder=new NotificationCompat.Builder(com.example.myapplication.player.this);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("1", "my_channel", NotificationManager.IMPORTANCE_MIN);
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId("1");
        }
        if(songnum!=-1){
            builder.setSmallIcon(R.drawable.music);
            builder.setContentTitle("MusicAPP");
            builder.setContentText("您正在收听"+arrayList.get(num).getTitle());
        }
        if(listnum!=-1){
            builder.setSmallIcon(R.drawable.music);
            builder.setContentTitle("MusicAPP");
            builder.setContentText("您正在收听"+arrayList2.get(num).getTitle());
        }
        result=new Intent(com.example.myapplication.player.this, com.example.myapplication.player.class);
        stackBuilder=TaskStackBuilder.create(com.example.myapplication.player.this);
        stackBuilder.addParentStack(com.example.myapplication.player.class);
        stackBuilder.addNextIntent(result);
        pendingIntent=PendingIntent.getActivity(this,NOTIFICATION_REQUEST_CODE,result,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(pendingIntent);
        notification=builder.build();
        notificationManager.notify(1,notification);
    }
    public void init(int num){
        refreshno(num);
        if(songnum!=-1){
            title1.setText(arrayList.get(num).getTitle());
            singer1.setText(arrayList.get(num).getArtist());
            media.reset();
            media=MediaPlayer.create(getApplicationContext(),commendsongs.get(num));
        }
        if(listnum!=-1){
            title1.setText(arrayList2.get(num).getTitle());
            singer1.setText(arrayList2.get(num).getArtist());
            media.reset();
            media=MediaPlayer.create(getApplicationContext(),listsongs.get(num));
        }
        seekBar.setMax(media.getDuration());
        duration = musictime(media.getDuration());
        text1.setText(duration);
        media.start();
    }
}