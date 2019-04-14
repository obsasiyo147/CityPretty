package com.example.cityprettyapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BeauticianSide extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautician_side);

        mListView = (ListView) findViewById(R.id.listMain);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference services = databaseRef.child("Services").child("1").child("request");
        services.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<StoreBeauticianSide> services = new ArrayList<>();

                for(DataSnapshot item : dataSnapshot.getChildren() ){
                    services.add(new StoreBeauticianSide(item.child("Address").getValue().toString(),
                            item.child("Name").getValue().toString(),
                            item.child("Time").getValue().toString(),
                            item.child("Date").getValue().toString(),
                            item.child("Email").getValue().toString()));

                }

                StoreBeauticianSideListAdapter adapter = new StoreBeauticianSideListAdapter(BeauticianSide.this, R.layout.beauticiansidelist, services);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
