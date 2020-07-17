package com.example.fur_real.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fur_real.R;
import com.example.fur_real.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class SignUpActivity extends AppCompatActivity {


    EditText et_name,et_email,et_password,et_re_password,et_phone_number;
    Button btn_sign_up;

    //instance of database reference
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference dataRefUser =database.getReference().child("user");
    Query query_existBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_re_password=findViewById(R.id.et_re_password);
        et_phone_number = findViewById(R.id.et_phone_number);
        btn_sign_up =  findViewById(R.id.btnSignUp);

    }

    public void btnSignUpOnClick(View view) {
        //checking if the name has been entered
        if (et_name.getText().toString().length()<3){
            Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_LONG).show();
        }
        //checks if there is a mobile value has entered correctly
        else if (et_phone_number.getText().toString().length()<11&&et_phone_number.getText().toString().length()>14){
            Toast.makeText(getApplicationContext(),"Please enter a right mobile",Toast.LENGTH_LONG).show();
        }
        //checking if the password is short
        else if (et_password.getText().toString().length()<6){
            Toast.makeText(getApplicationContext(),"The password is too short, try to make a longer one for your security",Toast.LENGTH_LONG).show();
        }
        // checks if the 2 passwords are the same
        else if (!et_password.getText().toString().trim().equals( et_re_password.getText().toString().trim())){
            Toast.makeText(getApplicationContext(),("password1 " + et_password.getText() +" password 2 "+et_re_password.getText()),Toast.LENGTH_LONG).show();
        }

        //the the values are complete
        // lets check if the user has registered before
        else{
            //checking if the mobile number has signed before
            query_existBefore=  dataRefUser.orderByChild("email").equalTo(String.valueOf(et_email.getText()));
            query_existBefore.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!dataSnapshot.exists()) {
                        //the number hasn't been registered before so we will create a new account
                        createNewAcc();
                    } else{
                        //the phone number have been registered before
                        Toast.makeText(getApplicationContext(),"This user has registered before",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("ifExistBefore", databaseError.getMessage());
                }
            });
        }
    }

    private void createNewAcc() {
        try {
            String id = dataRefUser.push().getKey();
            User user=new User(id,et_name.getText().toString(),et_email.getText().toString(),et_password.getText().toString(),et_phone_number.getText().toString());
            dataRefUser.child(id).setValue(user);
            Toast.makeText(getApplicationContext(),"Added Successfully, you can login now",Toast.LENGTH_LONG).show();
            finish();
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    public void backOnClick(View view) {
        onBackPressed();
    }
}
