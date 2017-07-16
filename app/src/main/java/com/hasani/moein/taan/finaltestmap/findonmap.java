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
    private ArrayList<Marker> markers=new ArrayList<>();
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        
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
                markers.add(mMarker);

            }
        });


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(findonmap.this, display_info.class);
                id=markers.indexOf(marker);
                intent.putExtra("id",id);
                startActivity(intent);
                return true;
            }
        });

    }
}