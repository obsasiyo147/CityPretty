package com.example.cityprettyapp;

import android.content.Intent;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAuth firebaseAuth;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauticians_list);
        mListView = (ListView) findViewById(R.id.listMain);
        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.drawer_layout);
        //nathan's code for navbar
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                myToolBar,
                R.string.Open,
                R.string.Close
        ) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                drawerLayout.closeDrawers();
            }
        };
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.services_page) {
                    startActivity(new Intent(getApplicationContext(), PayPalActivity.class));
                }

                if(id == R.id.beauticians_page) {
                    startActivity(new Intent(getApplicationContext(), BeauticiansList.class));
                }

                if(id == R.id.settings_page) {
                    startActivity(new Intent(getApplicationContext(), ChangeSettings.class));
                }

                if(id == R.id.profile_page) {
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }

                if(id == R.id.appointments_page) {
                    startActivity(new Intent(getApplicationContext(), AppointmentActivity.class));
                }

                if(id == R.id.about_city_pretty) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse("http://www.citypretty.com"));
                    startActivity(intent);
                }

                if(id == R.id.logout_button) {
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Signing you out", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });


        //get all the stores for firebase
        Store store1 = new Store("38002", "Beauticians Store 1", "1");
        Store store2 = new Store("38012", "Beauticians Store 2", "2");
        Store store3 = new Store("38022", "Beauticians Store 3", "3");
        Store store4 = new Store("38032", "Beauticians Store 4", "4");
        Store store5 = new Store("38042", "Beauticians Store 5", "5");
        Store store6 = new Store("38152", "Beauticians Store 1", "6");
        Store store7 = new Store("38132", "Beauticians Store 2", "7");
        Store store8 = new Store("38162", "Beauticians Store 3", "8");
        Store store9 = new Store("38172", "Beauticians Store 4", "9");
        Store store10 = new Store("38102", "Beauticians Store 5", "10");

        ArrayList<Store>  storeList = new ArrayList<>();
        storeList.add(store1);
        storeList.add(store2);
        storeList.add(store3);
        storeList.add(store4);
        storeList.add(store5);
        storeList.add(store6);
        storeList.add(store7);
        storeList.add(store8);
        storeList.add(store9);
        storeList.add(store10);

        StoreListAdapter adapter = new StoreListAdapter(this, R.layout.listviewcustom, storeList);
        mListView.setAdapter(adapter);


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
