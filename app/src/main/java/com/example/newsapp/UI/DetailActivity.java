package com.example.newsapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    String title, description, content, url, imageUrl;
    TextView tv_title, tv_subTitle, tv_content;
    Button btn_info;
    ImageView newsIV;
    ImageView img_share;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");
        url = getIntent().getStringExtra("url");
        imageUrl = getIntent().getStringExtra("imgUrl");
        img_share = findViewById(R.id.img_share);




        tv_title = findViewById(R.id.tv_title_detailActivity);
        tv_subTitle = findViewById(R.id.tv_subDesp_detailActivity);
        tv_content = findViewById(R.id.tv_content);
        newsIV = findViewById(R.id.idIVNews);
        btn_info = findViewById(R.id.btn_more_info);
        tv_title.setText(title);
        tv_subTitle.setText(description);
        tv_content.setText(content);
        Picasso.get().load(url).into(newsIV);
        btn_info.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(imageUrl));
            startActivity(intent);
        });
        img_share.setOnClickListener(view -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this news article: " + imageUrl);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });
    }
}