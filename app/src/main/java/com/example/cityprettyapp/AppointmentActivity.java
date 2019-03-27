package com.example.cityprettyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Currently not linked to other pages
// copy and paste in another activity in the manifests/AndroidManifest.xml file
// delete the other. this will start the app on this page rather than the main page
// don't forget to undo this before you push your code.
//  <intent-filter>
//                <action android:name="android.intent.action.MAIN" />
//
//                <category android:name="android.intent.category.LAUNCHER" />
//            </intent-filter>
public class AppointmentActivity extends AppCompatActivity {

    private static final String TAG = "AppointmentActivity";

    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private  Button mLocation;
    private EditText mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        mDisplayDate = (Button) findViewById(R.id.datepicker);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AppointmentActivity.this
                        ,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                SimpleDateFormat fullDate = new SimpleDateFormat("EEE, MMM d, yyyy");
                dialog.setTitle(fullDate.format(cal.getTime()));
                dialog.show();
            }
        });
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        final int min = cal.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener mTime = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(view.isShown()) {
                    Calendar cal1 = Calendar.getInstance();
                    cal1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    cal1.set(Calendar.MINUTE, minute);

                }
                }
        };

        final TimePickerDialog timePickerDialog = new TimePickerDialog(AppointmentActivity.this,  android.R.style.Theme_Holo_Light_Dialog_NoActionBar, mTime, hour, min, false);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // this is where you can save the date for when the database is made
                timePickerDialog.show();
            }
        };

        mLocation = (Button) findViewById(R.id.location);
       // for google maps mAddress = (EditText) findViewById(R.id.address);
        //pop up window for



// Set an EditText view to get user input


       /* final AlertDialog.Builder addressInputAlertBox = new AlertDialog.Builder(this);
        addressInputAlertBox.setTitle("Enter you Address");
        final EditText input = new EditText(this);
        addressInputAlertBox.setView(input);
        addressInputAlertBox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Do something with value!
            }
        });
        addressInputAlertBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });*/

        mLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder addressInputAlertBox = new AlertDialog.Builder(AppointmentActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.addresbox,null);
                final EditText mAddressInput = (EditText) mView.findViewById(R.id.addressInput);
                Button mCancel = (Button) mView.findViewById(R.id.cancel);
                Button mOk = (Button) mView.findViewById(R.id.ok);

                mOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if(!mAddressInput.getText().toString().isEmpty()){
                                Toast.makeText(AppointmentActivity.this, "Address Saved", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(AppointmentActivity.this, "Address Not Saved", Toast.LENGTH_SHORT).show();
                            }
                    }
                });



                addressInputAlertBox.setView(mView);
                final AlertDialog showdialog = addressInputAlertBox.create();
                showdialog.show();

                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showdialog.cancel();
                    }
                });
                // below is for google maps
              //  String lat = "35.149022";
               // String lng = "-90.051628";
                //String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " ("+ "PickUp" + ")";
       //         Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + mAddress.getText().toString()));
        //        getIntent().setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
         //       startActivity(intent);
            }
        });

    }
}
