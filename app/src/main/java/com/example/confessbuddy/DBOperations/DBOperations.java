package com.example.confessbuddy.DBOperations;

import com.example.confessbuddy.Model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

public class DBOperations {

    public static void addUserDetailsToDB(User user) {
        JSONObject userJson = User.convertToJson(user);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userDetails  = database.getReference("userDetails/");
        DatabaseReference currentUser = userDetails.child(user.getUserEmail());
        currentUser.setValue(userJson);

    }

    public static void addConfess() {

    }

//    TODO
//    on confess change listner

}
