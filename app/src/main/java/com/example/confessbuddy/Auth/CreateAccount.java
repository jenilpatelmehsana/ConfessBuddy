package com.example.confessbuddy.Auth;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.confessbuddy.DBOperations.DBOperations;
import com.example.confessbuddy.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AccountAvaibility{

    private static boolean status = false;

    private static void setStatusTrue() {
        status = true;
    }

    public static boolean createNewAccount(Context currentActivity, FirebaseAuth mAuth, String email, String userID, String password, String city, String university) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) currentActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("Account Creation Log", "createUserWithEmail:success");
                            setStatusTrue();
                        } else {
                            Log.w("Account Creation Log", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(currentActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnSuccessListener((Activity) currentActivity, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                System.out.println("authentication successful");
                User currentUser = new User(mAuth.getCurrentUser().getEmail());
                currentUser.setCity(city);
                currentUser.setUniversity(university);
                currentUser.setUserID(StringOperations.emailToUserID(mAuth.getCurrentUser().getEmail()));
                DBOperations.addUserDetailsToDB(currentUser);
            }
        }).addOnFailureListener((Activity) currentActivity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("authentication failed");
            }
        });
        return status;
    }
}
