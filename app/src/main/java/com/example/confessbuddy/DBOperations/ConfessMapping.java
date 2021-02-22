package com.example.confessbuddy.DBOperations;

import androidx.annotation.NonNull;

import com.example.confessbuddy.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;


public class ConfessMapping {

    public static class MappingOperations{

        public static int getLastIndex(Object obj) throws JSONException, NoSuchFieldException, IllegalAccessException {
            JSONObject json = (JSONObject) JSONObject.wrap(obj);
            System.out.println(json);
            return Integer.parseInt(json.getString("lastIndex"));
        }
    }

    public static void addToCity(String ref, String city) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference indexRef = db.getReference("byCity").child(city);
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0)
                {
                    return;
                }
                if(!snapshot.hasChild("lastIndex")) {
                    DatabaseReference lastIndexRef = db.getReference().child("byCity").child(city).child("lastIndex");
                    lastIndexRef.setValue(0);
                    loopBreaker++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0)
                    return;
                Object obj = snapshot.getValue();
                int lastIndex = -1;
                try {
                    lastIndex = MappingOperations.getLastIndex(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(lastIndex == -1) {
                    return;
                }
                System.out.println(lastIndex + "+*+*+*+*+*+*+*+*+*+**+*+*+*+*+*+*+*+*+*+*+*+*+*+*+**+*+*+*+*+*+");
                DatabaseReference insertionRef = db.getReference("byCity").child(city).child(String.valueOf(++lastIndex));
                insertionRef.setValue(ref);
                DatabaseReference lastIndexUpdateRef = db.getReference("byCity").child(city).child("lastIndex");
                lastIndexUpdateRef.setValue(lastIndex);
                System.out.println("maping done for city");
                loopBreaker++;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void addToUniversity(String ref, String university) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference indexRef = db.getReference("byUniversity").child(university);
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0) {
                    return;
                }
                if(!snapshot.hasChild("lastIndex")) {
                    DatabaseReference lastIndexRef = db.getReference().child("byUniversity").child(university).child("lastIndex");
                    lastIndexRef.setValue(0);
                    loopBreaker++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0)
                    return;
                Object obj = snapshot.getValue();
                int lastIndex = -1;
                try {
                    lastIndex = MappingOperations.getLastIndex(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(lastIndex == -1) {
                    return;
                }
                System.out.println(obj.toString());
                try {
                    lastIndex = ConfessMapping.MappingOperations.getLastIndex(obj);
                    DatabaseReference insertionRef = db.getReference().child("byUniversity").child(university).child(String.valueOf(++lastIndex));
                    insertionRef.setValue(ref);
                    DatabaseReference lastIndexUpdateRef = db.getReference().child("byUniversity").child(university).child("lastIndex");
                    lastIndexUpdateRef.setValue(lastIndex);
                    System.out.println("mapping done for university");
                    loopBreaker++;
                } catch (JSONException | NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //add by time
    public static void addToRecent(String ref) {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference indexRef = db.getReference("byTime");
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0) {
                    return;
                }
                if(!snapshot.hasChild("lastIndex")) {
                    DatabaseReference lastIndexRef = db.getReference("byTime").child("lastIndex");
                    lastIndexRef.setValue(0);
                    loopBreaker++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        indexRef.addValueEventListener(new ValueEventListener() {
            int loopBreaker = 0;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(loopBreaker > 0)
                    return;
                Object obj = snapshot.getValue();
                int lastIndex = -1;
                try {
                    lastIndex = MappingOperations.getLastIndex(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if(lastIndex == -1) {
                    return;
                }
                try {
                    lastIndex = ConfessMapping.MappingOperations.getLastIndex(obj);
                    DatabaseReference insertionRef = db.getReference().child("byTime").child(String.valueOf(++lastIndex));
                    insertionRef.setValue(ref);
                    DatabaseReference lastIndexUpdateRef = db.getReference("byTime").child("lastIndex");
                    lastIndexUpdateRef.setValue(lastIndex);
                    System.out.println("mapping done for time");
                    loopBreaker++;
                } catch (JSONException | NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void addConfessToAllMap(DatabaseReference snapshot, User user) throws InterruptedException {
        String ref = snapshot.toString();
        ConfessMapping.addToRecent(ref);
//        Thread.currentThread().sleep(1000);
        ConfessMapping.addToCity(ref, user.getCity());
//        Thread.currentThread().sleep(1000);
        ConfessMapping.addToUniversity(ref, user.getUniversity());
//        Thread.currentThread().sleep(1000);
    }
}
