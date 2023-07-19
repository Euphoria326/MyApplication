package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;

import com.example.myapplication.R;
import com.example.myapplication.Song;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SongAdapter extends ArrayAdapter<Song> {
    private Context scontext;
    private int sresource;

    public SongAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Song> songs){
        super(context,resource,songs);
        this.scontext=context;
        this.sresource=resource;

    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview,@NonNull ViewGroup parent){
        //LayoutInflater layoutInflater=LayoutInflater.from(scontext);
        //musicview=layoutInflater.inflate(sresource,group,false);

        Song song=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(sresource,parent,false);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView4);
        TextView songname=(TextView) view.findViewById(R.id.music1);
        TextView singer=(TextView) view.findViewById(R.id.singer);
        imageView.setImageResource(song.getImage());
        songname.setText(getItem(position).getTitle());
        singer.setText(getItem(position).getArtist());
        return view;
    }
}
