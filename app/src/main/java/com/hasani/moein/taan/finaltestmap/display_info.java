package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

public class display_info extends AppCompatActivity {

    private static final String TAG = "TAg";
    private TextView title,description;
    private Button delete;
    private ArrayList<marker_model> modelArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        title=(TextView)findViewById(R.id.titlev2);
        description=(TextView)findViewById(R.id.descriptionv2);
        delete=(Button)findViewById(R.id.delete);

        final DataBaseHandler dbh=new DataBaseHandler(display_info.this);

        modelArrayList=dbh.getObjects();
        Log.d(TAG, "Array List status " +
                "size: "+modelArrayList.size());

        Bundle bundle=getIntent().getExtras();
        final int ID=bundle.getInt("id");

        marker_model mModel=modelArrayList.get(ID);

            title.setText(mModel.getTitle());
            description.setText(mModel.getDescription());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.delete_Object(ID);
                startActivity(new Intent(display_info.this,findonmap.class));
                finish();
            }
        });


    }
}
