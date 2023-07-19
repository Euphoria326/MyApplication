package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.music_adminbean;

import java.util.List;
import java.util.ArrayList;

public class music_adminAdapter extends ArrayAdapter {

    private List<music_adminbean> data;
    private Context context;
    private int resource;


    public music_adminAdapter(Context context, int resource,List<music_adminbean> data) {
        super(context,resource,data);
        this.data = data;
        this.context = context;
        this.resource=resource;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup parent) {
        music_adminbean com= (music_adminbean) getItem(i);
        View view= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView username=(TextView) view.findViewById(R.id.sname);
        TextView comadmin=(TextView) view.findViewById(R.id.songsinger);
        username.setText(((music_adminbean) getItem(i)).getTitle());
        comadmin.setText(((music_adminbean) getItem(i)).getArtist());
        return view;
    }
}
