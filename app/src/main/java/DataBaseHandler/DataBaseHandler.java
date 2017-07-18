package DataBaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class DataBaseHandler extends SQLiteOpenHelper {

    private ArrayList<marker_model> markerList = new ArrayList<>();
    private ArrayList<LatLng> LatLngList = new ArrayList<>();


    public DataBaseHandler(Context context) {
        super(context, Constans.TABLE_NAME, null, Constans.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constans.TABLE_NAME +
                " (" + Constans.MARKER_ID + " INTEGER PRIMARY KEY, " +
                Constans.MARKER_TITLE + " TEXT," + Constans.MARKER_DESCRIPTION + " TEXT,"
                + Constans.MARKER_lat + " Double," + Constans.MARKER_lng + " Double);");
        Log.d(TAG, " ***object Table created successfully*** ");

//        db.execSQL("CREATE TABLE " + Constans.LIST_TABLE_NAME + "(" + Constans.MARKER_ID + " INTEGER PRIMARY KEY, "
//                + Constans.ARRAY_COULMN + ");");
//
//        Log.d(TAG, " ***Array Table created successfully*** ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME);
     //   db.execSQL("DROP TABLE IF EXISTS " + Constans.LIST_TABLE_NAME);
        onCreate(db);
    }


//    public void onDestroy(SQLiteDatabase db){
//        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME);
//    }

    public void AddObject(marker_model model) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constans.MARKER_TITLE, model.getTitle());
        values.put(Constans.MARKER_DESCRIPTION, model.getDescription());
        values.put(Constans.MARKER_lat, model.getLat());
        values.put(Constans.MARKER_lng, model.getLng());

        db.insert(Constans.TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "AddMarker: Successfully added to DB");


    }

    public ArrayList<marker_model> getObjects() {
        markerList.clear();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME
                , new String[]{Constans.MARKER_ID, Constans.MARKER_TITLE,
                        Constans.MARKER_DESCRIPTION, Constans.MARKER_lat, Constans.MARKER_lng},
                null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                marker_model model = new marker_model();

                model.setTitle(cursor.getString(cursor.getColumnIndex(Constans.MARKER_TITLE)));
                model.setDescription(cursor.getString(cursor.getColumnIndex(Constans.MARKER_DESCRIPTION)));

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

    public void delete_Object(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constans.TABLE_NAME, Constans.MARKER_ID + " =?",
                new String[]{String.valueOf(id)});

        Log.d(TAG, "delete: ***one item deleted from db***");
        db.close();
    }
//
//    /////////////////////////////////////////////////////////////////
//
////    public void Add_Array(ArrayList<Marker> markerArrayList){
////        for(int i=0;i<markerArrayList.size())
////    }
//
//    public void Add_Marker(Marker marker) {
//
//        SQLiteDatabase db = getWritableDatabase();
//
//        ContentValues ARRAY_values = new ContentValues();
//        ARRAY_values.put(Constans.ARRAY_MARKER_lat, marker.getPosition().latitude);
//        ARRAY_values.put(Constans.ARRAY_MARKER_lng, marker.getPosition().longitude);
//
//        db.insert(Constans.LIST_TABLE_NAME, null, ARRAY_values);
//    }

    public int Marker_Id(Marker marker) {

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

        int m_id = LatLngList.indexOf(marker.getPosition());
        return m_id;

    }
}