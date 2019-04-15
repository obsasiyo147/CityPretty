package com.example.cityprettyapp;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeSettings extends AppCompatActivity {

    private Button saveButton;
    private EditText newPassword;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        saveButton = (Button) findViewById(R.id.submitButton);
        newPassword = (EditText) findViewById(R.id.newPassword);
        firebaseAuth = FirebaseAuth.getInstance();


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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserPassword = newPassword.getText().toString().trim();

                if(TextUtils.isEmpty(newUserPassword)) {
                    Toast.makeText(ChangeSettings.this, "Please enter a new password", Toast.LENGTH_SHORT).show();
                }
                else {
                    user.updatePassword(newUserPassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()) {
                                 Toast.makeText(ChangeSettings.this, "Password changed.", Toast.LENGTH_SHORT).show();
                                 finish();
                                 startActivity(new Intent(ChangeSettings.this, ProfileActivity.class));
                             } else {
                                 String error = task.getException().getMessage();
                                 Toast.makeText(ChangeSettings.this, "Error." + error, Toast.LENGTH_SHORT).show();
                             }
                        }
                    });
                }
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
