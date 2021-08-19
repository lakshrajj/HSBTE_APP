package com.lrs.hsbte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity{

    private AdView mAdView;
    public static final String SHARED_PREFS="SharedPrefs";
    public static final String BRANCH="branch";
    String sub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button com=(Button)findViewById(R.id.button);
        Button textile=(Button)findViewById(R.id.button2);
        Button ele=(Button)findViewById(R.id.button3);
        Button mech=(Button)findViewById(R.id.button4);
        Button tron=(Button)findViewById(R.id.button5);
        Button fashion=(Button)findViewById(R.id.button6);
        Button help=(Button)findViewById(R.id.help);


        com.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Computer";
                Intent intent=new Intent(MainActivity.this,csem.class);
                ok();
                startActivity(intent);
            }
        });
        textile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Textile";
                Intent intent=new Intent(MainActivity.this,csem.class);
                ok();
                startActivity(intent);
            }
        });
        ele.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Electrical";
                Intent intent = new Intent(MainActivity.this, csem.class);
                ok();
                startActivity(intent);
            }
            });
        mech.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Mechanical";
                Intent intent=new Intent(MainActivity.this,csem.class);
                ok();
                startActivity(intent);
            }
        });
        tron.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Electronics";
                Intent intent=new Intent(MainActivity.this,csem.class);
                ok();
                startActivity(intent);
            }
        });
        fashion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sub = "Fashion";
                Intent intent=new Intent(MainActivity.this,csem.class);
                ok();
                startActivity(intent);
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void ok() {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(BRANCH,sub);
        editor.apply();
    }
}

