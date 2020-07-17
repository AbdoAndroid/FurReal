package com.example.fur_real.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.fur_real.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class StartActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "start");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);




        //getting the data inside file "user" from shared pref which contains the session info
        sharedPreferences= getSharedPreferences("user" ,MODE_PRIVATE);
        String userExist= sharedPreferences.getString("id","none");
        //Toast.makeText(this, userExist, Toast.LENGTH_SHORT).show();
        if (!userExist.equals("none")){
            finish();
            startActivity(new Intent(this,IntroActivity.class));
        }
    }





    public void btnSignInOnClick(View view) {
        startActivity(new Intent(this,SignInActivity.class));
    }

    public void btnSignUpOnClick(View view) {
        startActivity(new Intent(this,SignUpActivity.class));
    }
}
