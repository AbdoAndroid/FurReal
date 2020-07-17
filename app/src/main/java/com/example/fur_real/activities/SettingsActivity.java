package com.example.fur_real.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.fur_real.R;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;
    TextView tvName,tvEmail,tvPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        preferences=getSharedPreferences("user" ,MODE_PRIVATE);
        tvEmail=findViewById(R.id.tv_email);
        tvName=findViewById(R.id.tv_name);
        tvPhone=findViewById(R.id.tv_mobile);
        tvName.setText(preferences.getString("name",""));
        tvPhone.setText(preferences.getString("phone",""));
        tvEmail.setText(preferences.getString("email",""));
    }

    public void SignOutOnClick(View view) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("id","none");
        edit.commit();
        finish();
        startActivity(new Intent(this,StartActivity.class));
    }

}