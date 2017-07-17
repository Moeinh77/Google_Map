package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final marker_model markerModel=new marker_model();

                DataBaseHandler dbh=new DataBaseHandler(getApplicationContext());

                markerModel.setTitle(title.getText().toString());
                markerModel.setDescription(description.getText().toString());

                Bundle bundle = getIntent().getParcelableExtra("bundle");
                LatLng recived_latlng = bundle.getParcelable("from_position");
                if(recived_latlng!=null){
                    markerModel.setPosition(recived_latlng);
                }
                dbh.AddObject(markerModel);
                finish();
            }
        });
    }

}
