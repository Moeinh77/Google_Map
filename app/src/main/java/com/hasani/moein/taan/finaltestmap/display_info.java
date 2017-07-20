package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

public class display_info extends AppCompatActivity {

    private static final String TAG = "TAG";
    private TextView title,description,date;
    private Button delete;
    private ArrayList<marker_model> modelArrayList=new ArrayList<>();
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_info);

        date=(TextView)findViewById(R.id.date);
        image=(ImageView)findViewById(R.id.selected_image);
        title=(TextView)findViewById(R.id.titlev2);
        description=(TextView)findViewById(R.id.descriptionv2);
        delete=(Button)findViewById(R.id.delete);

        final DataBaseHandler dbh=new DataBaseHandler(display_info.this);

        modelArrayList=dbh.getObjects();
        Log.d(TAG, "Array List " +
                "size: "+modelArrayList.size());

        Bundle bundle=getIntent().getExtras();
        final int ID=bundle.getInt("id");


        final marker_model mModel=modelArrayList.get(ID);

            date.setText(mModel.getDate());
            title.setText(mModel.getTitle());
            description.setText(mModel.getDescription());
        if(mModel.getImageaddress().toString().equals("null")){
            Log.d(TAG, "Empty Uri*****");
        } else{
            image.setImageURI(mModel.getImageaddress());
        }


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.delete_Object(mModel.getId());//changed to mModel.getId()******
                                                  // solved the delete problem
                finish();
            }
        });


    }

    @Override
    protected void onDestroy() {
        startActivity(new Intent(display_info.this,findonmap.class));
        finish();
        super.onDestroy();
    }
    ////////////////////////end
}
