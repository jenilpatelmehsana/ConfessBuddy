package com.example.confessbuddy.DBOperations;


import androidx.annotation.NonNull;

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

    public static void addUserDetailsToDB(User user) {
//        JSONObject userJson = User.convertToJson(user);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDetails  = database.getReference("userDetails");
        DatabaseReference currentUser = userDetails.child(user.getUserID());
        currentUser.setValue(user);

    }


    public static void addConfess(FirebaseAuth mAuth, String confessText) {

//        TODO adding return flag
        if(mAuth == null) {
            System.out.println("user not found, exited");
            return;
        }

        //getting user details

        //TODO solving key problem for userDetails child

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference userRef = db.getReference("userDetails").child("jenilpatel");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot == null) return;
                else {
                    Object obj = snapshot.getValue();
                    System.out.println(obj.toString());
                    User currentUser = User.objectToUser(obj);
                    DatabaseReference confessRef = db.getReference("confess")
                            .child(currentUser.getCity())
                            .child(currentUser.getUniversity())
                            .child(GenerateUniqueConfessID.genereateUID(currentUser.getUserID()));
                    Date dateRN = new Date();
                    confessRef.setValue(new Confess(confessText,currentUser.getCity(),currentUser.getUniversity(),String.valueOf(dateRN.getTime())));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
//    TODO
//    on confess change listner

}
