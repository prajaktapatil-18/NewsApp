package com.example.newsapp;

public class RvModel {
    private String category;
    private String categoryImgUrl;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

    public RvModel(String category, String categoryImgUrl) {
        this.category = category;
        this.categoryImgUrl = categoryImgUrl;
    }
}
