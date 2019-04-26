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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


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
        final String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        // John's Additions
        final String fname = firstName.getText().toString().trim();
        final String lname = lastName.getText().toString().trim();

        // John --> 16 possible combinations on how user may interact with registration form.
        // 4 choose 4 --> 1 combination !!
        //Trying to create an account with nothing entered.
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password) && TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname)) {
            //Everything is empty.
            Toast.makeText(this, "Nothing is filled.", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }

        // 4 choose 3 --> 4 combinations
        // Missing fname, lname, and email
        else if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter full name and email.", Toast.LENGTH_SHORT).show();

        }
        // Missing fname, lname, and password
        else if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname) && TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter full name and password.", Toast.LENGTH_SHORT).show();

        }
        // Missing fname, email, and password
        else if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(password) && TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter first name, password, and email.", Toast.LENGTH_SHORT).show();

        }
        // Missing lname, email, and password
        else if (TextUtils.isEmpty(lname) && TextUtils.isEmpty(password) && TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter last name, password, and email.", Toast.LENGTH_SHORT).show();

        }
        // 4 choose 2 --> 6 combinations !!
        // Missing email and password
        else if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter email and password.", Toast.LENGTH_SHORT).show();

        }
        // Missing fname and email
        else if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter first name and email.", Toast.LENGTH_SHORT).show();

        }

        // Missing lname and email
        else if (TextUtils.isEmpty(lname) && TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter last name and email.", Toast.LENGTH_SHORT).show();

        }
        // Missing fname and password
        else if (TextUtils.isEmpty(fname) && TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter first name and password.", Toast.LENGTH_SHORT).show();

        }
        // Missing lname and password
        else if (TextUtils.isEmpty(lname) && TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter last name and password.", Toast.LENGTH_SHORT).show();

        }
        // Missing fname and lname.
        else if(TextUtils.isEmpty(fname) && TextUtils.isEmpty(lname))
        {
            Toast.makeText(this, "Please enter your full name.", Toast.LENGTH_SHORT).show();

        }

        // 4 choose 1 --> 4 combinations !!
        // Missing first name.
        else if (TextUtils.isEmpty(fname)) {
            //email is empty
            Toast.makeText(this, "Please enter your first name.", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }
        //Missing last name.
        else if (TextUtils.isEmpty(lname)) {
            Toast.makeText(this, "Please enter your last name.", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }
        // Missing password.
        else if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }
        // Missing email.
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
        }

        // Register form completely filled.
        else {


            progressDialog.setMessage("Registering you...");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                String fullName = firstName.getText().toString().trim() + " " + lastName.getText().toString().trim(); //combining first and last name
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fullName).build(); //creating UserProfileChangeRequest object to update name
                                FirebaseAuth.getInstance().getCurrentUser().updateProfile(profileUpdates); //setting the user's display name
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                progressDialog.hide();
                            }

                            else if(userPassword.length() < 6) {
                                Toast.makeText(MainActivity.this, "Password must be 6 characters or more.", Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                            }

                            else if(!(email.contains("@"))) {
                                Toast.makeText(MainActivity.this, "Enter a valid email", Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                            }

                            else if(fname.length() <= 1 || lname.length() <= 1) {
                                Toast.makeText(MainActivity.this, "Enter a valid first/last name.", Toast.LENGTH_SHORT).show();
                                progressDialog.hide();
                            }

                            else {
                                Toast.makeText(MainActivity.this, "Could not register. Check your fields again.", Toast.LENGTH_SHORT).show();
                                System.out.println(task.getException().getMessage());
                                progressDialog.hide();
                            }
                        }
                    });
        }
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
