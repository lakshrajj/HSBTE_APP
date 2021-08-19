package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FirebaseAuth mFirebaseAuth;
        FirebaseAuth.AuthStateListener mAuthStateListener;
        FirebaseAuth.getInstance().signOut();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(start.this,Select.class));
                finish();

            }
        },3200);
    }
}