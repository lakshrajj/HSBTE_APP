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
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QueMain extends AppCompatActivity {
    QueAdapter adapter123,adapter111;
    String sem,sub;
    DatabaseReference reff;

    public static final String SHARED_PREFS="SharedPrefs";
    public static final String SUBSEM="SemSub";
    public static final String SUBJECT="subject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quemain);





        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sub = sharedPreferences.getString(SUBJECT, "");
        sem = sharedPreferences.getString(SUBSEM, "");
        Button name=(Button)findViewById(R.id.nnn);
        Button addque=(Button)findViewById(R.id.addque);
        EditText newque=(EditText)findViewById(R.id.newque);
        name.setText(sub);

        RecyclerView recvieww = (RecyclerView) findViewById(R.id.userListt);
        recvieww.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<models> options =
                new FirebaseRecyclerOptions.Builder<models>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(sem).child(sub), models.class)
                        .build();

        adapter123=new QueAdapter(options);
        recvieww.setAdapter(adapter123);
        adapter123.getSubSem(sem,sub);


        recvieww.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(QueMain.this, "Long Preesed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });




        addque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String que = newque.getText().toString().trim();
                reff = FirebaseDatabase.getInstance().getReference(sem).child(sub);
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
                    Toast.makeText(QueMain.this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    //if the value is not given displaying a toast
                    Toast.makeText(QueMain.this, "Enter A Quetion ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter123.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter123.stopListening();
    }
}
