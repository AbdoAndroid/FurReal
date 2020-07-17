package com.example.fur_real.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fur_real.R;

public class ItemActivity extends AppCompatActivity {

    TextView tvName,tvPrice,tvName2,tvDecrease;
    ImageView ivImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        tvName=findViewById(R.id.tv_item_name);
        ivImage=findViewById(R.id.iv_item_image);
        tvName2=findViewById(R.id.tv_item_name2);
        tvPrice=findViewById(R.id.tv_item_price);
        tvDecrease=findViewById(R.id.tv_decrease);
        tvName.setText(bundle.getString("name"));
        tvName2.setText(bundle.getString("name"));
        ivImage.setImageResource(bundle.getInt("image"));
        tvPrice.setText("$"+bundle.getString("price"));
    }

    public void previewOnClick(View view) {
        startActivity(new Intent(this,LinkingActivity.class));
    }

    public void decreaseOnClick(View view) {
    }

    public void increaseOnClick(View view) {

    }
}