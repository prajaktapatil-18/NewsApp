package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String title, desp, urlImg;


    TextView idTVTitle, idTVSubTitle;
    ImageView IVNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        title = getIntent().getStringExtra("title");
        desp = getIntent().getStringExtra("desp");
        urlImg = getIntent().getStringExtra("image");


        idTVTitle = findViewById(R.id.idTVTitle);
        idTVSubTitle = findViewById(R.id.idTVSubTitle);
        IVNews = findViewById(R.id.idIVNews);
        idTVTitle.setText(title);
        idTVSubTitle.setText(desp);
        Picasso.get().load(urlImg).into(IVNews);
    }
}