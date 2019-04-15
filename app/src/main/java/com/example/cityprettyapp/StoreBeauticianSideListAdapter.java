package com.example.cityprettyapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class StoreBeauticianSideListAdapter extends ArrayAdapter<StoreBeauticianSide> {
    private  static final String TAG = "StoreBeauticianSideListAdapter";
    private  final Context mContext;
    private int mResource;

    public StoreBeauticianSideListAdapter(Context context, int resource, ArrayList<StoreBeauticianSide> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name = getItem(position).getName();
        String address = getItem(position).getAddress();
        String time = getItem(position).getTime();
        String date = getItem(position).getDate();
        String email = getItem(position).getEmail();

        StoreBeauticianSide store = new StoreBeauticianSide(address, name,time,date,email );
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView text1 =(TextView) convertView.findViewById(R.id.name);
        TextView text2 =(TextView) convertView.findViewById(R.id.email);
        TextView text3 =(TextView) convertView.findViewById(R.id.date);
        TextView text4 =(TextView) convertView.findViewById(R.id.time);
        TextView text5 =(TextView) convertView.findViewById(R.id.address);
        text1.setText(name);
        text2.setText(email);
        text3.setText(date);
        text4.setText(time);
        text5.setText(address);

        Button accept = (Button) convertView.findViewById(R.id.accept);
        Button deny = (Button) convertView.findViewById(R.id.deny);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }
}
