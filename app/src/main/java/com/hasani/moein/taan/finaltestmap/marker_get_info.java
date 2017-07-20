package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

public class marker_get_info extends AppCompatActivity {

    private static final String TAG = "TAG";
    private EditText title;
    private EditText description;
    private Button submit;
    private Button choose_bt;
    private int request_code = 1;
    private ImageView testview;
    private marker_model markerModel = new marker_model();
    //private EditText view;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_get_info);

       // view = (EditText) findViewById(R.id.test_text);
        testview = (ImageView) findViewById(R.id.test_image);
        submit = (Button) findViewById(R.id.submit);
        description = (EditText) findViewById(R.id.description);
        title = (EditText) findViewById(R.id.Title);
        choose_bt = (Button) findViewById(R.id.choose_button);

        markerModel.setImageaddress(Uri.parse("null"));//*********test
        choose_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting readable Uri
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,request_code);
                /////////////////////////
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DataBaseHandler dbh = new DataBaseHandler(getApplicationContext());


                if (title.getText().toString().equals("")) {

                    Toast.makeText(getApplicationContext(), "Please enter a title", Toast.LENGTH_LONG).show();
                } else {
                    markerModel.setTitle(title.getText().toString());
                    markerModel.setDescription(description.getText().toString());

                    //passing LatLng through activities
                    Bundle bundle = getIntent().getParcelableExtra("bundle");
                    LatLng recived_latlng = bundle.getParcelable("from_position");
                    ////////
                    markerModel.setPosition(recived_latlng);



                    dbh.AddObject(markerModel);
                    Log.d(TAG, " get_info onClick:Marker added to db ");

                    finish();
                    Intent i=new Intent(marker_get_info.this,findonmap.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == request_code) {
                Uri photoFileUri = data.getData();
                testview.setImageURI(photoFileUri);
                //markerModel.setImageaddress(photoFileUri);

                BitmapDrawable drawable = (BitmapDrawable) testview.getDrawable();//new***
                Bitmap bitmap = drawable.getBitmap();
                markerModel.setBitmap(bitmap);

                Log.d(TAG, "onActivityResult: Uri set *****");
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


}
