package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder> {

    private ArrayList<Articles> list;
    private  Context context;

    public AdapterNews(ArrayList<Articles> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNews.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_lyt,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Articles articles = list.get(position);

        holder.newsHeadline.setText(list.get(position).getTitle());
        holder.newSubHeadline.setText(list.get(position).getDesp());
Picasso.get().load(articles.getUrl()).into(holder.imgNews);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("title",list.get(position).getTitle());
                intent.putExtra("desp",list.get(position).getDesp());
//                intent.putExtra("url",list.get(position).getUrl());
                intent.putExtra("image",list.get(position).getUrlToImage());
//                intent.putExtra("content",list.get(position).getContent());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView newsHeadline,newSubHeadline;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews =  itemView.findViewById(R.id.imgNews);
            newsHeadline = itemView.findViewById(R.id.newsHeadline);
            newSubHeadline = itemView.findViewById(R.id.newsSubHeadline);

        }
    }
}
