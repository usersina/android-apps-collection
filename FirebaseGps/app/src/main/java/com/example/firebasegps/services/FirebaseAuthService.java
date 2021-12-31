package com.example.firebasegps.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthService {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;

    public Task<AuthResult> login() {
        return mAuth.signInAnonymously();
    }

    public void logout() {
        mAuth.signOut();
    }
}
