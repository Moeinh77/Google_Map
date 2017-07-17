package com.hasani.moein.taan.finaltestmap;

import android.app.FragmentTransaction;
import android.app.Instrumentation;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
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

    private GoogleMap mMap;
    private Marker mMarker;
    private static ArrayList<Marker> markersArrayList=new ArrayList<>();
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment MmapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        MmapFragment.getMapAsync(this);
//        mapFragment = (MapFragment) getFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
//
//        if (mapFragment == null) {
//            mapFragment = MapFragment.newInstance();
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.map, mapFragment, MAP_FRAGMENT_TAG);
//            fragmentTransaction.commit();
//        }

        DataBaseHandler dbh=new DataBaseHandler(getApplicationContext());
        ArrayList<marker_model> onCreate_array=dbh.getObjects();
        if(!onCreate_array.isEmpty()) {
          for (int i = 0; i <onCreate_array.size(); i++) {

                mMap.addMarker(new MarkerOptions().position(onCreate_array.get(i).getLatLng())
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2)));
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
                Bundle bundle = new Bundle();
                bundle.putParcelable("from_position",latLng);
                i.putExtra("bundle", bundle);
                startActivity(i);

                mMarker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker2)));
                markersArrayList.add(mMarker);

            }
        });




        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                //View view = getLayoutInflater().inflate(R.layout.info, null);
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View view = getLayoutInflater().inflate(R.layout.info, null);
                return view;
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