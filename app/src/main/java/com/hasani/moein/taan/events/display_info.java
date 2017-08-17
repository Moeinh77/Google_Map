package com.hasani.moein.taan.events;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;
import Map_Utilities.DbBitmapUtility;

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
            image.setImageBitmap(getRoundedCornerBitmap(DbBitmapUtility.getImage(
                    mModel.getBitmap())));


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbh.delete_Object(mModel.getId());//changed to mModel.getId()******
                                                  // solved the delete problem
                finish();
            }
        });


    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {//gerd kardan dore aks
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 20;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    @Override
    protected void onDestroy() {
        startActivity(new Intent(display_info.this,Main_MAP.class));//choon be azay baz shodan in activity activity map baste shod
                                                                     //dar natije moghe kharej shodan az in activity bayad
                                                                     // dobare map ra baz konim
        finish();
        super.onDestroy();
    }
    ////////////////////////end
}
