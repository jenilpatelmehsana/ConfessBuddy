package com.example.confessbuddy.Auth;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
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
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AccountAvaibility{

    boolean result = false;

    public static void createNewAccount(Context currentActivity, FirebaseAuth mAuth, String email, String password) {

//        TODO
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) currentActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Account Creation Log", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Account Creation Log", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(currentActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                }).addOnSuccessListener((Activity) currentActivity, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                System.out.println("authentication successful");
                User currentUser = new User(mAuth.getCurrentUser().getEmail());
                DBOperations.addUserDetailsToDB(currentUser);
            }
        }).addOnFailureListener((Activity) currentActivity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("authentication failed");
            }
        });
    }

}
