package com.hasani.moein.taan.finaltestmap;

import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

import static android.R.attr.data;

public class findonmap extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG ="";
    private GoogleMap mMap;
    private Marker mMarker;
    private ArrayList<Marker> markersArrayList=new ArrayList<>();
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        DataBaseHandler dataBaseHandler=new DataBaseHandler(getApplicationContext());
        ArrayList<marker_model> in_onCreate_array=dataBaseHandler.getMarkers();

        if(!in_onCreate_array.isEmpty()) {
            for (int i = 0; i < in_onCreate_array.size(); i++) {

               // mMap.addMarker()


            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(findonmap.this, marker_get_info.class);
                startActivity(i);
                MarkerOptions marker = new MarkerOptions();
                mMarker = mMap.addMarker(marker.position(latLng).icon(BitmapDescriptorFactory.
                        fromResource(R.drawable.marker2)));
                Log.d(TAG, "index increased by one ");

                markersArrayList.add(mMarker);

            }
        });


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(findonmap.this, display_info.class);
                id=markersArrayList.indexOf(marker);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });



//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                Intent intent = new Intent(findonmap.this, display_info.class);
//                id=markersArrayList.indexOf(marker);
//                intent.putExtra("id",id);
//                startActivity(intent);
//                return true;
//            }
//        });



    }
}