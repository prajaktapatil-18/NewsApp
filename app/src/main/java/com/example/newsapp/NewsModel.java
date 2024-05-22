package com.example.newsapp;

import java.util.ArrayList;

public class NewsModel {
    private String status;
    private int result;
    private ArrayList<Articles>articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }

    public NewsModel(String status, int result, ArrayList<Articles> articles) {
        this.status = status;
        this.result = result;
        this.articles = articles;
    }
}
