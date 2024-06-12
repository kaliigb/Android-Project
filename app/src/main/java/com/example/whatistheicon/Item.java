package com.example.whatistheicon;
public class Item {
    private String title;
    private String imageUrl;

    public Item() {
        // Required empty constructor for Firebase
    }

    public Item(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
