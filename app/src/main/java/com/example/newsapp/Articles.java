package com.example.newsapp;

public class Articles {

     private String  title;
     private String desp;
     private String url;
     private String urlToImage;
     private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Articles(String title, String desp, String url, String urlToImage, String content) {
        this.title = title;
        this.desp = desp;
        this.url = url;
        this.urlToImage = urlToImage;
        this.content = content;
    }
}
