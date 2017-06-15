package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button mbutton;
    private EditText xcor;
    private EditText ycor;

    private String x;
    private String y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xcor=(EditText)findViewById(R.id.xcordinate);
        ycor=(EditText)findViewById(R.id.ycordinate);

        mbutton=(Button)findViewById(R.id.map);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,MapsActivity.class);
                int a = Integer.parseInt(xcor.getText().toString());
                int b = Integer.parseInt(ycor.getText().toString());
                intent.putExtra("x",a);
                intent.putExtra("y",b);

                startActivity(intent);
            }
        });
    }
}
