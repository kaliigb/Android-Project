package com.example.whatistheicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpTextView = findViewById(R.id.signUpTextView);

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform login validation
                if (isValidLogin(username, password)) {
                    // Use Firebase Authentication to sign in the user
                    firebaseAuth.signInWithEmailAndPassword(username, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Login successful
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        // Proceed to the next activity or perform other actions
                                        if (username.toLowerCase().equals("oday.igbaria.oi@gmail.com")){
                                            Intent intent = new Intent(Login.this, AddIconSignActivity.class);
                                            startActivity(intent);

                                        }
                                        else {
                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    } else {
                                        // Login failed
                                        Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    // Login failed
                    Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for sign up text
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform sign up action (navigate to sign up activity, show sign up dialog, etc.)
                Toast.makeText(Login.this, "Sign up clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        // Perform your login validation logic here
        // You can check against a hardcoded username and password, or validate against a server/database

        // For demonstration purposes, assume the login is always valid
        return true;

    }
}