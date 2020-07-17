package com.example.fur_real.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.fur_real.R;
import com.unity3d.player.UnityPlayerActivity;

public class LinkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linking);
        try {
            startActivity(new Intent(this, UnityPlayerActivity.class));
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("linking",e.getMessage());
        }
        //startActivity(new Intent(this, UnityPlayerActivity.class));
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}