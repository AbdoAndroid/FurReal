package com.example.fur_real.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.fur_real.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void chairsCatOnClick(View view) {
        startActivity(new Intent(this,SubCategoryActivity.class));
    }

    public void backOnClick(View view) {
        onBackPressed();
    }
}