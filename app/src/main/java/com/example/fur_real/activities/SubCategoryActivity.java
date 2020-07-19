package com.example.fur_real.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.fur_real.R;
import com.example.fur_real.adapters.ItemAdapter;
import com.example.fur_real.model.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SubCategoryActivity extends AppCompatActivity {

    RecyclerView rv_items;
    List<Item> items;
    ItemAdapter adapter;

//TODO : change names
    //root database ref
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //ref of database donation to add the process inside
    DatabaseReference dataRefItem =database.getReference().child("item");
    Query queryGetItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        rv_items=findViewById(R.id.rv_items);

        Log.i("subError","2");
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        Log.i("subError","3");
        rv_items.setLayoutManager(layoutManager);
        Log.i("subError","4");
        rv_items.setHasFixedSize(false);
        Log.i("subError","5");


        //getting objects from database
        queryGetItems = dataRefItem.orderByChild("category").equalTo("chairs");
        queryGetItems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Log.i("reachedHere","3");

                    items=new ArrayList<>();
                    Map<String, Map> data = (Map) dataSnapshot.getValue();
                    Collection<Map> values = data.values();
                    Log.i("reachedHere","4 " +values);
                    for (Map<String, Object> item : values) {
                        items.add(new Item(item.get("id").toString(), item.get("_name").toString(), item.get("price").toString(), item.get("category").toString(),
                                Integer.parseInt(item.get("image").toString())));
                    }
                    Log.i("reachedHere", items.toString());
                    adapter = new ItemAdapter(items,SubCategoryActivity.this);
                    rv_items.setAdapter(adapter);


                } catch (Exception e) {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });





        //rv_items.setAdapter(adapter);
    }

    public void backOnClick(View view) {
        finish();
    }
}