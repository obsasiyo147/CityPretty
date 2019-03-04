package com.example.cityprettyapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signInButton;
    private Button registerButton;
    private EditText email;
    private EditText password;
    private TextView forgotPassword; //LOOK INTO THIS
    //firebase auth
    private FirebaseAuth firebaseAuth;
    //progress bar
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.loginRegisterButton);
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        forgotPassword = (TextView) findViewById(R.id.loginForgotPassword); //LOOK INTO THIS

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        signInButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

    }

    private void userLogin() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(userEmail)) {
            //email is empty
            Toast.makeText(this, "Please enter an email.", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }

        if(TextUtils.isEmpty(userEmail)) {
            //password is empty
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            //stopping the function from executing further
        }

        progressDialog.setMessage("Signing in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //user is successfully registered and logged in
                            //lets just display a message rn
                            Toast.makeText(LoginActivity.this, "Signed in successfully.", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Sign in failed.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view == signInButton) {
            userLogin();
        }
        if(view == registerButton) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
