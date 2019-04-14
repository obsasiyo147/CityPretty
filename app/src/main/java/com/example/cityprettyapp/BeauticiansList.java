package com.example.cityprettyapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.println;

public class BeauticiansList extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauticians_list);
        mListView = (ListView) findViewById(R.id.listMain);

        final List<Store> StoreObjects;
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference services = databaseRef.child("Services");
        services.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Store>  services = new ArrayList<Store>();

                for(DataSnapshot item : dataSnapshot.getChildren() ){
                    services.add(new Store(item.child("store").getValue().toString(), item.child("address").getValue().toString()  , item.getKey()));
                }

                StoreListAdapter adapter = new StoreListAdapter(BeauticiansList.this, R.layout.listviewcustom, services);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
