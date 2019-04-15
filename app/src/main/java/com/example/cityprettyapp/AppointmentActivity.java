package com.example.cityprettyapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

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

    private DrawerLayout drawerLayout;
    private FirebaseAuth firebaseAuth;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private  Button mLocation;
    private EditText mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView nav_view = findViewById(R.id.nav_view);
        mDisplayDate = (Button) findViewById(R.id.datepicker);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

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

        //nathan's code for navbar
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
