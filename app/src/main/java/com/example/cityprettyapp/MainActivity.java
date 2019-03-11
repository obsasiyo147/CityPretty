package com.example.cityprettyapp;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Button
    private Button registerButton;

    //EditTexts
    private EditText userEmail;
    private EditText userPassword;
    private EditText firstName;
    private EditText lastName;

    //TextViews
    private TextView alreadyRegistered;

    //Other Variables
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        userEmail = (EditText) findViewById(R.id.signUpEmail);
        userPassword = (EditText) findViewById(R.id.signUpPassword);
        alreadyRegistered = (TextView) findViewById(R.id.signUpAlreadyRegistered);

        //name editTexts
        firstName = (EditText) findViewById(R.id.registerFirstName);
        lastName = (EditText) findViewById(R.id.registerLastName);


        registerButton.setOnClickListener(this);
        alreadyRegistered.setOnClickListener(this);

    }

    private void registerUser() {
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter an email.", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }

        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }

        progressDialog.setMessage("Registering you...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            String fullName = firstName.getText().toString().trim() + " " +  lastName.getText().toString().trim(); //combining first and last name
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build(); //creating UserProfileChangeRequest object to update name
                            FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates); //setting the user's display name
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            progressDialog.hide();
                        } else {
                            Toast.makeText(MainActivity.this, "Could not register.", Toast.LENGTH_SHORT).show();
                            System.out.println(task.getException().getMessage().toString());
                            progressDialog.hide();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == registerButton) {
            registerUser();
        }

        if(view == alreadyRegistered) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
