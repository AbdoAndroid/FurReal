package com.example.fur_real.model;

import android.graphics.drawable.Drawable;

public class Category {
    private String name;
    private int imagePath;

    public Category(String name, int imagePath) {
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public int getImagePath() {
        return imagePath;
    }

}
