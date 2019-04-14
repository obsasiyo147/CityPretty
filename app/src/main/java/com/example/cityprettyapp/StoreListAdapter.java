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
import java.util.List;

public class StoreListAdapter extends ArrayAdapter<Store> {

    private  static final String TAG = "StoreListAdapter";
    private  final Context mContext;
    private int mResource;


    public StoreListAdapter(Context context, int resource, ArrayList<Store> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            String name = getItem(position).getName();
            String address = getItem(position).getAddress();
            final String serviceNumber = getItem(position).getServiceNumber();

            Store store = new Store(address, name, serviceNumber );
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
             TextView text1 =(TextView) convertView.findViewById(R.id.address);
             TextView text2 =(TextView) convertView.findViewById(R.id.store);
             text1.setText(address);
             text2.setText(name);
        Button setAppointment = (Button) convertView.findViewById(R.id.setAppointment);

        setAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( mContext ,AppointmentActivity.class);
                myIntent.putExtra("serviceNumber", serviceNumber);
                mContext.startActivity(myIntent);

            }
        });


        return convertView;
    }
}
