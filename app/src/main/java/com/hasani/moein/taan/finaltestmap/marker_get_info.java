package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

public class marker_get_info extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private Button submit;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_get_info);

        submit=(Button)findViewById(R.id.submit);
        description=(EditText)findViewById(R.id.description);
        title=(EditText)findViewById(R.id.Title);

        final marker_model markerModel=new marker_model();
        try {
            markerModel.setTitle(title.getText().toString());
            markerModel.setDescription(description.getText().toString());

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Please Enter A Title ",Toast.LENGTH_SHORT);
        }

        DataBaseHandler dbh=new DataBaseHandler(getApplicationContext());
        dbh.AddMarker(markerModel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
