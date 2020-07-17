package com.example.fur_real.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.example.fur_real.R;
import com.example.fur_real.adapters.CategoryAdapter;
import com.example.fur_real.model.Category;
import com.example.fur_real.model.Item;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_categories;
    List<Category> categories;
    CategoryAdapter adapter;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String currentPhotoPath;

//ToDo remove after after executing once
//Description that was for adding items
    //root database ref
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //ref of database donation to add the process inside
    DatabaseReference dataRefItem = database.getReference().child("item");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Log.i("mainError","1");
        //making the list of categories horizontal
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rv_categories = findViewById(R.id.rv_categories);
        Log.i("mainError","2");
        rv_categories.setLayoutManager(layoutManager);
        Log.i("mainError","3");
        //
        //TODO:  add categories here ,
        // so in future we can add more here as follows
        //
        categories=new LinkedList<>();
        categories.add(new Category("Living room furniture", R.drawable.rec));
        categories.add(new Category("BedRoom furniture", R.drawable.bed));
        categories.add(new Category("Kitchen Room Furniture", R.drawable.kech));
        categories.add(new Category("Bathroom Furniture", R.drawable.bathroom));
        Log.i("mainError","4");
        //
        // linking the list of categories with the view(the recycle view)
        //
        adapter=new CategoryAdapter(categories,this);
        Log.i("mainError","5");
        rv_categories.setAdapter(adapter);
        Log.i("mainError","11");



        //for adding items
        dataRefItem.removeValue();
        List<Item> items=new LinkedList<>();
        items.add(new Item("Shell Chair","219.0","chairs",R.drawable.shellchair));
        items.add(new Item("Varmora Chair","119.0","chairs",R.drawable.varmorachair));
        items.add(new Item("Rocky Chair","199.0","chairs",R.drawable.rockingchair));
        items.add(new Item("Wood Chair","169.0","chairs",R.drawable.woodchair));
        items.add(new Item("Wingback Chair","189.0","chairs",R.drawable.wingback));
        items.add(new Item("Bolla Chair","225.0","chairs",R.drawable.bollachair));
        for (Item item :
                items) {
            item=new Item(dataRefItem.push().getKey(),item.get_name(),item.getPrice(),item.getCategory(),item.getImage());
            dataRefItem.child(item.getId()).setValue(item);
        }



    }

    public void settingsIconOnClick(View view) {
        startActivity(new Intent(this,SettingsActivity.class));
    }


    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.CAMERA  }, 1 );
        }else{
            checkStoragePermission();
        }
    }

    private void checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.CAMERA  }, 2 );
        }else{
            dispatchTakePictureIntent();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkStoragePermission();
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                break;
            case 2:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return;
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
           /* if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }*/
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //imageView.setImageBitmap(imageBitmap);
        }
    }

    public void openCamerOnClick(View view) {
        checkPermission();
    }
}