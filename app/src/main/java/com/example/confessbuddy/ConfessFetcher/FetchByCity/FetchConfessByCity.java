package com.example.confessbuddy.ConfessFetcher.FetchByCity;

import android.renderscript.Sampler;

import androidx.annotation.NonNull;

import com.example.confessbuddy.DBOperations.ConfessMapping;
import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FetchConfessByCity {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();

    public static ArrayList<Confess> getConfess(String city) {
        ArrayList<Confess> ans = new ArrayList<>();
        DatabaseReference cityRef = db.getReference("byCity").child(city);
        cityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object obj = snapshot.getValue();
                try {
                    int lastIndex = ConfessMapping.MappingOperations.getLastIndex(obj);
                    while(lastIndex > 0) {
                        DatabaseReference indexRef = db.getReference("byCity").child(city).child(String.valueOf(lastIndex));
                        indexRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Object confess = snapshot.getValue();
                                JSONObject confessRefJsonObj = (JSONObject) JSONObject.wrap(confess);
                                try {
                                    String confessRef = confessRefJsonObj.getString(String.valueOf(lastIndex));
                                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(confessRef);
                                    DatabaseReference ref = firebaseDatabase.getReference();
                                    ref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Object obj = snapshot.getValue();
                                            System.out.println(obj.toString());
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return ans;
    }

}
