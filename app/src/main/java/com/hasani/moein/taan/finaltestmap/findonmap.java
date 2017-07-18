package com.hasani.moein.taan.finaltestmap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import DataBaseHandler.DataBaseHandler;
import DataBaseHandler.marker_model;

import static com.hasani.moein.taan.finaltestmap.R.drawable.marker2;

public class findonmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mMarker;
    private ArrayList<Marker> markersArrayList = new ArrayList<>();
    private int id;


    public void reload() {
        DataBaseHandler dbh = new DataBaseHandler(getApplicationContext());
        ArrayList<marker_model> onOpen_array = dbh.getObjects();

        if (!onOpen_array.isEmpty()) {
            for (int i = 0; i < onOpen_array.size(); i++) {

                mMap.addMarker(new MarkerOptions().position(onOpen_array.get(i).getLatLng())
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bluemarker", 60, 100))));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment MmapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        MmapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i = new Intent(findonmap.this, marker_get_info.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("from_position", latLng);
                i.putExtra("bundle", bundle);
                startActivity(i);


                mMarker = mMap.addMarker(new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("bluemarker", 60, 100))));
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
                View view = getLayoutInflater().inflate(R.layout.info, null);
                return view;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(findonmap.this, display_info.class);
                id = markersArrayList.indexOf(marker);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getPackageName()));
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return resizedBitmap;
    }
}