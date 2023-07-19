package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.Songlist;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SonglistAdapter extends ArrayAdapter<Songlist> {
    private int resld;
    public SonglistAdapter(Context context, int resource, List<Songlist> objects) {
        super(context, resource, objects);
        resld = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        Songlist songlist = getItem(position);//获得当前列表项Book对象
        View view= LayoutInflater.from(getContext()).inflate(resld,parent,false);
        TextView listname=(TextView)view.findViewById(R.id.bookname);
        listname.setText(songlist.getName());
        ImageView listpic=(ImageView)view.findViewById(R.id.bookpic);
        listpic.setImageResource(songlist.getPicld());
        return view;
    }
}
