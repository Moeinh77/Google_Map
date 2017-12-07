package com.hasani.moein.taan.events;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import static android.net.sip.SipErrorCode.TIME_OUT;

public class loading extends AppCompatActivity {
    private static int TIME_OUT = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(loading.this, Main_MAP.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
