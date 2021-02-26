package com.example.confessbuddy.UI.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confessbuddy.Auth.LoginAccount;
import com.example.confessbuddy.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    TextView signupText;
    Button loginButton;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void loginUser() {
        String email = emailEditText.getText().toString(),
                password = passwordEditText.getText().toString();
        if(email.compareTo("") == 0 || password.compareTo("") == 0) {
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
            return;
        } else {
            LoginAccount.loginAccount(this, mAuth, email, password);
        }
        if(mAuth.getCurrentUser() != null) {
            Log.d("loginStatus", "loged IN");
        }
    }

    public void registerUser() {
        System.out.println("working");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        this.getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailEditText = findViewById(R.id.emailTextView);
        passwordEditText = findViewById(R.id.passwordTextView);
        signupText = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });



        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
}