package com.example.confessbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.confessbuddy.UI.Authentication.LoginPage;
import com.example.confessbuddy.UI.HomePage.HomePage;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null) {
            //go to login page
            Intent loginPageIntent = new Intent(this, LoginPage.class);
            startActivity(loginPageIntent);
            this.finish();
        } else {
            Intent homePageIntent = new Intent(this, HomePage.class);
            startActivity(homePageIntent);
            this.finish();
        }
    }
}