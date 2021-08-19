package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;



public class csem extends AppCompatActivity{

    private AdView mAdView;
    public String branch;
    public String str,sem,sub;
    public static final String SHARED_PREFS="SharedPrefs";
    public static final String SUBSEM="SemSub";
    public static final String SUBJECT="Subject";
    public static final String BRANCH="branch";
    public static final String CHILD="child";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csem);


        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        sub = sharedPreferences.getString(BRANCH,"");

        Button sem1=(Button)findViewById(R.id.button7);
        Button sem2=(Button)findViewById(R.id.button8);
        Button sem3=(Button)findViewById(R.id.button9);
        Button sem4=(Button)findViewById(R.id.button10);
        Button sem5=(Button)findViewById(R.id.button11);
        Button sem6=(Button)findViewById(R.id.button12);


        TextView myTextView = (TextView) findViewById(R.id.subject);
        /*Intent intent = getIntent();
        String sub = intent.getStringExtra("subject");*/
        // display the string into textView
        myTextView.setText("Selected ->"+sub);

        switch(sub){
            case ("Computer"):branch="Computer";
                break;
            case ("Textile"):branch="Textile";
                break;
            case ("Electrical"):branch="Electrical";
                break;
            case ("Mechanical"):branch="Mechanical";
                break;
            case ("Electronics"):branch="Electronics";
                break;
            case ("Fashion"):branch="Fashion";
                break;
        }

        sem1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem1";
                sem="Xsem1";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });
        sem2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem1";
                sem="Xsem1";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });
        sem3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem3";
                sem="Xsem3";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });
        sem4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem4";
                sem="Xsem4";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });
        sem5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem5";
                sem="Xsem5";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });
        sem6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                str = branch+"Sem6";
                sem="Xsem6";
                ok();
                Intent intent=new Intent(csem.this,Final.class);
                startActivity(intent);

            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void ok() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();


        editor.putString(SUBSEM,str);
        editor.putString(CHILD,sem);
        editor.apply();
    }
}
