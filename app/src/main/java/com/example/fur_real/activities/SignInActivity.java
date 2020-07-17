package com.example.fur_real.activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fur_real.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class SignInActivity extends AppCompatActivity {

    EditText et_email,et_password;
    Button btnSignIn;

    //shared preferences to save the session info
    SharedPreferences sharedPreferences;
    // for checking if the user exists already and getting his info
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataRefUser =database.getReference().child("user");
    Query query_userExist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btnSignIn =  findViewById(R.id.btnSignIn);
        sharedPreferences= getSharedPreferences("user" ,MODE_PRIVATE);
    }

    public void btnSignInOnClick(View view) {
        query_userExist=  dataRefUser.orderByChild("email").equalTo(String.valueOf(et_email.getText()));
        query_userExist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()) {
                    //the number hasn't been registered
                    Toast.makeText(getApplicationContext(),"This email hasn't registered before  ",Toast.LENGTH_LONG).show();

                } else{//the phone number already exists, checking on the password
                    try {
                        Map<String, Map> root = (Map) dataSnapshot.getValue();
                        Collection<Map> userData=  root.values();
                        //Log.i("donations",donations.toString());
                        Map<String, String> child=new HashMap<>();
                        for (Map<String,String> item:userData) {
                            child =item;
                            break;
                        }
                        //Map<String, String> child =root.get(et_phoneNum.getText().toString());
                        //Log.i("fullName",/*map+"  "+*/"UserCreated");
                        String id="";
                        for (String key : root.keySet()){
                            id=key;
                            break;
                        }
                        if (child.get("password").equals(et_password.getText().toString())){
                            SharedPreferences.Editor edit = sharedPreferences.edit();
                            edit.putString("id",id);
                            edit.putString("name",child.get("name"));
                            edit.putString("phone",child.get("phoneNumber"));
                            edit.putString("password",child.get("password"));
                            edit.putString("email",child.get("email"));
                            edit.commit();

                            String userExist= sharedPreferences.getString("id","none");
                            //Toast.makeText(SignInActivity.this, userExist, Toast.LENGTH_SHORT).show();

                            //Toast.makeText(getApplicationContext(),"Correct Password",Toast.LENGTH_LONG).show();
                            Log.d("logIn","completed");
                            finish();
                            startActivity(new Intent(SignInActivity.this,IntroActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Wrong Password ",Toast.LENGTH_LONG).show();
                        }
                    }catch (Exception ex){
                        Log.d("logIn",ex.getMessage());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }


    public void btnBackOnClick(View view) {
        onBackPressed();
    }
}
