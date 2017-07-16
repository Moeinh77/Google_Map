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
    private ArrayList<marker_model> mapmarkerList = new ArrayList<>();

    public DataBaseHandler(Context context) {
        super(context, Constans.TABLE_NAME, null, Constans.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Constans.TABLE_NAME +
                " (" + Constans.MARKER_ID + " INTEGER PRIMARY KEY, " +
                Constans.MARKER_TITLE + " TEXT, " + Constans.MARKER_DESCRIPTION + " TEXT ,"
                +Constans.MARKER_LatLang+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constans.TABLE_NAME);
        onCreate(db);
    }

    public void AddMarker(marker_model marker) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constans.MARKER_TITLE, marker.getTitle());
        values.put(Constans.MARKER_DESCRIPTION, marker.getDescription());

        db.insert(Constans.TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "AddMarker: Successfully added to DB");


    }

    public ArrayList<marker_model> getMarkers() {
        markerList.clear();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME
                , new String[]{Constans.MARKER_ID, Constans.MARKER_TITLE,
                        Constans.MARKER_DESCRIPTION}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                marker_model model = new marker_model();
                model.setDescription(cursor.getString(cursor.getColumnIndex(Constans.MARKER_DESCRIPTION)));
                model.setTitle(cursor.getString(cursor.getColumnIndex(Constans.MARKER_TITLE)));
                markerList.add(model);


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return markerList;
    }

    ////////////

    public void addMarker(Marker mapmarker){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constans.MARKER_LatLang, String.valueOf(mapmarker.getPosition()));


        db.insert(Constans.TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "Lat lang: Successfully added to DB");

    }

    public ArrayList<marker_model> getmapMarkers() {
        mapmarkerList.clear();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constans.TABLE_NAME
                , new String[]{Constans.MARKER_ID, Constans.MARKER_LatLang}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
              //  marker.setTitle(cursor.getString(cursor.getColumnIndex(Constans.MARKER_TITLE)));
              //  mapmarkerList.add(marker);


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return markerList;
    }
////////////

}