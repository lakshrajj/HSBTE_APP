package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QueMain2 extends AppCompatActivity {

        QueAdapter2 adapter1234;
        String sem, sub;
        DatabaseReference reff;

        public static final String SHARED_PREFS = "SharedPrefs";
        public static final String SUBSEM = "SemSub";
        public static final String SUBJECT = "subject";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_quemain2);


            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            sub = sharedPreferences.getString(SUBJECT, "");
            sem = sharedPreferences.getString(SUBSEM, "");
            Button name = (Button) findViewById(R.id.nnn2);
            Button addque = (Button) findViewById(R.id.addque2);
            EditText newque = (EditText) findViewById(R.id.newque2);
            if(sem=="Prepaer"){
                name.setText("Previous Paper");
            }
            else{
            name.setText(sem);}

            RecyclerView recvieww2 = (RecyclerView) findViewById(R.id.userListt2);
            recvieww2.setLayoutManager(new LinearLayoutManager(this));

            FirebaseRecyclerOptions<model2> optionss =
                    new FirebaseRecyclerOptions.Builder<model2>()
                            .setQuery(FirebaseDatabase.getInstance().getReference(sem), model2.class)
                            .build();

            adapter1234 = new QueAdapter2(optionss);
            recvieww2.setAdapter(adapter1234);
            adapter1234.getSubSem(sem);


            recvieww2.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(QueMain2.this, "Long Preesed", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


            addque.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String que = newque.getText().toString().trim();
                    reff = FirebaseDatabase.getInstance().getReference(sem);
                    //checking if the value is provided
                    if (!TextUtils.isEmpty(que)) {

                        //getting a unique id using push().getKey() method
                        //it will create a unique id and we will use it as the Primary Key for our Artist
                        String id = reff.push().getKey();

                        //creating an Artist Object
                        Question question = new Question(que);

                        //Saving the Artist
                        reff.child(id).setValue(question);

                        //setting edittext to blank again
                        newque.setText("");

                        //displaying a success toast
                        Toast.makeText(QueMain2.this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        //if the value is not given displaying a toast
                        Toast.makeText(QueMain2.this, "Enter A Quetion ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


        @Override
        protected void onStart() {
            super.onStart();
            adapter1234.startListening();
        }

        @Override
        protected void onStop() {
            super.onStop();
            adapter1234.stopListening();
        }
    }
