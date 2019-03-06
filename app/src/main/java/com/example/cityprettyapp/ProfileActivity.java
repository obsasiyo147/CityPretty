package com.example.cityprettyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth firebaseAuth;
    private TextView exampleText;
    private Button changeSettings;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        exampleText = (TextView) findViewById(R.id.exampleProfileLogin);
        exampleText.setText("Welcome " + user.getEmail());
        logoutButton = (Button) findViewById(R.id.signOutButton);
        changeSettings = (Button) findViewById(R.id.changeSettingsButton);
        logoutButton.setOnClickListener(this);
        changeSettings.setOnClickListener(this);
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
    }
}
