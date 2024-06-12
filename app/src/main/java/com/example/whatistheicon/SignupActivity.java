package com.example.whatistheicon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signupButton;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);

        // Set click listener for sign up button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the entered username, email, and password
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform sign-up validation
                if (isValidSignup(username, email, password)) {
                    // Create a new user with email and password
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign up success
                                        FirebaseUser user = firebaseAuth.getCurrentUser();
                                        Toast.makeText(SignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                        // Proceed to the next activity or perform other actions
                                    } else {
                                        // Sign up failed
                                        if (task.getException() instanceof FirebaseAuthException) {
                                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                                            Toast.makeText(SignupActivity.this, "Sign up failed: " + errorCode, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignupActivity.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                } else {
                    // Sign up failed
                    Toast.makeText(SignupActivity.this, "Invalid sign up details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidSignup(String username, String email, String password) {
        // Perform your sign-up validation logic here
        // You can check against validation rules (e.g., username length, email format)

        // For demonstration purposes, let's assume a valid sign-up if all fields are non-empty
        return !username.isEmpty() && !email.isEmpty() && !password.isEmpty();
    }
}
