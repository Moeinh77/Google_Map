package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mbutton;
    private EditText xcor;
    private EditText ycor;
    private Button find;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      ;

//        find.setText("Open the Map");
        find = (Button) findViewById(R.id.find);
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //try {
                    Intent intent = new Intent(MainActivity.this, findonmap.class);
//                    float a = Float.parseFloat(xcor.getText().toString());
//                    float b = Float.parseFloat(ycor.getText().toString());
//                    intent.putExtra("x", a);
//                    intent.putExtra("y", b);

                    startActivity(intent);
//                } catch (NumberFormatException e){
//                    Toast.makeText(getApplicationContext(),"You didn't Enter Cordinates",Toast.LENGTH_SHORT).show();
//
//                }

            }
        });



        mbutton = (Button) findViewById(R.id.map);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /// try {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class);
//                    float a = Float.parseFloat(xcor.getText().toString());
//                    float b = Float.parseFloat(ycor.getText().toString());
//                    intent.putExtra("x", a);
//                    intent.putExtra("y", b);

                    startActivity(intent);
              //  } catch (NumberFormatException e){
              //      Toast.makeText(getApplicationContext(),"You didn't Enter Cordinates",Toast.LENGTH_SHORT).show();

         //       }

            }
        });
    }

}
