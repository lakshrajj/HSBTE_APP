package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.orhanobut.dialogplus.DialogPlus;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);



        Button main=(Button)findViewById(R.id.button13);
        Button paper=(Button)findViewById(R.id.button14);
        Button notice=(Button)findViewById(R.id.button15);
        Button login=(Button)findViewById(R.id.button16);
        Button help=(Button)findViewById(R.id.help);
        Button signout=(Button)findViewById(R.id.signout);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mFirebaseAuth;
                FirebaseAuth.AuthStateListener mAuthStateListener;
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Select.this, Select.class);
                startActivity(intent);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select.this, MainActivity.class);
                startActivity(intent);
            }
        });

        paper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a="Prepaper";
                Intent intent = new Intent(Select.this, Acc.class);
                intent.putExtra("main",a);
                startActivity(intent);
            }
        });
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a="Announcement";
                Intent intent=new Intent(Select.this,Acc.class);
                intent.putExtra("main",a);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Select.this,admina.class);
                startActivity(intent);
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select.this, Help.class);
                startActivity(intent);

            }
        });

        if( FirebaseAuth.getInstance().getCurrentUser() == null )
        {
            login.setVisibility(View.VISIBLE);
            signout.setVisibility(View.GONE);
        }else {
            login.setVisibility(View.GONE);
            signout.setVisibility(View.VISIBLE);
        }
    }
}