package com.example.confessbuddy.UI.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.confessbuddy.Auth.CreateAccount;
import com.example.confessbuddy.Auth.StringOperations;
import com.example.confessbuddy.R;
import com.example.confessbuddy.UI.HomePage.HomePage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {
    private Button singupButton;
    private EditText emailTextView, passwordTextView;
    private Spinner universitySpinner, citySpinner;
    private ArrayList<String> universityDropDownList, cityDropDownList;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private ArrayAdapter<String> universityAdapter, cityAdapter;
    private FirebaseAuth mAuth;
    private String city, university;

    private void fillUniversity(ArrayList<String> universityDropDownList) {
        db.getReference("/byUniversity")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> hm = (Map<String, Object>)snapshot.getValue();
                        for(Map.Entry<String, Object> entry: hm.entrySet()) {
                            universityDropDownList.add(entry.getKey());
                            universityAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SignUpPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void fillCity(ArrayList<String> cityDropDownList) {
        db.getReference("/byCity")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> hm = (Map<String, Object>)snapshot.getValue();
                        for(Map.Entry<String, Object> entry: hm.entrySet()) {
                            cityDropDownList.add(entry.getKey());
                            cityAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(SignUpPage.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void signUp() {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        if(email.compareTo("") == 0 || password.compareTo("") == 0)  {
            Toast.makeText(this, "Enter EmailID and Password", Toast.LENGTH_SHORT).show();
            return;
        }
        city = citySpinner.getSelectedItem().toString();
        university = universitySpinner.getSelectedItem().toString();
        boolean status = CreateAccount.createNewAccount(this,
                mAuth, email,
                StringOperations.emailToUserID((email)),
                password,
                city,
                university);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 3; ++i) {
                    if(status) {
                        changeActivity();
                        return;
                    }
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    private void changeActivity() {
        Intent homePageIntent = new Intent(this, HomePage.class);
        startActivity(homePageIntent);
        this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();
        singupButton = findViewById(R.id.signUpButton);
        emailTextView =  findViewById(R.id.signUpEmailTextView);
        passwordTextView = findViewById(R.id.signUpPasswordTextView);
        universitySpinner = findViewById(R.id.signUpUniversitySpinner);
        citySpinner = findViewById(R.id.singUpCitySpinner);
        universityDropDownList = new ArrayList<>();
        cityDropDownList = new ArrayList<>();
        fillCity(cityDropDownList);
        universityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, universityDropDownList);
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityDropDownList);
        universitySpinner.setAdapter(universityAdapter);
        citySpinner.setAdapter(cityAdapter);
        city = "";
        university = "";
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                fillUniversity(universityDropDownList);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                fillCity(cityDropDownList);
            }
        }).start();
    }
}