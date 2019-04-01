package com.example.cityprettyapp;

import android.app.Service;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ServiceList extends AppCompatActivity{
    private TextView tv;
    DatabaseReference database;
    ArrayList<Services> s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        tv = (TextView) findViewById(R.id.serviceExample);
        s = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Services");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tv.setText(dataSnapshot.getValue().toString());
                /*for (DataSnapshot ds : dataSnapshot.getChildren()){
                    ds.getValue().toString();
                    String name = ds.child("name").getValue().toString();
                    String price = ds.child("price").getValue().toString();
                    String id = ds.child("id").getValue().toString();
                    String description = ds.child("description").getValue().toString();
                    Services newServ = new Services(name,price, description, id);
                    s.add(newServ);
                }*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //ArrayAdapter<Services> arrayAdapter = new ArrayAdapter<Services>(this, R.layout.serv_adapter, s);
        //tv.setAdapter(arrayAdapter);

        //tv.setText(s.get(1).toString());
        //ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.service_list, s);
        //tv.setAdapter(adapter);
    }


}

