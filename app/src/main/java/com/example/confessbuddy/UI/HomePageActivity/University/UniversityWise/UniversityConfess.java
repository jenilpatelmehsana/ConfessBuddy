package com.example.confessbuddy.UI.HomePageActivity.University.UniversityWise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UniversityConfess extends AppCompatActivity {

    private ListView universityConfessListView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_confess);
        String universityName = getIntent().getStringExtra("UniversityName");
        universityConfessListView = findViewById(R.id.universityWiseConfess);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, this.arrayList);
        universityConfessListView.setAdapter(arrayAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchConfess(universityName);
            }
        }).start();
    }

    private void fetchConfess(String universityName) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byUniversity/" + universityName + "/lastIndex")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int lastInteger = snapshot.getValue(Integer.class);
                        while(lastInteger > 0) {
                            db.getReference("byUniversity/" + universityName + '/' + String.valueOf(lastInteger))
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String path = snapshot.getValue(String.class);
                                            System.out.println(path + "-----------------------");
                                            db.getReference(path)
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            Confess confess = snapshot.getValue(Confess.class);
                                                            arrayList.add(confess.getConfess());
                                                            arrayAdapter.notifyDataSetChanged();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            Log.d("database error", error.getMessage());
                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                            lastInteger--;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        return;
                    }
                });
    }

}