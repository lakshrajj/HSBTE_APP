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

public class que extends AppCompatActivity {
    RecyclerView recview;
    MyAdapter adapter;
    String sem,sub;
    Button name;

    public static final String SHARED_PREFS="SharedPrefs";
    public static final String SUBSEM="SemSub";
    public static final String SUBJECT="subject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_que);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sub = sharedPreferences.getString(SUBJECT, "");
        sem = sharedPreferences.getString(SUBSEM, "");

        Button admin=(Button)findViewById(R.id.admin);
        name=(Button)findViewById(R.id.nn);
        name.setText(sub);

        if( FirebaseAuth.getInstance().getCurrentUser() == null )
        {
            admin.setVisibility(View.GONE);
        }else {
            admin.setVisibility(View.VISIBLE);
        }



        recview=(RecyclerView)findViewById(R.id.userList);


        recview.setLayoutManager(new LinearLayoutManager(this));

            FirebaseRecyclerOptions<model> options =
                    new FirebaseRecyclerOptions.Builder<model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child(sem).child(sub), model.class)
                            .build();

            adapter=new MyAdapter(options);
            recview.setAdapter(adapter);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(que.this,QueMain.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

}