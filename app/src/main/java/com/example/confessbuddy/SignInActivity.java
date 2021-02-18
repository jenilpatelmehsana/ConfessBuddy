package com.example.confessbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.confessbuddy.Auth.CreateAccount;
import com.example.confessbuddy.Auth.LoginAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button signInButton;

    public void signInButtonOnClick() {
        CreateAccount.createNewAccount(this, mAuth, emailEditText.getText().toString(), passwordEditText.getText().toString());
        if(mAuth.getCurrentUser() != null) {
            Intent createConfessIntent = new Intent(this, CreateConfess.class);
            startActivity(createConfessIntent);
            this.finish();
        }
        else {
            Toast.makeText(this, "Login Failed \n Try Again", Toast.LENGTH_SHORT).show();
            emailEditText.setText("");
            passwordEditText.setText("");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent createConfessIntent = new Intent(this, CreateConfess.class);
            startActivity(createConfessIntent);
            this.finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signInButton = findViewById(R.id.signIn);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInButtonOnClick();
            }
        });
    }
}