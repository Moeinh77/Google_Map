package com.hasani.moein.taan.finaltestmap;

import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.hasani.moein.taan.finaltestmap.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{
//
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

    }

    private void setUpMapIfNeeded() {

                mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                    @Override
                    public void onMyLocationChange(Location arg0) {
                        LatLng latLng=new LatLng(arg0.getLatitude(), arg0.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title("It's Me!").icon(BitmapDescriptorFactory.
                                fromResource(R.drawable.marker2)));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        int zoomLevel = 14; //This goes up to 21
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                    }
                });
        }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        try{
            mMap.setMyLocationEnabled(true);

            setUpMapIfNeeded();


        } catch(SecurityException e){

            Toast.makeText(getApplicationContext(),"App doesn't have the permission to gps... ",Toast.LENGTH_SHORT).show();
        }
}

}
