package DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;


public class DataBaseHandler extends SQLiteOpenHelper {

    private ArrayList<marker_model> markerList = new ArrayList<>();


    public DataBaseHandler(Context context) {
        super(context, Constans.TABLE_NAME, null, Constans.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constans.TABLE_NAME +
                " (" + Constans.MARKER_ID + " INTEGER PRIMARY KEY, " +
                Constans.MARKER_TITLE + " TEXT," + Constans.MARKER_DESCRIPTION + " TEXT,"
                + Constans.MARKER_lat + " Double," + Constans.MARKER_lng + " Double," + Constans.IMAGE_ADDRESS
                + " TEXT,"+Constans.DATE_NAME+" LONG);");

        Log.d(TAG, " ***object Table created successfully*** ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME);
        onCreate(db);
    }


    public void AddObject(marker_model model) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constans.MARKER_TITLE, model.getTitle());
        values.put(Constans.MARKER_DESCRIPTION, model.getDescription());
        values.put(Constans.MARKER_lat, model.getLat());
        values.put(Constans.MARKER_lng, model.getLng());
        values.put(Constans.IMAGE_ADDRESS, model.getImageaddress().toString());//new *****
        values.put(Constans.DATE_NAME,java.lang.System.currentTimeMillis());//new *****

        db.insert(Constans.TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "AddMarker: Successfully added to DB");


    }

    public ArrayList<marker_model> getObjects() {
        markerList.clear();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME
                , new String[]{Constans.MARKER_ID, Constans.MARKER_TITLE,
                        Constans.MARKER_DESCRIPTION, Constans.MARKER_lat, Constans.MARKER_lng,
                        Constans.IMAGE_ADDRESS,Constans.DATE_NAME},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                marker_model model = new marker_model();

                model.setImageaddress(Uri.parse(cursor.getString(cursor.getColumnIndex(Constans.IMAGE_ADDRESS))));//new *****
                model.setId(cursor.getInt(cursor.getColumnIndex(Constans.MARKER_ID)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(Constans.MARKER_TITLE)));
                model.setDescription(cursor.getString(cursor.getColumnIndex(Constans.MARKER_DESCRIPTION)));

                java.text.DateFormat dateFormat=java.text.DateFormat.getDateInstance();//new ***
                String date_=dateFormat.format
                        (new Date(cursor.getLong(cursor.getColumnIndex(Constans.DATE_NAME))).getTime());


                model.setDate(date_);

                LatLng latlng = new LatLng(
                        cursor.getDouble(cursor.getColumnIndex(Constans.MARKER_lat))
                        , cursor.getDouble(cursor.getColumnIndex(Constans.MARKER_lng))
                );
                model.setPosition(latlng);

                markerList.add(model);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return markerList;
    }

    public int Marker_Id(LatLng latLng) {

        ArrayList<LatLng> LatLngList = new ArrayList<>();//changedit position from top to here *******
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME
                , new String[]{Constans.MARKER_ID, Constans.MARKER_lat, Constans.MARKER_lng},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                LatLng latlng = new LatLng(
                        cursor.getDouble(cursor.getColumnIndex(Constans.MARKER_lat))
                        , cursor.getDouble(cursor.getColumnIndex(Constans.MARKER_lng))
                );
                LatLngList.add(latlng);


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        int m_id = LatLngList.indexOf(latLng);
        return m_id;

    }

    public void delete_Object(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constans.TABLE_NAME, Constans.MARKER_ID + " =?",
                new String[]{String.valueOf(id)});

        Cursor cursor = db.rawQuery("SELECT * FROM " + Constans.TABLE_NAME, null);

        Log.d(TAG, "delete: ***one item deleted from db*** \n" + "  Items Left in DB= " + cursor.getCount());

        db.close();
    }


    ///////////////////////End
}