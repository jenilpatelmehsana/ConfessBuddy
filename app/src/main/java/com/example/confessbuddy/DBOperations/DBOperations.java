package com.example.confessbuddy.DBOperations;


import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.confessbuddy.Auth.StringOperations;
import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Date;

public class DBOperations {

    private static void copyUser(User to, User from)
    {
        to.setUserID(from.getUserID());
        to.setUserEmail(from.getUserEmail());
        to.setConfessCount(from.getConfessCount());
        to.setUniversity(from.getUniversity());
        to.setCity(from.getCity());
    }

    public static void addUserDetailsToDB(User user) {
//        JSONObject userJson = User.convertToJson(user);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDetails  = database.getReference("userDetails");
        DatabaseReference currentUser = userDetails.child(user.getUserID());
        currentUser.setValue(user);
    }


    public static void addConfess(Activity currentActivity, FirebaseAuth mAuth, String confessText) {

//        TODO adding return flag
        if(mAuth == null) {
            Toast.makeText(currentActivity, "You must be logged in to confess something", Toast.LENGTH_SHORT).show();
            return;
        }

        //getting user details

        //TODO solving key problem for userDetails child

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference userRef = db.getReference("userDetails").child(StringOperations.emailToUserID(mAuth.getCurrentUser().getEmail()));
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot == null)  {

                }
                else {
                    Object obj = snapshot.getValue();
                    System.out.println(obj.toString());
                    User user = User.objectToUser(obj);
                    String confessID = GenerateUniqueConfessID.genereateUID(user.getUserID());
                    DatabaseReference confessRef = db.getReference("confess")
                            .child(user.getCity())
                            .child(user.getUniversity())
                            .child(confessID);
                    Date dateRN = new Date();
                    Confess newConfess = new Confess(confessText,user.getCity(),user.getUniversity(),String.valueOf(dateRN.getTime()), confessID);

                    confessRef.setValue(newConfess);
                    try {
                        ConfessMapping.addConfessToAllMap(confessRef, user, newConfess);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                 }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //there is no such option yet
            }
        });
    }
//    TODO
//    on confess change listener on less priority

}
