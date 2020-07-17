package com.example.fur_real.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.fur_real.R;

public class IntroActivity extends AppCompatActivity {

    ViewPager vpImageViewPager;
    Button btnBack,btnNext;
    int fragIndex;
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        manager=this.getSupportFragmentManager();
        manager.beginTransaction()
                .hide(manager.findFragmentById(R.id.fragmentStyle))
                .show(manager.findFragmentById(R.id.fragmentBrands))
                .hide(manager.findFragmentById(R.id.fragmentIdeas))
                .commit();
        btnBack=findViewById(R.id.btnBack);
        btnNext=findViewById(R.id.btnNext);
        /*
        Log.d("logIn","1");
        vpImageViewPager = findViewById(R.id.pager);
        Log.d("logIn","2");
        TabLayout tabLayout =  findViewById(R.id.tabDots);
        Log.d("logIn","3");
        tabLayout.setupWithViewPager(vpImageViewPager, true);
        Log.d("logIn","4");*/
        fragIndex=0;

    }

    public void btnBackOnClick(View view) {
        fragIndex--;
        checkVisibility();
        if (fragIndex<0){
            this.finish();
        }
        if (fragIndex==2) {
            btnNext.setText(R.string.get_started);
        }else{
            btnNext.setText(R.string.next);
        }
        inflateFragment(fragIndex);
    }

    public void btnNextOnClick(View view) {
        fragIndex++;
        checkVisibility();
        if (fragIndex>0) {
            btnBack.setText(R.string.back);
        }
        if (fragIndex==2||fragIndex==3) {
            btnNext.setText(R.string.get_started);
        }else{
            btnNext.setText(R.string.next);
        }
        inflateFragment(fragIndex);
    }

    void checkVisibility(){
        if (fragIndex==0){
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.INVISIBLE);
        }else if (fragIndex>0){
            btnNext.setVisibility(View.VISIBLE);
            btnBack.setVisibility(View.VISIBLE);
        }
    }

    void inflateFragment(int index){
        switch (index){
            case 0:
                manager.beginTransaction()
                        .hide(manager.findFragmentById(R.id.fragmentStyle))
                        .show(manager.findFragmentById(R.id.fragmentBrands))
                        .hide(manager.findFragmentById(R.id.fragmentIdeas))
                        .commit();

                break;
            case 1:
                manager.beginTransaction()
                        .show(manager.findFragmentById(R.id.fragmentStyle))
                        .hide(manager.findFragmentById(R.id.fragmentBrands))
                        .hide(manager.findFragmentById(R.id.fragmentIdeas))
                        .commit();

                break;
            case 2:
                manager.beginTransaction()
                        .hide(manager.findFragmentById(R.id.fragmentStyle))
                        .hide(manager.findFragmentById(R.id.fragmentBrands))
                        .show(manager.findFragmentById(R.id.fragmentIdeas))
                        .commit();

                break;
            case 3:
                checkPermission();
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
            if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.CAMERA  }, 1 );
            }else{
                finish();
                startActivity(new Intent(this,MainActivity.class));
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    finish();
                    startActivity(new Intent(this,MainActivity.class));
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                }
                return;
        }
    }
}
