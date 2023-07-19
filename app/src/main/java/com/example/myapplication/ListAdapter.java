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
    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<List_item> list_itemArrayList){
        super(context,resource);
        this.scontext=context;
        this.sresource=resource;}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        //LayoutInflater layoutInflater=LayoutInflater.from(scontext);
        //musicview=layoutInflater.inflate(sresource,group,false);

        List_item listItem =getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(sresource,parent,false);
        TextView count=(TextView) view.findViewById(R.id.count);
        TextView music1=(TextView) view.findViewById(R.id.music1);
        TextView singer=(TextView) view.findViewById(R.id.singer);
        count.setText(getItem(position).getCount());
        music1.setText(getItem(position).getTitle());
        singer.setText(getItem(position).getArtist());
        return view;
    }
}