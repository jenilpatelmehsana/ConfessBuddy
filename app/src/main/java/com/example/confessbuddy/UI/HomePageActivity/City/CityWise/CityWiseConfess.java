package com.example.confessbuddy.UI.HomePageActivity.City.CityWise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CityWiseConfess extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_wise_confess);
        String cityName = getIntent().getStringExtra("cityName");
        listView = findViewById(R.id.cityConfessViewListView);
        arrayList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        new Thread(new Runnable() {
            @Override
            public void run() {
                fetchConfess(cityName);
            }
        }).start();
    }

    private void fetchConfess(String cityName) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byCity/" + cityName + "/lastIndex")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int lastInteger = snapshot.getValue(Integer.class);
                        while(lastInteger > 0) {
                            db.getReference("byCity/" + cityName + '/' + String.valueOf(lastInteger))
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