package com.example.newsapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Articles;
import com.example.newsapp.UI.DetailActivity;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.MyViewHolder> {

    private ArrayList<Articles> list;
    private Context context;

    public AdapterNews(ArrayList<Articles> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterNews.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_lyt, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Articles articles = list.get(position);
        holder.newsTitle.setText(list.get(position).getTitle());
        holder.newSubTitle.setText(list.get(position).getDescription());
        Picasso.get().load(articles.getUrl()).into(holder.imgNews);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", articles.getTitle());
            intent.putExtra("description", articles.getDescription());
            intent.putExtra("content", articles.getContent());
            intent.putExtra("url", articles.getUrl());
            intent.putExtra("imgUrl", articles.getUrlToImage());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNews;
        TextView newsTitle, newSubTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgNews = itemView.findViewById(R.id.imgNews);
            newsTitle = itemView.findViewById(R.id.newsHeadline);
            newSubTitle = itemView.findViewById(R.id.newsSubHeadline);

        }
    }
}
