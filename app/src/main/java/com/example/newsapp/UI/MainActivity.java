package com.example.newsapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.example.newsapp.Adapter.AdapterCategory;
import com.example.newsapp.Adapter.AdapterNews;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.Model.NewsModel;
import com.example.newsapp.Model.RvModel;
import com.example.newsapp.R;
import com.example.newsapp.RetrofitApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterCategory.categoryClickInterface {

    private RecyclerView rvCategory, rvNews;
    ProgressBar progressBar;
    SearchView searchView;
    Switch switcher;
    SharedPreferences.Editor editor;
    private ArrayList<RvModel> cateList;
    private ArrayList<Articles> articleList;
    private AdapterCategory adapterCategory;
    private AdapterNews adapterNews;
    private SharedPreferences sharedPreferences;
    boolean nightMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rvCategory);
        progressBar = findViewById(R.id.progress_bar);
        rvNews = findViewById(R.id.rvNews);
        cateList = new ArrayList<>();
        articleList = new ArrayList<>();
        adapterCategory = new AdapterCategory(cateList, this, this::onCategoryClick);
        adapterNews = new AdapterNews(articleList, this);
        searchView = findViewById(R.id.search_view);
        switcher = findViewById(R.id.switchOn);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getNews("All", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCategory.setLayoutManager(linearLayoutManager);

        rvCategory.setAdapter(adapterCategory);


        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(adapterNews);
        getCategories();
        getNews("All", null);
        adapterNews.notifyDataSetChanged();


        getSwitchOn();

    }

    private void getSwitchOn() {
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        nightMode = sharedPreferences.getBoolean("night", false);

        if (nightMode) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putBoolean("night", false);
                    nightMode = false;  // Update the nightMode variable
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putBoolean("night", true);
                    nightMode = true;  // Update the nightMode variable
                }
                editor.apply();
            }
        });
    }

    private void getCategories() {

        cateList.add(new RvModel("All", "https://images.unsplash.com/photo-1526470608268-f674ce90ebd4?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        cateList.add(new RvModel("Technology", "https://plus.unsplash.com/premium_photo-1661963212517-830bbb7d76fc?q=80&w=1986&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        cateList.add(new RvModel("Science", "https://cdn.pixabay.com/photo/2019/08/06/22/48/artificial-intelligence-4389372_960_720.jpg"));
        cateList.add(new RvModel("Sports", "https://images.unsplash.com/photo-1612872087720-bb876e2e67d1?q=80&w=1907&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        cateList.add(new RvModel("Business", "https://miro.medium.com/v2/resize:fit:786/format:webp/1*L0Ad7H8OhExXzIBUpv--gw.jpeg"));
        cateList.add(new RvModel("Entertainment", "https://storage.googleapis.com/5paisa-prod-storage/files/2022-07/Entertainment_1.jpg"));
        cateList.add(new RvModel("Health", "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"));
        adapterCategory.notifyDataSetChanged();
    }

    private void getNews(String category, String query) {
        progressBar.setVisibility(View.VISIBLE);
        articleList.clear();
        String cateUrl = "https://newsapi.org/v2/top-headlines?country=in&category=" + category + "&apikey=1f00de996a3f48e0a371ff3683a9de81";
        //   String cateUrl= "https://newsapi.org/v2/top-headlines/sources?category=" + category + "&apiKey=1f00de996a3f48e0a371ff3683a9de81";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apikey=1f00de996a3f48e0a371ff3683a9de81";
        String BASE_UrL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_UrL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<NewsModel> call;

        if (query != null && !query.isEmpty()) {
            String searchUrl = "https://newsapi.org/v2/everything?q=" + query + "&apikey=1f00de996a3f48e0a371ff3683a9de81";
            call = retrofitApi.getAllNews(searchUrl);
        } else if (category.equals("All")) {
            call = retrofitApi.getAllNews(url);
        } else {
            call = retrofitApi.getNewsByCategory(cateUrl);
        }

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                NewsModel newsModel = response.body();
                progressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModel.getArticles();
                for (int i = 0; i < articles.size(); i++) {
                    articleList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getUrl(), articles.get(i).getContent()));
                }
                adapterNews.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "fail to get news", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onCategoryClick(int position) {
        String category = cateList.get(position).getCategory();
        getNews(category, null);

    }
}