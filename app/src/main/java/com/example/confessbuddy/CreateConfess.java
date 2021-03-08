package com.example.confessbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.JsonToken;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.confessbuddy.DBOperations.DBOperations;
import com.example.confessbuddy.DBOperations.GenerateUniqueConfessID;
import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Date;

public class CreateConfess extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText confessEditText;
    Button submitConfess;

    public void uploadConfessToDatabase() {
        String confessText = confessEditText.getText().toString();
        if(confessText.compareTo("") == 0) {
            Toast.makeText(this, "Confess must not be empty", Toast.LENGTH_SHORT).show();
        }
        else {
            Activity activity = this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DBOperations.addConfess(activity, mAuth, confessText);
                }
            }).start();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_confess);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        confessEditText = findViewById(R.id.confessBox);
        submitConfess = findViewById(R.id.submitConfess);

        submitConfess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadConfessToDatabase();
            }
        });

    }
}