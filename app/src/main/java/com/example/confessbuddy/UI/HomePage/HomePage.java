package com.example.confessbuddy.UI.HomePage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavHostController;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confessbuddy.CreateConfess;
import com.example.confessbuddy.R;
import com.example.confessbuddy.UI.Authentication.LoginPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import javax.net.ssl.KeyManager;

public class HomePage extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    boolean doubleBack = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addConfess: {
                Context context = this;
                Activity activity = this;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Intent createConfessIntent = new Intent(context, CreateConfess.class);
                        startActivity(createConfessIntent);
                    }
                }).start();

                return true;
            }
            case R.id.logout: {
                FirebaseAuth.getInstance().signOut();
                Intent loginPageIntent = new Intent(this, LoginPage.class);
                startActivity(loginPageIntent);
                this.finish();
            }
            default: {
                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(doubleBack) {
            super.onBackPressed();
            return;
        }
        doubleBack = true;
        Toast.makeText(this, "Press twice to exit application", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("View Confess");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment_layout);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}