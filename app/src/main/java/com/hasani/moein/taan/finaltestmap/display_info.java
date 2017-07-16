package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

public class display_info extends AppCompatActivity {

    private TextView title,description;
    private ArrayList<marker_model> modelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        title=(TextView)findViewById(R.id.titlev2);
        description=(TextView)findViewById(R.id.descriptionv2);

        DataBaseHandler dbh=new DataBaseHandler(getApplicationContext());

        modelArrayList=dbh.getMarkers();

        Bundle bundle=getIntent().getExtras();
        int ID=bundle.getInt("id");

        marker_model mModel=modelArrayList.get(ID);

            title.setText(String.valueOf(ID));
            description.setText(mModel.getDescription());



    }
}
