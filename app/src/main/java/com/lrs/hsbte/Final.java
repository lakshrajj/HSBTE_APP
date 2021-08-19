package com.lrs.hsbte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Final extends AppCompatActivity {

    private AdView mAdView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbsub1,dbsub2,dbsub3,dbsub4,dbsub5,dbsub6;
    String sub01,sub02,sub03,sub05,sub04,sub06,Pass,str,sem;
    ProgressDialog progressDialog;

    public static final String SHARED_PREFS="SharedPrefs";
    public static final String SUBSEM="SemSub";
    public static final String CHILD="child";
    public static final String SUBJECT="subject";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sem = sharedPreferences.getString(CHILD, "");
        str = sharedPreferences.getString(SUBSEM, "");

        Button sub1 = (Button) findViewById(R.id.button20);
        Button sub2 = (Button) findViewById(R.id.button21);
        Button sub3 = (Button) findViewById(R.id.button22);
        Button sub4 = (Button) findViewById(R.id.button23);
        Button sub5 = (Button) findViewById(R.id.button24);
        Button sub6 = (Button) findViewById(R.id.button25);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        /*Intent intent = getIntent();
        str = intent.getStringExtra("semester");
        String sem = intent.getStringExtra("Xsem");*/

        TextView one = (TextView) findViewById(R.id.textView);
        one.setText("Selected -> "+str);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbsub1 = firebaseDatabase.getReference(str).child(sem).child("sub1");
        dbsub2 = firebaseDatabase.getReference(str).child(sem).child("sub2");
        dbsub3 = firebaseDatabase.getReference(str).child(sem).child("sub3");
        dbsub4 = firebaseDatabase.getReference(str).child(sem).child("sub4");
        dbsub5 = firebaseDatabase.getReference(str).child(sem).child("sub5");
        dbsub6 = firebaseDatabase.getReference(str).child(sem).child("sub6");

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        dbsub1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub1.setText(value);
                sub01 = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Can't Load Subjects", Toast.LENGTH_SHORT).show();
            }
        });
        dbsub2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub2.setText(value);
                sub02 = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Can't Load Subjects", Toast.LENGTH_SHORT).show();
            }
        });
        dbsub3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub3.setText(value);
                sub03 = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Can't Load Subjects", Toast.LENGTH_SHORT).show();
            }
        });
        dbsub4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub4.setText(value);
                sub04 = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Can't Load Subjects", Toast.LENGTH_SHORT).show();
            }
        });
        dbsub5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub5.setText(value);
                sub05 = value;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Sub 5 Not Available", Toast.LENGTH_SHORT).show();
            }
        });
        dbsub6.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                sub6.setText(value);
                sub06 = value;
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Final.this, "Sub 6 Not Available", Toast.LENGTH_SHORT).show();

            }
        });

        sub1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub01;
                ok();
                startActivity(intent);


            }
        });
        sub2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub02;
                ok();
                startActivity(intent);

            }
        });
        sub3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub03;
                ok();
                startActivity(intent);

            }
        });
        sub4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub04;
                ok();
                startActivity(intent);

            }
        });
        sub5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub05;
                ok();
                startActivity(intent);

            }
        });
        sub6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,que.class);
                Pass=sub06;
                ok();
                startActivity(intent);
            }
        });/*
        acc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Final.this,Acc.class);
                ok();
                startActivity(intent);
            }
        });*/

    }
    private void ok() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(SUBJECT,Pass);
        editor.putString(SUBSEM,str);
        editor.apply();
    }
    }
