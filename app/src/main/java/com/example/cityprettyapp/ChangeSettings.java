package com.example.cityprettyapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_settings);

        firebaseAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        saveButton = (Button) findViewById(R.id.submitButton);
        newPassword = (EditText) findViewById(R.id.newPassword);

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
}
