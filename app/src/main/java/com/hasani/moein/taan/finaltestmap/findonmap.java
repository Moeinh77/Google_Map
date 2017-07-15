package com.hasani.moein.taan.finaltestmap;

import android.app.Instrumentation;
import android.content.Intent;
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

import DataBaseHandler.marker_model;

import static android.R.attr.data;

public class findonmap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker mMarker;

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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //Bundle mBundle=new Bundle();
                //marker_model mModel=(marker_model) marker.getTag();
                Intent intent=new Intent(findonmap.this,display_info.class);
               // Bundle myBundle=new Bundle();
               // myBundle.putSerializable("marker on click",mModel);
                intent.putExtra("id",marker.getId());
                startActivity(intent);
                return true;
            }
        });

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent i=new Intent(findonmap.this,marker_get_info.class);
                startActivity(i);
                MarkerOptions marker=new MarkerOptions();
                mMarker=mMap.addMarker(marker.position(latLng).icon(BitmapDescriptorFactory.
                        fromResource(R.drawable.marker2)));
                //mMarker.setTag(markerModel);
               // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 21));
            }
        });



//        Bundle recevier = getIntent().getExtras();
//        float a = recevier.getFloat("x");
//        float b = recevier.getFloat("y");
//
//        LatLng mycordinate = new LatLng(a,b);
//
//
//        mMap.addMarker(new MarkerOptions().position(mycordinate)
//                .icon(BitmapDescriptorFactory.
//                        fromResource(R.drawable.marker2)));
//        mMap.addMarker(new MarkerOptions().position(new LatLng(35, 51))
//                .icon(BitmapDescriptorFactory.
//                        fromResource(R.drawable.karimi2)));


//        MapsActivity test = new MapsActivity();
//        test.onMapReady(mMap);

//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//            @Override
//            public View getInfoWindow(Marker marker) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//                // ConstraintLayout layout=(ConstraintLayout)findViewById(R.id.indo)
//                View view = getLayoutInflater().inflate(R.layout.info, null);
//                return view;
//            }
//        });
//        mMap.addPolyline(new PolylineOptions().add(mycordinate,new LatLng(35, 51))
//                .width(5).color(Color.GREEN));

         //This goes up to 21
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mycordinate, zoomLevel));

    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if(requestCode==1){
//            if(resultCode==RESULT_OK){
//                markerModel=(marker_model)getIntent().getSerializableExtra("My marker");
//            }
//        }
//    }
}