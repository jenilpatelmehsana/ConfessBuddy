package com.example.confessbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.confessbuddy.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

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
//            retriving userData from database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("userDetails");
            DatabaseReference currentUserRef = myRef.child(mAuth.getCurrentUser().getEmail());
            currentUserRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    TODO using sharedPreferences
                    JSONObject userJson = (JSONObject) snapshot.getValue();
                    User user = User.convertToUser(userJson);
                    DatabaseReference cityRef = database.getReference(user.getCity() + '/');
                    DatabaseReference univRef = cityRef.child(user.getUniversity() + '/');
//                    TODO CONTINUE
                    DatabaseReference confessRef = univRef.child()
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(CreateConfess.this, "", Toast.LENGTH_SHORT).show();
                }
            });


//            DatabaseReference myRef = database.getReference("City/");
//            DatabaseReference userRef = myRef.child("Silver Oak University");
//            myRef.setValue(confessText);

//            userRef.setValue(confessText);

            Toast.makeText(this, "Confess uploaded successfully", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_confess);

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