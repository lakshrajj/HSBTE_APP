package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Acc extends AppCompatActivity {
    RecyclerView recview;
    MyAdapter adapter,adapter2;
    String str,a;

    public static final String SHARED_PREFS="SharedPrefs";
    public static final String SUBSEM="SemSub";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        str = sharedPreferences.getString(SUBSEM, "");

        Button admin=(Button)findViewById(R.id.admin);

        if( FirebaseAuth.getInstance().getCurrentUser() == null )
        {
            admin.setVisibility(View.GONE);
        }else {
            admin.setVisibility(View.VISIBLE);
        }

        Intent intent = getIntent();
        String a = intent.getStringExtra("main");

        recview=(RecyclerView)findViewById(R.id.userList);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference(a), model.class)
                        .build();

        adapter=new MyAdapter(options);
        recview.setAdapter(adapter);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putString(SUBSEM,a);
                editor.apply();
                Intent intent=new Intent(Acc.this,QueMain2.class);
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }



}