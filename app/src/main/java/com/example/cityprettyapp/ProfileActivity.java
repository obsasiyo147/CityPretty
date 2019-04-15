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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private TextView exampleText;
    private Button changeSettings;
    private Button seeServices;
    private Button logoutButton;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();

        //code for navBar
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
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

        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        String name = user.getDisplayName();
        exampleText = (TextView) findViewById(R.id.exampleProfileLogin);
        exampleText.setText("Welcome " + name); //displaying user's first and last name
        logoutButton = (Button) findViewById(R.id.signOutButton);
        changeSettings = (Button) findViewById(R.id.changeSettingsButton);
        seeServices = (Button) findViewById(R.id.service);
        logoutButton.setOnClickListener(this);
        changeSettings.setOnClickListener(this);
        seeServices.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == logoutButton) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if (view == changeSettings) {
            finish();
            startActivity(new Intent(this, ChangeSettings.class));
        }

        if (view == seeServices){
            finish();
            startActivity(new Intent(this, BeauticiansList.class));
        }
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
