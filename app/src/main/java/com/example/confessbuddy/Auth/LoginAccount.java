package com.example.confessbuddy.Auth;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAccount {

    public static void loginAccount(Context currentActivity, FirebaseAuth mAuth, String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) currentActivity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Log.d("Account Login Status", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        }
                        else
                            {
                            Log.w("Account Login Status", "signInWithEmail:failure", task.getException());
                            Toast.makeText(currentActivity, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            }

                    }
                });
    }

}
