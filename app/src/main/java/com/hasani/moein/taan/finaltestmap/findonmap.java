package com.hasani.moein.taan.finaltestmap;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
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

public class findonmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle recevier = getIntent().getExtras();
        float a = recevier.getFloat("x");
        float b = recevier.getFloat("y");

        LatLng mycordinate = new LatLng(b, a);


        mMap.addMarker(new MarkerOptions().position(mycordinate)
                .icon(BitmapDescriptorFactory.
                        fromResource(R.drawable.marker2)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(35, 51))
                .icon(BitmapDescriptorFactory.
                        fromResource(R.drawable.karimi2)));


//        MapsActivity test = new MapsActivity();
//        test.onMapReady(mMap);

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.indo)
                View view = getLayoutInflater().inflate(R.layout.info, null);
                return view;
            }
        });
        mMap.addPolyline(new PolylineOptions().add(mycordinate,new LatLng(35, 51))
                .width(5).color(Color.GREEN));

        int zoomLevel = 4; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mycordinate, zoomLevel));

    }
}