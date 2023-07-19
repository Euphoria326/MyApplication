package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.javabean.List_item;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<List_item> {
    private Context scontext;
    private int sresource;
    public ListAdapter( Context context, int resource, ArrayList<List_item> lists){
        super(context,resource,lists);
        this.scontext=context;
        this.sresource=resource;}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        View view= LayoutInflater.from(getContext()).inflate(sresource,parent,false);
        TextView count=(TextView) view.findViewById(R.id.count);
        TextView music1=(TextView) view.findViewById(R.id.music2);
        TextView singer=(TextView) view.findViewById(R.id.singer2);
        count.setText(getItem(position).getCount());
        music1.setText(getItem(position).getTitle());
        singer.setText(getItem(position).getArtist());
        return view;
    }
}