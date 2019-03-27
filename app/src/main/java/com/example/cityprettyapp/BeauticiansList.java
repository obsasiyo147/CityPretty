package com.example.cityprettyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class BeauticiansList extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauticians_list);
        mListView = (ListView) findViewById(R.id.listMain);
        //get all the stores for firebase
        Store store1 = new Store("38002", "Beauticians Store 1");
        Store store2 = new Store("38012", "Beauticians Store 2");
        Store store3 = new Store("38022", "Beauticians Store 3");
        Store store4 = new Store("38032", "Beauticians Store 4");
        Store store5 = new Store("38042", "Beauticians Store 5");
        Store store6 = new Store("38152", "Beauticians Store 1");
        Store store7 = new Store("38132", "Beauticians Store 2");
        Store store8 = new Store("38162", "Beauticians Store 3");
        Store store9 = new Store("38172", "Beauticians Store 4");
        Store store10 = new Store("38102", "Beauticians Store 5");

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

    }
}
