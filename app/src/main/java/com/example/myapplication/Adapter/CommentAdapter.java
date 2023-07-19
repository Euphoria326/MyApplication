package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.BaseAdapter;

import com.example.myapplication.Comment;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


public class CommentAdapter extends BaseAdapter{
    private int cresource;
    private  Context context;
    List<Comment> data;

    public CommentAdapter(@NonNull Context context,List<Comment> comments){
        this.context=context;
        this.data=comments;

    }
    @Override
    public int getCount(){
        return  data.size();
    }
    @Override
    public Object getItem(int i){
        return  data.get(i);
    }
    @Override
    public long getItemId(int i){
        return i;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent){
        ViewHolder holder1;
        if(convertview==null){
            holder1=new ViewHolder();
            convertview=LayoutInflater.from(context).inflate(R.layout.comment_list,null);
            holder1.username=(TextView) convertview.findViewById(R.id.user);
            holder1.comment=(TextView)convertview.findViewById(R.id.com);
            convertview.setTag(holder1);
        }else {
            holder1=(ViewHolder) convertview.getTag();
        }
        holder1.username.setText(data.get(position).getUser());
        holder1.comment.setText(data.get(position).getComment());
        return convertview;
    }
    public void addcomment(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }
    public void delcomment(Comment comment){
        data.remove(comment);
        notifyDataSetChanged();
    }
    public static class ViewHolder {
        TextView username;
        TextView comment;

    }

}
