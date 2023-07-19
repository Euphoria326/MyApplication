package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.com_adminbean;
import java.util.List;

public class com_adminAdapter extends ArrayAdapter {

     List<com_adminbean> data;
    private Context context;
    private int resource;

    public com_adminAdapter(Context context, int resource,List<com_adminbean> data) {
        super(context,resource,data);
        this.data = data;
        this.context = context;
        this.resource=resource;
    }



    @Override
    public View getView(int i, View convertview, ViewGroup parent) {
        com_adminbean com= (com_adminbean) getItem(i);
        View view= LayoutInflater.from(context).inflate(resource,parent,false);
        TextView username=(TextView) view.findViewById(R.id.comuser);
        TextView comadmin=(TextView) view.findViewById(R.id.comad);
        username.setText(((com_adminbean) getItem(i)).getUsername());
        comadmin.setText(((com_adminbean) getItem(i)).getComments());
        return view;
    }

}
